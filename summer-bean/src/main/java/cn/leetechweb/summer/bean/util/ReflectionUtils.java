package cn.leetechweb.summer.bean.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

/**
 * 反射工具类
 * Project Name: summer
 * Create Time: 2020/11/4 14:47
 *
 * @author junyu lee
 **/
public abstract class ReflectionUtils {

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

}
