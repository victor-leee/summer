package cn.leetechweb.summer.bean.handler;

import cn.leetechweb.summer.bean.creator.BeanCreator;
import cn.leetechweb.summer.bean.definition.AbstractBeanDefinition;
import cn.leetechweb.summer.bean.definition.BeanDefinitionRegistry;
import cn.leetechweb.summer.bean.exception.AnnotationContainerInitializationException;
import cn.leetechweb.summer.bean.factory.BeanFactory;
import cn.leetechweb.summer.bean.loader.Loader;

/**
 * Project Name: summer
 * Create Time: 2020/11/17 18:10
 *
 * @author junyu lee
 **/
public class BeanDefinitionLoaderHandler extends BeanDefinitionInjectionHandler {

    public BeanDefinitionLoaderHandler(BeanFactory beanFactory, BeanCreator beanCreator) {
        super(beanFactory, beanCreator);
    }

    @Override
    public void onEvent(BeanDefinitionRegistry data) {
        for (AbstractBeanDefinition beanDefinition : data) {
            Object bean = beanFactory.getBean(beanDefinition.getBeanName());
            if (bean instanceof Loader) {
                try {
                    ((Loader) bean).load();
                }catch (Exception e) {
                    throw new AnnotationContainerInitializationException("执行loader遍历器时发生错误，信息:{}", e.getMessage());
                }
            }
        }
    }
}
