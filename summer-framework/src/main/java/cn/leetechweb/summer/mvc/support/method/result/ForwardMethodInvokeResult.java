package cn.leetechweb.summer.mvc.support.method.result;

import cn.leetechweb.summer.mvc.support.HttpStatus;
import cn.leetechweb.summer.mvc.view.InternalView;
import cn.leetechweb.summer.mvc.view.View;

/**
 * Project Name: summer
 * Create Time: 2020/11/22 15:35
 *
 * @author junyu lee
 **/
public class ForwardMethodInvokeResult extends AbstractMethodInvokeResult {

    private final String forwardUrl;

    @Override
    public View getView() {
        View forwardView = new InternalView();
        forwardView.setViewName(this.forwardUrl);
        return forwardView;
    }

    public ForwardMethodInvokeResult(String forwardUrl) {
        this.forwardUrl = forwardUrl;
    }
}
