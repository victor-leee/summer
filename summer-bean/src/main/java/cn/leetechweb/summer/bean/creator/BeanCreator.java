package cn.leetechweb.summer.bean.creator;

import cn.leetechweb.summer.bean.definition.AbstractBeanDefinition;
import cn.leetechweb.summer.bean.definition.BeanDefinitionParameter;
import cn.leetechweb.summer.bean.definition.BeanDefinitionRegistry;
import cn.leetechweb.summer.bean.definition.impl.AnnotationBeanDefinitionImpl;
import cn.leetechweb.summer.bean.factory.BeanFactory;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * Project Name: summer
 * Create Time: 2020/11/15 22:24
 *
 * @author junyu lee
 **/
public final class BeanCreator {

    /**
     * 实例构建器
     * 通过构造函数，并经过一定的修饰，构造一个示例对象
     */
    private final InstanceCreator instanceCreator;

    private final BeanFactory beanFactory;

    public Object create(AbstractBeanDefinition beanDefinition, BeanDefinitionRegistry registry) throws IllegalAccessException, InstantiationException, InvocationTargetException {
        // 如果这个bean是通过@Bean生成的，则invoke对应的方法生成
        Object[] args = new Object[beanDefinition.dependsOn().length];
        // 先生成beanDefinition需要的参数
        for (int i = 0; i < args.length; i++) {
            BeanDefinitionParameter parameter = beanDefinition.getParameter(beanDefinition.dependsOn()[i]);
            AbstractBeanDefinition dependencyBeanDef = registry.getBeanDefinition(parameter);
            args[i] = this.beanFactory.getBean(dependencyBeanDef.getBeanName());
        }
        if (beanDefinition.isMethodProduce()) {
            Object parentBean = beanFactory.getBean(beanDefinition.getParent().getBeanName());
            return ((AnnotationBeanDefinitionImpl) beanDefinition).getBeanMethod().invoke(parentBean, args);
        }
        Map<String, Object> beanParam = new HashMap<>(16);
        for (int i = 0; i < args.length; i++) {
            beanParam.put(beanDefinition.dependsOn()[i], args[i]);
        }
        // 如果该bean已经创建，则只需要修饰一下就好了
        String beanName = beanDefinition.getBeanName();
        boolean isCreated = this.beanFactory.hasBean(beanName);
        return this.instanceCreator.create(isCreated ?
                this.beanFactory.getBean(beanName) : beanDefinition.beanType(), isCreated, beanParam);
    }

    public BeanCreator(InstanceCreator instanceCreator, BeanFactory beanFactory) {
        this.instanceCreator = instanceCreator;
        this.beanFactory = beanFactory;
    }

}
