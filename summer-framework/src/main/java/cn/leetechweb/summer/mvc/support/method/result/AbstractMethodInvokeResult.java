package cn.leetechweb.summer.mvc.support.method.result;

import cn.leetechweb.summer.mvc.support.HttpStatus;

/**
 * Project Name: summer
 * Create Time: 2020/11/22 16:02
 *
 * @author junyu lee
 **/
public abstract class AbstractMethodInvokeResult implements MethodInvokeResult {

    protected HttpStatus httpStatus;

    @Override
    public HttpStatus getStatus() {
        return this.httpStatus;
    }

    @Override
    public void setStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }
}
