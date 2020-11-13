package cn.leetechweb.summer.bean;

import cn.leetechweb.summer.bean.util.BeanUtils;

import java.util.Map;

/**
 * Project Name: summer
 * Create Time: 2020/11/13 22:28
 *
 * @author junyu lee
 **/
public class ConstructorBeanCreator implements BeanCreator {
    @Override
    public Object create(Class<?> clazz) {
        return null;
    }

    @Override
    public Object create(Class<?> clazz, Map<String, Object> paramMap) {
        return BeanUtils.createBeanByConstructor(clazz, paramMap);
    }

    @Override
    public Object create(String beanPath, Map<String, Object> paramMap) {
        return BeanUtils.createBeanByConstructor(beanPath, paramMap);
    }
}
