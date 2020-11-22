package cn.leetechweb.summer.mvc.handler;

import cn.leetechweb.summer.mvc.annotation.Status;
import cn.leetechweb.summer.mvc.mapping.ServletDescriptor;
import cn.leetechweb.summer.mvc.support.HttpStatus;
import cn.leetechweb.summer.mvc.support.method.result.MethodInvokeResult;

import java.lang.reflect.Method;

/**
 * Project Name: summer
 * Create Time: 2020/11/18 20:58
 *
 * @author junyu lee
 **/
public class HttpStatusPostHandler implements InvokeHandler {

    @Override
    public void postHandle(Object resultObject, MethodInvokeResult methodInvokeResult, ServletDescriptor descriptor) {
        Method servletMethod = descriptor.getMethod();
        HttpStatus responseStatus = HttpStatus.OK;
        if (servletMethod.isAnnotationPresent(Status.class)) {
            responseStatus = servletMethod.getAnnotation(Status.class).value();
        }
        if (methodInvokeResult.getStatus() == null) {
            methodInvokeResult.setStatus(responseStatus);
        }
    }

}
