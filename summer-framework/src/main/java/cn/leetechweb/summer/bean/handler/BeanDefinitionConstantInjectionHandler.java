package cn.leetechweb.summer.bean.handler;

import cn.leetechweb.summer.bean.creator.BeanCreator;
import cn.leetechweb.summer.bean.definition.AbstractBeanDefinition;
import cn.leetechweb.summer.bean.definition.BeanDefinitionParameter;
import cn.leetechweb.summer.bean.definition.BeanDefinitionRegistry;
import cn.leetechweb.summer.bean.factory.BeanFactory;
import cn.leetechweb.summer.bean.util.ReflectionUtils;

/**
 * 在所有的bean初始化完毕后，该处理器处理常量注入
 * Project Name: summer
 * Create Time: 2020/11/14 15:54
 *
 * @author junyu lee
 **/
public final class BeanDefinitionConstantInjectionHandler extends BeanDefinitionInjectionHandler {

    public BeanDefinitionConstantInjectionHandler(BeanFactory beanFactory, BeanCreator beanCreator) {
        super(beanFactory, beanCreator);
    }

    @Override
    public void onEvent(BeanDefinitionRegistry data) {
        for (AbstractBeanDefinition beanDefinition : data) {
            String[] parameterNames = beanDefinition.getParameterNames();
            for (String parameterName : parameterNames) {
                BeanDefinitionParameter parameter = beanDefinition.getParameter(parameterName);
                if (!parameter.isReference()) {
                    String beanName = beanDefinition.getBeanName();
                    if (beanFactory.hasBean(beanName)) {
                        Object bean = beanFactory.getBean(beanName);
                        try {
                            ReflectionUtils.setFieldParameter(bean, parameter.getParameterName(),
                                    parameter.getParameterValue());
                        }catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }

}
