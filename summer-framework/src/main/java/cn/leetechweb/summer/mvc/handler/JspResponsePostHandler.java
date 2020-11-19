package cn.leetechweb.summer.mvc.handler;

import cn.leetechweb.summer.mvc.annotation.Restful;
import cn.leetechweb.summer.mvc.mapping.ServletDescriptor;
import cn.leetechweb.summer.mvc.support.MethodInvokeResult;
import cn.leetechweb.summer.mvc.view.JspView;
import cn.leetechweb.summer.mvc.view.View;

import java.lang.reflect.Method;

/**
 * Project Name: summer
 * Create Time: 2020/11/19 16:54
 *
 * @author junyu lee
 **/
public class JspResponsePostHandler implements InvokeHandler {
    @Override
    public void postHandle(Object resultObject, MethodInvokeResult methodInvokeResult, ServletDescriptor descriptor) {
        if (resultObject instanceof String) {
            String jsp = (String) resultObject;
            Method method = descriptor.getMethod();
            if (!method.isAnnotationPresent(Restful.class)
                    && !method.getDeclaringClass().isAnnotationPresent(Restful.class)) {
                View jspView = new JspView();
                jspView.setViewName("/" + jsp);
                methodInvokeResult.setView(jspView);
            }
        }
    }
}
