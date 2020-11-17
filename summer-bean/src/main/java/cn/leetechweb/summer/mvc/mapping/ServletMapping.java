package cn.leetechweb.summer.mvc.mapping;

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
     * @return Servlet描述
     */
    ServletDescriptor getMapping(String urlPattern);


    /**
     * 新增servlet描述
     * @param servletDescriptor servlet描述
     */
    void addServletDescriptor(ServletDescriptor servletDescriptor);

}
