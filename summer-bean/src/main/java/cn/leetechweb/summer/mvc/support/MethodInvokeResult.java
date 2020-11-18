package cn.leetechweb.summer.mvc.support;

import cn.leetechweb.summer.mvc.ModelAndView;

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

    ModelAndView modelAndView;

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

    public void setModelAndView(ModelAndView modelAndView) {
        this.modelAndView = modelAndView;
    }

    public ModelAndView getModelAndView() {
        return modelAndView;
    }
}
