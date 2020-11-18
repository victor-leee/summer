package cn.leetechweb.summer.mvc.handler;

import cn.leetechweb.summer.mvc.mapping.ServletDescriptor;
import cn.leetechweb.summer.mvc.support.MethodInvokeResult;
import cn.leetechweb.summer.mvc.view.View;

/**
 * Project Name: summer
 * Create Time: 2020/11/18 14:47
 *
 * @author junyu lee
 **/
public class ViewInvokeResultPostHandler implements InvokeHandler {
    @Override
    public void postHandle(Object resultObject, MethodInvokeResult methodInvokeResult, ServletDescriptor descriptor) {
        if (resultObject instanceof View) {
            methodInvokeResult.setView((View) resultObject);
        }
    }
}
