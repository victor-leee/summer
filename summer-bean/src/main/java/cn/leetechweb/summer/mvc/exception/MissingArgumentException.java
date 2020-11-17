package cn.leetechweb.summer.mvc.exception;

import cn.leetechweb.summer.bean.util.StringUtils;

/**
 * Project Name: summer
 * Create Time: 2020/11/18 1:30
 *
 * @author junyu lee
 **/
public class MissingArgumentException extends IllegalArgumentException {
    public MissingArgumentException(String cause, Object... args) {
        super(StringUtils.format(cause, false, args));
    }
}
