package cn.leetechweb.summer.bean.exception;

import cn.leetechweb.summer.bean.util.StringUtils;

/**
 * Project Name: summer
 * Create Time: 2020/11/13 20:25
 *
 * @author junyu lee
 **/
public class AnnotationContainerInitializationException extends RuntimeException {
    public AnnotationContainerInitializationException(String cause) {
        super(StringUtils.format("容器初始化失败，失败原因：{}", false, cause));
    }
}
