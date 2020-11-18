package cn.leetechweb.summer.mvc.support;

import cn.leetechweb.summer.mvc.exception.UnknownRequestMethodException;

/**
 * Http方法枚举类
 * Project Name: summer
 * Create Time: 2020/11/18 21:08
 *
 * @author junyu lee
 **/
public enum HttpMethod {

    /**
     * GET请求
     */
    GET("get"),

    /**
     * POST请求
     */
    POST("post"),

    /**
     * PUT请求
     */
    PUT("put"),

    /**
     * DELETE请求
     */
    DELETE("delete");

    String methodName;

    HttpMethod(String methodName) {
        this.methodName = methodName;
    }

    public static HttpMethod methodOf(String name) {
        for (HttpMethod method : values()) {
            if (method.getMethodName().equals(name.toLowerCase())) {
                return method;
            }
        }
        throw new UnknownRequestMethodException("未知请求方法:{}", name);
    }

    public String getMethodName() {
        return methodName;
    }
}
