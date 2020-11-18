package cn.leetechweb.summer.mvc;

import cn.leetechweb.summer.mvc.support.MethodInvokeResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Project Name: summer
 * Create Time: 2020/11/18 13:02
 *
 * @author junyu lee
 **/
public interface View {

    /**
     * 如果要进行重定向/转发/或者渲染JSP视图，该方法会返回相应的值
     * @return 要渲染的视图名称
     */
    String getViewName();

    /**
     * 返回是否进行重定向
     * @return 是否重定向
     */
    boolean isRedirect();

    /**
     * 返回是否进行转发
     * @return 是否转发
     */
    boolean isForward();

    /**
     * 做最终的渲染工作
     * @param servletRequest HttpServletRequest
     * @param servletResponse HttpServletResponse
     * @param invokeResult 执行结果
     */
    void doRender(HttpServletRequest servletRequest, HttpServletResponse servletResponse, MethodInvokeResult invokeResult);

}
