package cn.leetechweb.summer.mvc.mapping;

import cn.leetechweb.summer.mvc.MvcUtils;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * Servlet描述器，用于包含基本的servlet信息
 * Project Name: summer
 * Create Time: 2020/11/17 17:21
 *
 * @author junyu lee
 **/
public class ServletDescriptor {

    /**
     * Servlet映射方法
     * 也就是一个请求来了过后要执行的方法
     */
    private Method method;

    /**
     * 对应的bean实体
     */
    private Object bean;

    /**
     * 映射Url
     */
    private String mappingUrl;


    private Class<?>[] methodParameterTypes;

    private String[] urlSegments;

    public void setMethod(Method method) {
        this.method = method;
        Parameter[] parameters = method.getParameters();
        Class<?>[] methodParameterTypes = new Class[parameters.length];
        for (int i = 0; i < methodParameterTypes.length; i++) {
            methodParameterTypes[i] = parameters[i].getType();
        }
        this.methodParameterTypes = methodParameterTypes;
    }

    public Class<?>[] getMethodParameterTypes() {
        return methodParameterTypes;
    }

    public Method getMethod() {
        return method;
    }

    public String getMappingUrl() {
        return mappingUrl;
    }

    public void setMappingUrl(String mappingUrl) {
        this.mappingUrl = mappingUrl;
        this.urlSegments = MvcUtils.getUrlSegments(mappingUrl);
    }

    public String[] getUrlSegments() {
        return urlSegments;
    }

    public void setBean(Object bean) {
        this.bean = bean;
    }

    public Object getBean() {
        return bean;
    }

    /**
     * 执行该方法
     * @param args 参数
     */
    public void invoke(Object... args) {
        try {
            this.method.invoke(this.bean, args);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}
