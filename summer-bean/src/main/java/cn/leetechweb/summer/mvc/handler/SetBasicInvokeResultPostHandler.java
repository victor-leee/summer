package cn.leetechweb.summer.mvc.handler;

import cn.leetechweb.summer.mvc.Constant;
import cn.leetechweb.summer.mvc.support.MethodInvokeResult;

/**
 * 将一些基本信息设置到methodInvokeResult上
 * Project Name: summer
 * Create Time: 2020/11/18 0:36
 *
 * @author junyu lee
 **/
public class SetBasicInvokeResultPostHandler implements InvokeHandler {

    @Override
    public void postHandle(Object resultObject, MethodInvokeResult methodInvokeResult) {
        methodInvokeResult.setResultObject(resultObject);
        if (resultObject instanceof String) {
            String desc = (String) resultObject;
            if (desc.startsWith(Constant.FORWARD_RETURN_PREFIX)) {
                methodInvokeResult.setForward(true);
                methodInvokeResult.setTargetAddress(desc.substring(Constant.FORWARD_RETURN_PREFIX.length()));
            }
            if (desc.startsWith(Constant.REDIRECT_RETURN_PREFIX)) {
                methodInvokeResult.setRedirect(true);
                methodInvokeResult.setTargetAddress(desc.substring(Constant.REDIRECT_RETURN_PREFIX.length()));
            }
        }else {
            methodInvokeResult.setResultObject(resultObject);
        }
    }

}
