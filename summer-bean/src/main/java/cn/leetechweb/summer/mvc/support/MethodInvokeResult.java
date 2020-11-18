package cn.leetechweb.summer.mvc.support;

import cn.leetechweb.summer.mvc.view.View;

/**
 * Project Name: summer
 * Create Time: 2020/11/17 22:04
 *
 * @author junyu lee
 **/
public final class MethodInvokeResult {

    /**
     * 是否重定向
     */
    boolean isRedirect;

    /**
     * 是否forward到指定的mapper
     */
    boolean isForward;

    /**
     * 重定向或者转发的路径
     */
    String url;

    /**
     * 返回的视图模型
     */
    View view;

    /**
     * 响应状态
     */
    HttpStatus httpStatus;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }

    public boolean isRedirect() {
        return isRedirect;
    }

    public void setRedirect(boolean redirect) {
        isRedirect = redirect;
    }

    public boolean isForward() {
        return isForward;
    }

    public void setForward(boolean forward) {
        isForward = forward;
    }


    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }
}
