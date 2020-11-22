package cn.leetechweb.summer.mvc.support.method.result;

import cn.leetechweb.summer.mvc.view.InternalView;
import cn.leetechweb.summer.mvc.view.View;

/**
 * Project Name: summer
 * Create Time: 2020/11/22 16:05
 *
 * @author junyu lee
 **/
public class InternalResourceResult extends AbstractMethodInvokeResult {

    private final InternalView internalView;

    @Override
    public View getView() {
        return this.internalView;
    }

    public InternalResourceResult(InternalView internalView) {
        this.internalView = internalView;
        internalView.setViewName("/" + internalView.getViewName() + ".jsp");
    }
}
