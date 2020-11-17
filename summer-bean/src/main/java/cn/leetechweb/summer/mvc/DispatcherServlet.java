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

        this.invoke(mappingUrl, argumentMapper, request, response);
    }

    private void invoke(String mappingUrl, ArgumentMapper argumentMapper, HttpServletRequest request, HttpServletResponse response) {
        ServletDescriptor descriptor = this.servletMapping.getMapping(mappingUrl);

        MethodInvokeResult invokeResult = methodInvoker.doInvoke(descriptor, argumentMapper);

        if (invokeResult.isRedirect()) {
            doRedirect(response, invokeResult);
        }

        if (invokeResult.isForward()) {
            doForward(request, response, argumentMapper, invokeResult);
        }
    }

    private void doForward(HttpServletRequest request, HttpServletResponse response, ArgumentMapper argumentMapper, MethodInvokeResult result) {
        this.invoke(result.getTargetAddress(), argumentMapper, request, response);
    }

    private void doRedirect(HttpServletResponse response, MethodInvokeResult result) {
        response.setStatus(HttpServletResponse.SC_MOVED_TEMPORARILY);
        if (result.getTargetAddress().startsWith(Constant.HTTP_PREFIX)) {
            response.setHeader(Constant.REDIRECT_RESPONSE_HEADER, result.getTargetAddress());
        }else {
            response.setHeader(Constant.REDIRECT_RESPONSE_HEADER,
                    MvcUtils.mergeMappingUrl(serverConfig.getContext(), result.getTargetAddress()));
        }
    }


}
