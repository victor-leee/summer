package cn.leetechweb.summer.mvc.exception;

import cn.leetechweb.summer.bean.util.StringUtils;

/**
 * Project Name: summer
 * Create Time: 2020/11/17 16:55
 *
 * @author junyu lee
 **/
public class WebServerStartException extends RuntimeException {
    public WebServerStartException(String cause, Object... args) {
        super(StringUtils.format(cause, false, args));
    }
}
