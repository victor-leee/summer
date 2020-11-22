package cn.leetechweb.summer.mvc.support.method.result;

import cn.leetechweb.summer.mvc.support.HttpStatus;
import cn.leetechweb.summer.mvc.view.RedirectView;
import cn.leetechweb.summer.mvc.view.View;

/**
 * Project Name: summer
 * Create Time: 2020/11/22 15:43
 *
 * @author junyu lee
 **/
public class RedirectMethodInvokeResult extends AbstractMethodInvokeResult {

    private final String redirectUrl;

    @Override
    public View getView() {
        return new RedirectView(this.redirectUrl);
    }

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.REDIRECT;
    }

    public RedirectMethodInvokeResult(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }
}
