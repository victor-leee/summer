package cn.leetechweb.summer.bean.handler;

import cn.leetechweb.summer.bean.creator.BeanCreator;
import cn.leetechweb.summer.bean.definition.BeanDefinitionParameter;
import cn.leetechweb.summer.bean.exception.AnnotationContainerInitializationException;
import cn.leetechweb.summer.bean.exception.CycleDependencyException;
import cn.leetechweb.summer.bean.definition.AbstractBeanDefinition;
import cn.leetechweb.summer.bean.definition.BeanDefinitionRegistry;
import cn.leetechweb.summer.bean.factory.BeanFactory;
import cn.leetechweb.summer.bean.util.StringUtils;

import java.util.*;

/**
 * 在容器初始化所有的BeanDefinition后，BeanDefinitionPostHandler负责处理注册表中的信息
 * 这是bean之间处理依赖注入的处理器
 * Project Name: summer
 * Create Time: 2020/11/3 22:22
 *
 * @author junyu lee
 **/
public final class BeanDefinitionCtorDependencyInjectionHandler extends BeanDefinitionInjectionHandler {

    /**
     * 依赖倒置map，能够通过beanName快速查找哪些bean依赖了该bean
     */
    private final Map<String, List<String>> dependedMap = new HashMap<>(256);

    /**
     * 该依赖处理器对由Loader发送过来的Bean定义表作初始化工作
     * 实例化所有的Bean，但是忽略Bean中的所有常量注入(交由下一级监听器处理)
     * @param data 发布者发送的数据 这里代表由Loader发送的Bean定义表
     */
    @Override
    public void onEvent(BeanDefinitionRegistry data) {

        // 构建bean之间的依赖树
        Map<String, List<AbstractBeanDefinition>> dependencyTree = new HashMap<>(256);
        for (AbstractBeanDefinition beanDefinition : data) {
            String beanName = beanDefinition.getBeanName();
            String[] dependsOn = beanDefinition.dependsOn();
            List<AbstractBeanDefinition> dependencies = new ArrayList<>();
            // 检查参数依赖
            for (String dependency : dependsOn) {
                BeanDefinitionParameter parameter = beanDefinition.getParameter(dependency);
                if (!parameter.isConstructorParameter()) {
                    continue;
                }
                AbstractBeanDefinition dependencyBean = data.getBeanDefinition(parameter);
                dependencies.add(dependencyBean);
                String dependencyBeanName = dependencyBean.getBeanName();
                if (!dependedMap.containsKey(dependencyBeanName)) {
                    dependedMap.put(dependencyBeanName, new ArrayList<>());
                }
                dependedMap.get(dependencyBeanName).add(beanName);
            }
            // 检查父子依赖
            AbstractBeanDefinition parent = beanDefinition.getParent();
            if (parent != null) {
                dependencies.add(parent);
                String parentBeanName = parent.getBeanName();
                if (!dependedMap.containsKey(parentBeanName)) {
                    dependedMap.put(parentBeanName, new ArrayList<>());
                }
                dependedMap.get(parentBeanName).add(beanName);
            }
            dependencyTree.put(beanName, dependencies);
        }

        // 对这个依赖树拓扑排序
        Deque<AbstractBeanDefinition> initStack = new LinkedList<>();
        dependencyTree.forEach((beanName, beanDefList) -> {
            if (beanDefList.isEmpty()) {
                initStack.offerLast(data.getBeanDefinition(beanName));
            }
        });
        while (!initStack.isEmpty()) {
            AbstractBeanDefinition beanDefinition = initStack.pollLast();
            String beanName = beanDefinition.getBeanName();
            Object bean;
            try {
                bean = this.beanCreator.create(beanDefinition, data);
            }catch (Exception e) {
                throw new AnnotationContainerInitializationException("bean初始化错误:{}", e.getMessage());
            }
            this.beanFactory.addBean(beanName, bean);


            if (dependedMap.containsKey(beanName)) {
                // 将依赖该bean的bean的依赖列表减一
                List<String> isDependedBy = dependedMap.get(beanName);
                for (String dependent : isDependedBy) {
                    dependencyTree.get(dependent).remove(beanDefinition);
                    // 该bean没有任何依赖，则初始化该bean
                    if (dependencyTree.get(dependent).size() == 0) {
                        initStack.offerLast(data.getBeanDefinition(dependent));
                    }
                }
            }
        }

        dependencyTree.forEach((beanName, beanDefList) -> {
            if (beanDefList.size() > 0) {
                throw new CycleDependencyException(StringUtils.format("发现循环依赖，请检查{}", false,
                        beanName));
            }
        });

        this.dependedMap.clear();

    }

    public BeanDefinitionCtorDependencyInjectionHandler(BeanFactory beanFactory, BeanCreator beanCreator) {
        super(beanFactory, beanCreator);
    }

}
