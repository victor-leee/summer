package cn.leetechweb.summer.bean;

import cn.leetechweb.summer.bean.factory.BeanFactory;

/**
 * 容器响应接口，实现该接口的类应该自动调用setBeanFactory完成bean工厂注入并且该实例交由summer管理
 * Project Name: summer
 * Create Time: 2020/11/17 0:21
 *
 * @author junyu lee
 **/
public interface ContainerAware {

    /**
     * 配置bean工厂
     * @param beanFactory bean工厂
     */
    void setBeanFactory(BeanFactory beanFactory);

}
