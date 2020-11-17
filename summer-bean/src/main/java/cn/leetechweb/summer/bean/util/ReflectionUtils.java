package cn.leetechweb.summer.bean.util;

import cn.leetechweb.summer.bean.annotation.Autowired;
import cn.leetechweb.summer.bean.annotation.Bean;
import cn.leetechweb.summer.bean.annotation.Resource;
import cn.leetechweb.summer.bean.annotation.Value;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;


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
        return filterField(clazz, field -> field.isAnnotationPresent(Autowired.class)
        || field.isAnnotationPresent(Resource.class));
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

    public static void setFieldParam(Object bean, Field field, Object fieldValue) throws IllegalAccessException {
        Assert.allNotNull("空参数", bean, field);
        makeAccessible(field);
        field.set(bean, fieldValue);
    }

    public static void setFieldParameter(Object bean, String fieldName, String fieldValue) throws NoSuchFieldException, IllegalAccessException {
        Assert.allNotNull(StringUtils.format("设置bean的field参数时发生错误，bean:{}，" +
                "fieldName:{}，fieldValue:{}", false, bean, fieldName, fieldValue));
        Field field = bean.getClass().getDeclaredField(fieldName);
        Assert.isNotNull(field, "field名为{}的field为null", fieldName);
        makeAccessible(field);
        field.set(bean, ConvertUtils.convert(field.getType(), fieldValue));
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
                result.add(method);
            }
        }
        return result;
    }

    public static List<Field> getFieldsNeedInjectionValue(Object bean) {
        return getFieldsNeedInjectionValue(bean.getClass());
    }

    public static List<Field> getFieldsNeedInjectionValue(Class<?> clazz) {
        return filterField(clazz, field -> field.isAnnotationPresent(Value.class));
    }

    /**
     * 根据过滤条件对类的fields做一次过滤
     * @param clazz 类对象
     * @param fieldPredicate 类参数过滤器
     * @return 过滤过后的fields
     */
    public static List<Field> filterField(Class<?> clazz, Predicate<Field> fieldPredicate) {
        List<Field> result = new ArrayList<>();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if (fieldPredicate.test(field)) {
                result.add(field);
            }
        }
        return result;
    }

    public static List<Method> getMethodsProducingBeans(Class<?> clazz) {
        return filterMethods(clazz, method -> method.isAnnotationPresent(Bean.class));
    }

    public static List<Class<?>> getInterfaces(Class<?> clazz) {
        List<Class<?>> result = new ArrayList<>();
        while (!Object.class.equals(clazz)) {
            Collections.addAll(result, clazz.getInterfaces());
            clazz = clazz.getSuperclass();
        }
        return result;
    }

    private static List<Method> filterMethods(Class<?> clazz, Predicate<Method> predicate) {
        List<Method> result = new ArrayList<>();
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            if (predicate.test(method)) {
                result.add(method);
            }
        }
        return result;
    }

}
