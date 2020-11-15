package cn.leetechweb.summer.bean.exception;

import cn.leetechweb.summer.bean.util.StringUtils;

/**
 * Project Name: summer
 * Create Time: 2020/11/15 12:44
 *
 * @author junyu lee
 **/
public class BeanNameAlreadyExistsException extends RuntimeException {
    public BeanNameAlreadyExistsException(String message, Object... args) {
        super(StringUtils.format(message, false, args));
    }
}
