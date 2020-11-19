package cn.leetechweb.summer.bean.handler;

import cn.leetechweb.summer.bean.Listener;
import cn.leetechweb.summer.bean.creator.BeanCreator;
import cn.leetechweb.summer.bean.definition.AbstractBeanDefinition;
import cn.leetechweb.summer.bean.definition.BeanDefinitionParameter;
import cn.leetechweb.summer.bean.definition.BeanDefinitionRegistry;
import cn.leetechweb.summer.bean.exception.AnnotationContainerInitializationException;
import cn.leetechweb.summer.bean.factory.BeanFactory;

/**
 * Project Name: summer
 * Create Time: 2020/11/17 10:57
 *
 * @author junyu lee
 **/
public final class BeanDefinitionFieldDependencyInjectionHandler extends BeanDefinitionInjectionHandler {

    public BeanDefinitionFieldDependencyInjectionHandler(BeanFactory beanFactory, BeanCreator beanCreator) {
        super(beanFactory, beanCreator);
    }

    @Override
    public void onEvent(BeanDefinitionRegistry data) {
        for (AbstractBeanDefinition beanDefinition : data) {
            // 由于是作字段注入，所以直接调用接口就好了
            try {
                beanCreator.create(beanDefinition, data);
            }catch (Exception e) {
                throw new AnnotationContainerInitializationException("为{}注册字段发生错误", false,
                        beanDefinition.getBeanName());
            }
        }
    }
}
