package cn.leetechweb.summer.bean.handler;

import cn.leetechweb.summer.bean.Constant;
import cn.leetechweb.summer.bean.annotation.Bean;
import cn.leetechweb.summer.bean.creator.BeanCreator;
import cn.leetechweb.summer.bean.exception.AnnotationContainerInitializationException;
import cn.leetechweb.summer.bean.exception.CycleDependencyException;
import cn.leetechweb.summer.bean.Listener;
import cn.leetechweb.summer.bean.definition.AbstractBeanDefinition;
import cn.leetechweb.summer.bean.definition.BeanDefinitionParameter;
import cn.leetechweb.summer.bean.definition.BeanDefinitionRegistry;
import cn.leetechweb.summer.bean.factory.BeanFactory;
import cn.leetechweb.summer.bean.util.ReflectionUtils;
import cn.leetechweb.summer.bean.util.StringUtils;

import java.lang.reflect.Method;
import java.util.*;

/**
 * 在容器初始化所有的BeanDefinition后，BeanDefinitionPostHandler负责处理注册表中的信息
 * 这是bean之间处理依赖注入的后处理器
 * Project Name: summer
 * Create Time: 2020/11/3 22:22
 *
 * @author junyu lee
 **/
public final class BeanDefinitionDependencyInjectionHandler implements Listener<BeanDefinitionRegistry> {

    private final BeanFactory beanFactory;

    private final BeanCreator beanCreator;

    /**
     * 实例化对象时，对于已经创建好的对象，暂存到这个map中，供可能的依赖使用
     * 注意创建完毕后需要将这个map清空节省内存空间
     */
    private final Map<String, Object> alreadyCreatedBeanMap = new HashMap<>(256);

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
                dependencies.add(data.getBeanDefinition(dependency));
                if (!dependedMap.containsKey(dependency)) {
                    dependedMap.put(dependency, new ArrayList<>());
                }
                dependedMap.get(dependency).add(beanName);
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
            Map<String, Object> beanParamMap = getParameters(beanDefinition);
            String beanPath = beanDefinition.getBeanCompletePath();
            String beanName = beanDefinition.getBeanName();
            Object bean;
            Map<String, Object> nestedBeans;
            try {
                bean = this.beanCreator.create(beanPath, beanParamMap);
                nestedBeans = getNestedBeans(bean);
            }catch (Exception e) {
                throw new AnnotationContainerInitializationException("bean初始化错误");
            }
            this.beanFactory.addBean(beanName, bean);
            nestedBeans.forEach(this.beanFactory::addBean);

            // 如果这个bean有被依赖的bean，将其放入dependencyInitializationMap中备用
            if (dependedMap.containsKey(beanName)) {
                alreadyCreatedBeanMap.put(beanName, bean);
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

        this.alreadyCreatedBeanMap.clear();
        this.dependedMap.clear();

    }

    public BeanDefinitionDependencyInjectionHandler(BeanFactory beanFactory, BeanCreator beanCreator) {
        this.beanFactory = beanFactory;
        this.beanCreator = beanCreator;
    }

    /**
     * 取出beanDef的参数
     * @param definition bean定义类对象
     */
    private Map<String, Object> getParameters(AbstractBeanDefinition definition) {
        Map<String, Object> parameters = new HashMap<>(32);
        String[] paramNames = definition.getParameterNames();
        for (String paramName : paramNames) {
            BeanDefinitionParameter parameter = definition.getParameter(paramName);
            Object paramValue = parameter.isReference() ? alreadyCreatedBeanMap.get(parameter.getParameterValue())
                    : parameter.getParameterValue();
            parameters.put(paramName, paramValue);
        }
        return parameters;
    }

    /**
     * 返回bean实体中的嵌套beans
     * @param bean bean实体
     * @return bean实体中的嵌套bean
     */
    private Map<String, Object> getNestedBeans(Object bean) {
        List<Method> methods = ReflectionUtils.getMethodsProducingBeans(bean.getClass());
        Map<String, Object> result = new HashMap<>(16);
        for (Method method : methods) {
            ReflectionUtils.makeAccessible(method);
            String beanName = method.getAnnotation(Bean.class).name();
            if (Constant.EMPTY_STRING.equals(beanName)) {
                beanName = method.getReturnType().getSimpleName();
            }
            try {
                result.put(beanName, method.invoke(bean));
            }catch (Exception e) {
                e.printStackTrace();
                throw new AnnotationContainerInitializationException("初始化一个inner bean发生错误");
            }
        }
        return result;
    }

}
