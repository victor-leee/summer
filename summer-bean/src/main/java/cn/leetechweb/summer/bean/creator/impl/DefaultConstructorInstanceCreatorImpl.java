package cn.leetechweb.summer.bean.creator.impl;

import cn.leetechweb.summer.bean.creator.InstanceCreator;
import cn.leetechweb.summer.bean.util.ReflectionUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * Project Name: summer
 * Create Time: 2020/11/14 13:40
 *
 * @author junyu lee
 **/
public class DefaultConstructorInstanceCreatorImpl implements InstanceCreator {
    @Override
    public Object create(Class<?> clazz) throws InstantiationException, InvocationTargetException, IllegalAccessException {
        return ReflectionUtils.getDefaultConstructor(clazz).newInstance();
    }

    @Override
    public Object create(Class<?> clazz, Map<String, Object> paramMap) throws InstantiationException, InvocationTargetException, IllegalAccessException {
        return create(clazz);
    }

    @Override
    public Object create(String beanPath, Map<String, Object> paramMap) {
        return null;
    }
}
