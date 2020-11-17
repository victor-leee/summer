package cn.leetechweb.summer.bean.handler;

import cn.leetechweb.summer.bean.creator.BeanCreator;
import cn.leetechweb.summer.bean.definition.AbstractBeanDefinition;
import cn.leetechweb.summer.bean.definition.BeanDefinitionRegistry;
import cn.leetechweb.summer.bean.factory.BeanFactory;
import cn.leetechweb.summer.bean.handler.creation.PostCreationProcessor;

/**
 * Project Name: summer
 * Create Time: 2020/11/17 16:02
 *
 * @author junyu lee
 **/
public class BeanDefinitionPostBeanProcessor extends BeanDefinitionInjectionHandler {
    public BeanDefinitionPostBeanProcessor(BeanFactory beanFactory, BeanCreator beanCreator) {
        super(beanFactory, beanCreator);
    }

    @Override
    public void onEvent(BeanDefinitionRegistry data) {
        for (AbstractBeanDefinition beanDefinition : data) {
            Object bean = this.beanFactory.getBean(beanDefinition.getBeanName());
            if (bean instanceof PostCreationProcessor) {
                ((PostCreationProcessor) bean).invoke();
            }
        }
    }
}
