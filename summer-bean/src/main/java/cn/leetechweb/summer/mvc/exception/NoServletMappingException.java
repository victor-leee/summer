package cn.leetechweb.summer.mvc.exception;

import cn.leetechweb.summer.bean.util.StringUtils;

/**
 * Project Name: summer
 * Create Time: 2020/11/17 21:27
 *
 * @author junyu lee
 **/
public class NoServletMappingException extends RuntimeException {
    public NoServletMappingException(String message, Object... args) {
        super(StringUtils.format(message, false, args));
    }
}
