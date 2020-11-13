package cn.leetechweb.summer.bean.factory.impl;

import cn.leetechweb.summer.bean.definition.BeanDefinitionRegistry;
import cn.leetechweb.summer.bean.factory.BeanFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * 懒加载bean工厂，提供bean的懒加载服务
 * Project Name: summer
 * Create Time: 2020/11/4 19:33
 *
 * @author junyu lee
 **/
public final class LazyInitBeanFactory implements BeanFactory {

    /**
     * 存储后面懒加载的bean
     */
    private final Map<String, Object> lazyInitializedBeans = new HashMap<>(256);

    /**
     * bean的注册表
     */
    private final BeanDefinitionRegistry beanDefinitions;


    @Override
    public Object getBean(String beanName) {
        return null;
    }

    @Override
    public <T> T getBean(String beanName, Class<T> clazz) {
        return null;
    }

    @Override
    public boolean hasBean(String beanName) {
        return false;
    }

    @Override
    public void addBean(String beanName, Object bean) {

    }

    public LazyInitBeanFactory(BeanDefinitionRegistry beanDefinitions) {
        this.beanDefinitions = beanDefinitions;
    }
}
