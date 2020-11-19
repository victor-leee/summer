package cn.leetechweb.summer.bean.handler;

import cn.leetechweb.summer.bean.Listener;
import cn.leetechweb.summer.bean.creator.BeanCreator;
import cn.leetechweb.summer.bean.definition.BeanDefinitionRegistry;
import cn.leetechweb.summer.bean.factory.BeanFactory;

/**
 * Project Name: summer
 * Create Time: 2020/11/17 10:58
 *
 * @author junyu lee
 **/
public abstract class BeanDefinitionInjectionHandler implements Listener<BeanDefinitionRegistry> {

    protected final BeanFactory beanFactory;

    protected final BeanCreator beanCreator;

    protected BeanDefinitionInjectionHandler(BeanFactory beanFactory, BeanCreator beanCreator) {
        this.beanFactory = beanFactory;
        this.beanCreator = beanCreator;
    }

}
