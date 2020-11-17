package cn.leetechweb.summer.bean.creator.impl;

import cn.leetechweb.summer.bean.creator.InstanceCreator;
import cn.leetechweb.summer.bean.util.BeanUtils;

import java.util.Map;

/**
 * Project Name: summer
 * Create Time: 2020/11/13 22:28
 *
 * @author junyu lee
 **/
public class ConstructorInstanceCreatorImpl implements InstanceCreator {

    @Override
    public Object create(Object bean, boolean isCreated) {
        return null;
    }

    @Override
    public Object create(Object bean, boolean isCreated, Map<String, Object> paramMap) {
        if (isCreated) {
            return bean;
        }
        return BeanUtils.createBeanByConstructor((Class<?>) bean, paramMap);
    }
}
