package cn.leetechweb.summer.bean.handler;

import cn.leetechweb.summer.bean.creator.BeanCreator;
import cn.leetechweb.summer.bean.definition.BeanDefinitionParameter;
import cn.leetechweb.summer.bean.exception.AnnotationContainerInitializationException;
import cn.leetechweb.summer.bean.exception.CycleDependencyException;
import cn.leetechweb.summer.bean.Listener;
import cn.leetechweb.summer.bean.definition.AbstractBeanDefinition;
import cn.leetechweb.summer.bean.definition.BeanDefinitionRegistry;
import cn.leetechweb.summer.bean.factory.BeanFactory;
import cn.leetechweb.summer.bean.util.Assert;
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
public final class BeanDefinitionDependencyInjectionHandler implements Listener<BeanDefinitionRegistry> {

    private final BeanFactory beanFactory;

    private final BeanCreator beanCreator;

    /**
     * 依赖倒置map，能够通过beanName快速查找哪些bean依赖了该bean
     */
    private final Map<String, List<String>> dependedMap = new HashMap<>(256);

    @Override
    public void onEvent(BeanDefinitionRegistry data) {

        // 构建bean之间的依赖树
        Map<String, List<AbstractBeanDefinition>> dependencyTree = new HashMap<>(256);
        for (AbstractBeanDefinition beanDefinition : data) {
            String beanName = beanDefinition.getBeanName();
            String[] dependsOn = beanDefinition.dependsOn();
            List<AbstractBeanDefinition> dependencies = new ArrayList<>();
            for (String dependency : dependsOn) {
                BeanDefinitionParameter parameter = beanDefinition.getParameter(dependency);
                AbstractBeanDefinition dependencyBean = data.getBeanDefinition(parameter);
                dependencies.add(dependencyBean);
                String dependencyBeanName = dependencyBean.getBeanName();
                if (!dependedMap.containsKey(dependencyBeanName)) {
                    dependedMap.put(dependencyBeanName, new ArrayList<>());
                }
                dependedMap.get(dependencyBeanName).add(beanName);
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

    public BeanDefinitionDependencyInjectionHandler(BeanFactory beanFactory, BeanCreator beanCreator) {
        this.beanFactory = beanFactory;
        this.beanCreator = beanCreator;
    }

}
