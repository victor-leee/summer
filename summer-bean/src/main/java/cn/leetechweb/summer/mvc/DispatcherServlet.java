package cn.leetechweb.summer.mvc;

import cn.leetechweb.summer.mvc.mapping.ServletDescriptor;
import cn.leetechweb.summer.mvc.mapping.argument.ArgumentFactory;
import cn.leetechweb.summer.mvc.mapping.argument.ArgumentMapper;
import cn.leetechweb.summer.mvc.support.MethodInvokeResult;

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

        ArgumentMapper argumentMapper = ArgumentFactory.getArgumentMapper(request);

        String mappingUrl = request.getServletPath();

        ServletDescriptor descriptor = this.servletMapping.getMapping(mappingUrl);

        MethodInvokeResult invokeResult = methodInvoker.doInvoke(descriptor, argumentMapper);

        finalProcess(invokeResult, request, response);
    }

    private void finalProcess(MethodInvokeResult invokeResult, HttpServletRequest request, HttpServletResponse response) {

    }


}
