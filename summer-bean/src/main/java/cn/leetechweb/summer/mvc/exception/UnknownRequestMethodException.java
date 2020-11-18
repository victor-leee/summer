package cn.leetechweb.summer.mvc.exception;

import cn.leetechweb.summer.bean.util.StringUtils;

/**
 * Project Name: summer
 * Create Time: 2020/11/18 21:14
 *
 * @author junyu lee
 **/
public class UnknownRequestMethodException extends RuntimeException {
    public UnknownRequestMethodException(String cause, Object... args) {
        super(StringUtils.format(cause, false, args));
    }
}
