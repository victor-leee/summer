package cn.leetechweb.summer.mvc;

import cn.leetechweb.summer.mvc.annotation.Controller;
import cn.leetechweb.summer.mvc.annotation.Mapping;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Project Name: summer
 * Create Time: 2020/11/17 17:30
 *
 * @author junyu lee
 **/
public abstract class MvcUtils {

    /**
     * 根据@Mapping注解返回该bean的映射路径
     * 如果该bean继承于其他bean，则返回嵌套路径
     * @param beanType bean类型
     * @return 映射到该bean的映射路径
     */
    public static String getBaseMappingUrl(Class<?> beanType) {
        if (beanType.isAnnotationPresent(Controller.class)) {
            String mappingUrl = getBaseMappingUrl(beanType.getSuperclass());
            if (beanType.isAnnotationPresent(Mapping.class)) {
                String currentMappingUrl = beanType.getAnnotation(Mapping.class).path();
                mappingUrl = mappingUrl + currentMappingUrl;
            }
            return mappingUrl;
        }
        return "";
    }

    /**
     * 根据bean返回其所有可以用于servlet映射的方法
     * @param beanType bean类型
     * @return 所有的可以用作映射关系的方法
     */
    public static List<Method> getServletMethods(Class<?> beanType) {
        List<Method> methods = new ArrayList<>();
        for (Method method : beanType.getDeclaredMethods()) {
            if (method.isAnnotationPresent(Mapping.class)) {
                methods.add(method);
            }
        }
        return methods;
    }


    /**
     * 将baseUrl和subUrl进行合并操作
     * 如果baseUrl以/结尾，subUrl以/开头，需要去掉一个/
     * 如果baseUrl和subUrl均不以/结尾和开头，则添加一个/
     * @param baseUrl 基地址
     * @param subUrl 子地址
     * @return merge过后的映射地址
     */
    public static String mergeMappingUrl(String baseUrl, String subUrl) {
        if (baseUrl.endsWith(Constant.URL_SEPARATOR) && subUrl.startsWith(Constant.URL_SEPARATOR)) {
            return baseUrl + subUrl.substring(1);
        }
        if (!baseUrl.endsWith(Constant.URL_SEPARATOR) && !subUrl.startsWith(Constant.URL_SEPARATOR)) {
            return baseUrl + Constant.URL_SEPARATOR + subUrl;
        }
        return baseUrl + subUrl;
    }

}
