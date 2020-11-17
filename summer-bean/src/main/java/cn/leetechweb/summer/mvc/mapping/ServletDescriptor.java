package cn.leetechweb.summer.mvc.mapping;

import java.lang.reflect.Method;

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
     * 映射Url
     */
    private String mappingUrl;

    public void setMethod(Method method) {
        this.method = method;
    }

    public Method getMethod() {
        return method;
    }

    public String getMappingUrl() {
        return mappingUrl;
    }

    public void setMappingUrl(String mappingUrl) {
        this.mappingUrl = mappingUrl;
    }

    /**
     * 执行该方法
     * @param args 参数
     */
    public void invoke(Object... args) {
        // TODO: 2020/11/17
    }
}
