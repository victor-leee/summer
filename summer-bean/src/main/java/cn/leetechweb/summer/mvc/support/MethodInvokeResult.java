package cn.leetechweb.summer.mvc.support;

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
     * 如果要重定向forward，这个就是要到达的目的地址
     */
    String targetAddress;

    /**
     * 方法的返回对象
     */
    Object resultObject;

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

    public String getTargetAddress() {
        return targetAddress;
    }

    public void setTargetAddress(String targetAddress) {
        this.targetAddress = targetAddress;
    }

    public Object getResultObject() {
        return resultObject;
    }

    public void setResultObject(Object resultObject) {
        this.resultObject = resultObject;
    }
}
