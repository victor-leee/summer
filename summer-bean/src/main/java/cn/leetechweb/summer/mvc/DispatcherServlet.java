package cn.leetechweb.summer.mvc;

import cn.leetechweb.summer.bean.util.StringUtils;
import cn.leetechweb.summer.mvc.mapping.ServletDescriptor;
import cn.leetechweb.summer.mvc.mapping.argument.ArgumentFactory;
import cn.leetechweb.summer.mvc.mapping.argument.ArgumentMapper;
import cn.leetechweb.summer.mvc.support.HttpMethod;
import cn.leetechweb.summer.mvc.support.MethodInvokeResult;
import cn.leetechweb.summer.mvc.view.View;

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

        ServletDescriptor descriptor = this.servletMapping.getMapping(mappingUrl,
                HttpMethod.methodOf(request.getMethod()));

        MethodInvokeResult invokeResult = methodInvoker.doInvoke(descriptor, argumentMapper);

        processResult(request, response, invokeResult);
    }

    private void processResult(HttpServletRequest request, HttpServletResponse response, MethodInvokeResult result) {
        response.setStatus(result.getHttpStatus().getValue());
        // 单独处理转发与重定向
        if (result.isForward()) {
            try {
                request.getRequestDispatcher(result.getUrl()).forward(request, response);
            }catch (Exception e) {
                logger.severe(StringUtils.format("转发发生错误:{}", false, e.getMessage()));
            }
        }

        if (result.isRedirect()) {
            response.setHeader(Constant.REDIRECT_RESPONSE_HEADER, result.getUrl());
            return;
        }

        // 其余的所有情况，根据包装的View实例进行处理
        if (result.getView() == null) {
            logger.warning("View空");
        }else {
            View view = result.getView();
            view.render(request, response);
        }
    }


}
