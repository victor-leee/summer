package cn.leetechweb.summer.bean.exception;

import cn.leetechweb.summer.bean.util.StringUtils;

/**
 * Project Name: summer
 * Create Time: 2020/11/13 20:25
 *
 * @author junyu lee
 **/
public class AnnotationContainerInitializationException extends RuntimeException {
    public AnnotationContainerInitializationException(String cause, Object... args) {
        super(StringUtils.format(cause, false, args));
    }
}
