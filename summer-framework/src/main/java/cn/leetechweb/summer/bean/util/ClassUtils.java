package cn.leetechweb.summer.bean.util;

/**
 * Project Name: summer
 * Create Time: 2020/11/19 0:53
 *
 * @author junyu lee
 **/
public abstract class ClassUtils {
    public static String getClassName(Class<?> clazz) {
        return clazz.getName();
    }

    public static String getClassName(Object object) {
        return getClassName(object.getClass());
    }
}
