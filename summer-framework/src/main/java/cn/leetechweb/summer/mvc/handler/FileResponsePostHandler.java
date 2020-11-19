package cn.leetechweb.summer.mvc.handler;

import cn.leetechweb.summer.mvc.mapping.ServletDescriptor;
import cn.leetechweb.summer.mvc.support.MethodInvokeResult;
import cn.leetechweb.summer.mvc.view.FileView;
import cn.leetechweb.summer.mvc.view.View;

import java.io.File;

/**
 * Project Name: summer
 * Create Time: 2020/11/19 1:48
 *
 * @author junyu lee
 **/
public class FileResponsePostHandler implements InvokeHandler {
    @Override
    public void postHandle(Object resultObject, MethodInvokeResult methodInvokeResult, ServletDescriptor descriptor) {
        if (resultObject instanceof File) {
            View fileView = new FileView();
            fileView.append(View.SINGLE_OBJECT_TAG, resultObject);
            methodInvokeResult.setView(fileView);
        }
    }
}
