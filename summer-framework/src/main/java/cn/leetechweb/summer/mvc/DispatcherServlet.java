package cn.leetechweb.summer.mvc;

import cn.leetechweb.summer.mvc.context.RequestAttributes;
import cn.leetechweb.summer.mvc.context.RequestContextHolder;
import cn.leetechweb.summer.mvc.mapping.ServletDescriptor;
import cn.leetechweb.summer.mvc.mapping.argument.ArgumentFactory;
import cn.leetechweb.summer.mvc.mapping.argument.ArgumentMapper;
import cn.leetechweb.summer.mvc.support.HttpMethod;
import cn.leetechweb.summer.mvc.support.method.result.MethodInvokeResult;
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

        RequestContextHolder.setRequestAttrHolder(new RequestAttributes(request, response));

        String mappingUrl = request.getServletPath();

        ServletDescriptor descriptor = this.servletMapping.getMapping(mappingUrl,
                HttpMethod.methodOf(request.getMethod()));

        MethodInvokeResult invokeResult = methodInvoker.doInvoke(descriptor, argumentMapper);

        processResult(request, response, invokeResult);
    }

    private void processResult(HttpServletRequest request, HttpServletResponse response, MethodInvokeResult result) {
        // 设置HTTP状态码
        response.setStatus(result.getStatus().getValue());
        // 渲染结果视图
        if (result.getView() == null) {
            logger.warning("View空");
        }else {
            View view = result.getView();
            view.render(request, response);
        }
    }


}
