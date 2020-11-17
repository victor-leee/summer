package cn.leetechweb.summer.bean.handler;

import cn.leetechweb.summer.bean.ContainerAware;
import cn.leetechweb.summer.bean.creator.BeanCreator;
import cn.leetechweb.summer.bean.definition.AbstractBeanDefinition;
import cn.leetechweb.summer.bean.definition.BeanDefinitionRegistry;
import cn.leetechweb.summer.bean.factory.BeanFactory;

/**
 * Project Name: summer
 * Create Time: 2020/11/17 15:14
 *
 * @author junyu lee
 **/
public class BeanDefinitionContainerAwareInjectionHandler extends BeanDefinitionInjectionHandler {
    public BeanDefinitionContainerAwareInjectionHandler(BeanFactory beanFactory, BeanCreator beanCreator) {
        super(beanFactory, beanCreator);
    }

    @Override
    public void onEvent(BeanDefinitionRegistry data) {
        for (AbstractBeanDefinition beanDefinition : data) {
            Object bean = this.beanFactory.getBean(beanDefinition.getBeanName());
            if (bean instanceof ContainerAware) {
                ((ContainerAware) bean).setBeanFactory(this.beanFactory);
            }
        }
    }
}
