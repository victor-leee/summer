package cn.leetechweb.summer.mvc.handler;

import cn.leetechweb.summer.mvc.annotation.Restful;
import cn.leetechweb.summer.mvc.mapping.ServletDescriptor;
import cn.leetechweb.summer.mvc.support.MethodInvokeResult;
import cn.leetechweb.summer.mvc.view.RestfulJsonView;
import cn.leetechweb.summer.mvc.view.View;

import java.io.File;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * Project Name: summer
 * Create Time: 2020/11/18 21:35
 *
 * @author junyu lee
 **/
public class RestfulResponsePostHandler implements InvokeHandler {

    @Override
    public void postHandle(Object resultObject, MethodInvokeResult methodInvokeResult, ServletDescriptor descriptor) {
        Method method = descriptor.getMethod();
        if (method.isAnnotationPresent(Restful.class) || method.getDeclaringClass().isAnnotationPresent(Restful.class)) {
            if (!(resultObject instanceof File)) {
                View restfulView = new RestfulJsonView();
                methodInvokeResult.setView(restfulView);
                // 如果返回结果是Map类型，则取出所有的kv
                if (resultObject instanceof Map) {
                    Map<String, Object> map = (Map<String, Object>) resultObject;
                    map.forEach(restfulView::append);
                } else {
                    // 返回参数是普通的类对象的话，就直接将该对象添加到view的参数表中
                    restfulView.append(View.SINGLE_OBJECT_TAG, resultObject);
                }
            }
        }
    }

}
