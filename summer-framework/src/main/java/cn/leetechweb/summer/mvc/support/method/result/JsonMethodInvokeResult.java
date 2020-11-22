package cn.leetechweb.summer.mvc.support.method.result;

import cn.leetechweb.summer.mvc.view.RestfulJsonView;
import cn.leetechweb.summer.mvc.view.View;

import java.util.Map;

/**
 * Project Name: summer
 * Create Time: 2020/11/22 15:49
 *
 * @author junyu lee
 **/
public class JsonMethodInvokeResult extends AbstractMethodInvokeResult {

    private final Object resultObject;

    @Override
    public View getView() {
        View restfulView = new RestfulJsonView();
        // 如果返回结果是Map类型，则取出所有的kv
        if (resultObject instanceof Map) {
            Map<String, Object> map = (Map<String, Object>) resultObject;
            map.forEach(restfulView::append);
        } else {
            // 返回参数是普通的类对象的话，就直接将该对象添加到view的参数表中
            restfulView.append(View.SINGLE_OBJECT_TAG, resultObject);
        }
        return restfulView;
    }

    public JsonMethodInvokeResult(Object resultObject) {
        this.resultObject = resultObject;
    }
}
