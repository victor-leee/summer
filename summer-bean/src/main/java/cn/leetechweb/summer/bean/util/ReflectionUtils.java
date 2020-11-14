package cn.leetechweb.summer.bean.util;

import cn.leetechweb.summer.bean.Filter;
import cn.leetechweb.summer.bean.annotation.Autowired;
import cn.leetechweb.summer.bean.annotation.AutowiredFilter;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static cn.leetechweb.summer.bean.Constant.*;

/**
 * 反射工具类
 * Project Name: summer
 * Create Time: 2020/11/4 14:47
 *
 * @author junyu lee
 **/
public abstract class ReflectionUtils {

    public static final int NO_PARAMS = 0;

    /**
     * 让构造函数可以被访问到
     * 对于非public的构造函数，或者非public的类，需要使用此方法
     * @param ctor 构造函数
     */
    public static void makeAccessible(Constructor<?> ctor) {
        if ((!Modifier.isPublic(ctor.getModifiers()) ||
                !Modifier.isPublic(ctor.getDeclaringClass().getModifiers())) && !ctor.isAccessible()) {
            ctor.setAccessible(true);
        }
    }

    /**
     * 返回类中带有@Autowired注解的字段
     * @see cn.leetechweb.summer.bean.annotation.Autowired
     * @param clazz 类
     * @return 带有@Autowired注解的field
     */
    public static List<Field> getFieldsNeedAutowired(Class<?> clazz) {
        Field[] fields = clazz.getDeclaredFields();
        Filter<Field> fieldFilter = new AutowiredFilter();
        List<Field> result = new ArrayList<>();
        for (Field field : fields) {
            if (fieldFilter.stay(field)) {
                result.add(field);
            }
        }
        return result;
    }

    /**
     * 返回默认构造函数
     * 如果没有默认构造函数则抛出错误
     * @param clazz 类
     * @return 默认构造函数
     * @throws InstantiationError 没有默认构造函数
     */
    public static Constructor<?> getDefaultConstructor(Class<?> clazz) throws InstantiationException {
        Constructor<?>[] constructors = clazz.getDeclaredConstructors();
        for (Constructor<?> constructor : constructors) {
            if (constructor.getParameterCount() == NO_PARAMS) {
                return constructor;
            }
        }
        throw new InstantiationException("没有默认构造函数");
    }

    /**
     * 将参数设置在bean上的字段中
     * @param bean bean实体
     * @param paramMap 参数map
     */
    public static void setFieldParameters(Object bean, Map<String,Object> paramMap) throws IllegalAccessException {
        Assert.isNotNull(bean);
        List<Field> fields = getFieldsNeedAutowired(bean.getClass());
        for (Field field : fields) {
            Object fieldVal = paramMap.get(field.getType().getName());
            Assert.isNotNull(fieldVal);
            makeAccessible(field);
            field.set(bean, fieldVal);
        }
    }

    public static void makeAccessible(AccessibleObject accessibleObject) {
        accessibleObject.setAccessible(true);
    }

    public static List<Method> getMethodsNeedAutowired(Object bean) {
        return getMethodsNeedAutowired(bean.getClass());
    }

    public static List<Method> getMethodsNeedAutowired(Class<?> clazz) {
        Method[] allMethods = clazz.getDeclaredMethods();
        List<Method> result = new ArrayList<>();
        for (Method method : allMethods) {
            if (method.isAnnotationPresent(Autowired.class)) {
                if (method.getName().startsWith(SETTER_PREFIX)) {
                    result.add(method);
                }
            }
        }
        return result;
    }

}
