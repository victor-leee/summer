package cn.leetechweb.summer.mvc.context;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Project Name: summer
 * Create Time: 2020/11/19 10:36
 *
 * @author junyu lee
 **/
public final class RequestAttributes {

    private final HttpServletRequest request;

    private final HttpServletResponse response;

    public RequestAttributes(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public HttpServletResponse getResponse() {
        return response;
    }
}
