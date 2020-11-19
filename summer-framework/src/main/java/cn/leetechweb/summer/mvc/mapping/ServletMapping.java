package cn.leetechweb.summer.mvc.mapping;

import cn.leetechweb.summer.mvc.support.HttpMethod;

import java.util.List;
import java.util.Set;

/**
 * Project Name: summer
 * Create Time: 2020/11/17 17:20
 *
 * @author junyu lee
 **/
public interface ServletMapping {

    /**
     * 根据请求Url获取相应的Servlet
     * @param urlPattern 请求Url
     * @param httpMethod HTTP方法
     * @return Servlet描述
     */
    ServletDescriptor getMapping(String urlPattern, HttpMethod httpMethod);


    /**
     * 新增servlet描述
     * @param servletDescriptor servlet描述
     * @param httpMethod Http方法
     */
    void addServletDescriptor(ServletDescriptor servletDescriptor, HttpMethod httpMethod);

    /**
     * 返回所有的处理器路径
     * @param httpMethod HTTP请求方法
     * @return 所有的servlet路径
     */
    Set<String> getAllServletPaths(HttpMethod httpMethod);

}
