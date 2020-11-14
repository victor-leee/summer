package cn.leetechweb.summer.bean.exception;

import cn.leetechweb.summer.bean.util.StringUtils;

/**
 * Project Name: summer
 * Create Time: 2020/11/14 20:37
 *
 * @author junyu lee
 **/
public class NoSuchBeanException extends RuntimeException {
    public NoSuchBeanException(String message, Object... args) {
        super(StringUtils.format(message, false, args));
    }
}
