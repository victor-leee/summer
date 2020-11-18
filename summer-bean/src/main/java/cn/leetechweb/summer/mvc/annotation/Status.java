package cn.leetechweb.summer.mvc.annotation;

import cn.leetechweb.summer.mvc.support.HttpStatus;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 设置响应的HTTP状态码
 * Project Name: summer
 * Create Time: 2020/11/18 20:57
 *
 * @author junyu lee
 **/
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Status {

    /**
     * @return HTTP状态码
     */
    HttpStatus value();

}
