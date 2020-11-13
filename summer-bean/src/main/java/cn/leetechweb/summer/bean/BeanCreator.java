package cn.leetechweb.summer.bean;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * Project Name: summer
 * Create Time: 2020/11/13 22:27
 *
 * @author junyu lee
 **/
public interface BeanCreator {

    Object create(Class<?> clazz);

    Object create(Class<?> clazz, Map<String,Object> paramMap) throws InstantiationException, InvocationTargetException, IllegalAccessException;

    Object create(String beanPath, Map<String,Object> paramMap);

}
