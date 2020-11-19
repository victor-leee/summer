package cn.leetechweb.summer.mvc.exception;

import cn.leetechweb.summer.bean.util.StringUtils;

/**
 * Project Name: summer
 * Create Time: 2020/11/18 0:11
 *
 * @author junyu lee
 **/
public class MethodInvokeException extends IllegalArgumentException {
    public MethodInvokeException(String cause, Object... args) {
        super(StringUtils.format(cause, false, args));
    }
}
