package cn.leetechweb.summer.mvc.handler;

import cn.leetechweb.summer.mvc.mapping.ServletDescriptor;
import cn.leetechweb.summer.mvc.support.method.result.MethodInvokeResult;

/**
 * 执行方法的切面接口
 * Project Name: summer
 * Create Time: 2020/11/18 0:27
 *
 * @author junyu lee
 **/
public interface InvokeHandler {

    /**
     * 前置aspect
     * @param args 参数数组
     * @param servletDescriptor 该servlet描述
     */
    default void preHandle(Object[] args, ServletDescriptor servletDescriptor) {

    }

    /**
     * 后置切面
     * @param resultObject 返回结果
     * @param methodInvokeResult 返回结果包装
     */
    default void postHandle(Object resultObject, MethodInvokeResult methodInvokeResult, ServletDescriptor descriptor) {

    }

}
