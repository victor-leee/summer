package cn.leetechweb.summer.bean.factory.impl;

import cn.leetechweb.summer.bean.factory.BeanFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * Project Name: summer
 * Create Time: 2020/11/4 1:06
 *
 * @author junyu lee
 **/
public final class SimpleBeanFactory implements BeanFactory {

    /**
     * 默认的beanMap初始化容量
     */
    static final int DEFAULT_BEAN_COUNT = 256;

    /**
     * bean map
     */
    Map<String, Object> beanMap = new HashMap<>(DEFAULT_BEAN_COUNT);

    @Override
    public Object getBean(String beanName) {
        return this.beanMap.get(beanName);
    }

    @Override
    public <T> T getBean(String beanName, Class<T> clazz) {
        return (T) this.beanMap.get(beanName);
    }

    @Override
    public boolean hasBean(String beanName) {
        return this.beanMap.containsKey(beanName);
    }

    @Override
    public void addBean(String beanName, Object bean) {
        this.beanMap.put(beanName, bean);
    }

}
