package cn.leetechweb.summer.mvc.handler;

import cn.leetechweb.summer.mvc.Constant;
import cn.leetechweb.summer.mvc.annotation.Controller;
import cn.leetechweb.summer.mvc.annotation.Restful;
import cn.leetechweb.summer.mvc.mapping.ServletDescriptor;
import cn.leetechweb.summer.mvc.support.MethodInvokeResult;

import java.lang.reflect.Method;

/**
 * 将一些servlet/jsp的处理结果设置到methodInvokeResult上
 * Project Name: summer
 * Create Time: 2020/11/18 0:36
 *
 * @author junyu lee
 **/
public class ServletResultPostHandler implements InvokeHandler {

    @Override
    public void postHandle(Object resultObject, MethodInvokeResult methodInvokeResult, ServletDescriptor descriptor) {
        if (resultObject instanceof String) {
            Method method = descriptor.getMethod();
            if (!method.isAnnotationPresent(Restful.class) && !method.getDeclaringClass().isAnnotationPresent(Restful.class)) {
                String desc = (String) resultObject;
                if (desc.startsWith(Constant.FORWARD_RETURN_PREFIX)) {
                    methodInvokeResult.setForward(true);
                    methodInvokeResult.setUrl(desc.substring(Constant.FORWARD_RETURN_PREFIX.length()));
                }
                if (desc.startsWith(Constant.REDIRECT_RETURN_PREFIX)) {
                    methodInvokeResult.setRedirect(true);
                    methodInvokeResult.setUrl(desc.substring(Constant.REDIRECT_RETURN_PREFIX.length()));
                }
            }
        }
    }

}
