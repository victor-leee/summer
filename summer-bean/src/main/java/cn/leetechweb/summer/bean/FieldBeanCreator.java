package cn.leetechweb.summer.bean;

import cn.leetechweb.summer.bean.util.ReflectionUtils;
import cn.leetechweb.summer.bean.util.StringUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * Project Name: summer
 * Create Time: 2020/11/13 22:37
 *
 * @author junyu lee
 **/
public class FieldBeanCreator implements BeanCreator {

    @Override
    public Object create(Class<?> clazz) {
        return null;
    }

    @Override
    public Object create(Class<?> clazz, Map<String, Object> paramMap) throws InstantiationException, InvocationTargetException, IllegalAccessException {
        Constructor<?> defaultCtor = ReflectionUtils.getDefaultConstructor(clazz);
        Object bean = defaultCtor.newInstance();
        ReflectionUtils.setFieldParameters(bean, paramMap);
        return bean;
    }

    @Override
    public Object create(String beanPath, Map<String, Object> paramMap) {
        try {
            Class<?> clazz = Thread.currentThread().getContextClassLoader().loadClass(beanPath);
            return this.create(clazz, paramMap);
        }catch (Exception e) {
            e.printStackTrace();
        }
        throw new IllegalArgumentException(StringUtils.format("初始化{}，参数map为{}时发生错误", false,
                beanPath, paramMap));
    }
}
