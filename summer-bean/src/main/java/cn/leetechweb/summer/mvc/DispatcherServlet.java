package cn.leetechweb.summer.mvc;

import cn.leetechweb.summer.mvc.mapping.ServletDescriptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Project Name: summer
 * Create Time: 2020/11/17 0:16
 *
 * @author junyu lee
 **/
public final class DispatcherServlet extends SummerServletBean {

    public static final String SERVLET_NAME = "DispatcherServlet";

    @Override
    protected void doInternalDispatch(HttpServletRequest request, HttpServletResponse response) {
        String mappingUrl = request.getServletPath();
        ServletDescriptor descriptor = this.servletMapping.getMapping(mappingUrl);
        descriptor.invoke("1", 2);

    }


}
