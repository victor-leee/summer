package cn.leetechweb.summer.mvc.view;

import cn.leetechweb.summer.mvc.Constant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Project Name: summer
 * Create Time: 2020/11/22 15:43
 *
 * @author junyu lee
 **/
public class RedirectView extends AbstractView {

    private final String redirectUrl;

    @Override
    public void render(HttpServletRequest request, HttpServletResponse response) {
        response.setHeader(Constant.REDIRECT_RESPONSE_HEADER, this.redirectUrl);
    }

    public RedirectView(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }
}
