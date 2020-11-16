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

    private final InstanceCreator instanceCreator;

    private final BeanFactory beanFactory;

    public Object create(AbstractBeanDefinition beanDefinition, BeanDefinitionRegistry registry) throws IllegalAccessException, InstantiationException, InvocationTargetException {
        // 如果这个bean是通过@Bean生成的，则invoke对应的方法生成
        Object[] args = new Object[beanDefinition.dependsOn().length];
        for (int i = 0; i < args.length; i++) {
            BeanDefinitionParameter parameter = beanDefinition.getParameter(beanDefinition.dependsOn()[i]);
            AbstractBeanDefinition dependencyBeanDef = registry.getBeanDefinition(parameter);
            args[i] = this.beanFactory.getBean(dependencyBeanDef.getBeanName());
        }
        if (beanDefinition.isMethodProduce()) {
            AnnotationBeanDefinitionImpl beanDef = (AnnotationBeanDefinitionImpl) beanDefinition;
            Object parentBean = beanFactory.getBean(beanDef.getParentBeanDef().getBeanName());
            Object[] argsExcludeParentBean = new Object[Math.max(args.length - 1, 0)];
            if (args.length - 1 >= 0) {
                System.arraycopy(args, 0, argsExcludeParentBean, 0, args.length - 1);
            }
            return beanDef.getBeanMethod().invoke(parentBean, argsExcludeParentBean);
        }
        Map<String, Object> beanParam = new HashMap<>(16);
        for (int i = 0; i < args.length; i++) {
            beanParam.put(beanDefinition.dependsOn()[i], args[i]);
        }
        return this.instanceCreator.create(beanDefinition.beanType(), beanParam);
    }

    public BeanCreator(InstanceCreator instanceCreator, BeanFactory beanFactory) {
        this.instanceCreator = instanceCreator;
        this.beanFactory = beanFactory;
    }

}
