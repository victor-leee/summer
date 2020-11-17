package cn.leetechweb.summer.bean.handler.creation;

import cn.leetechweb.summer.bean.ContainerAware;
import cn.leetechweb.summer.bean.factory.BeanFactory;


/**
 * Project Name: summer
 * Create Time: 2020/11/17 15:48
 *
 * @author junyu lee
 **/
public abstract class AbstractBeanPostProcessor implements ContainerAware, PostCreationProcessor {

    protected BeanFactory beanFactory;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

}
