package cn.leetechweb.summer.mvc.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 请求参数注解，将前端参数标记到对应的参数字段上
 * Project Name: summer
 * Create Time: 2020/11/18 0:13
 *
 * @author junyu lee
 **/
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestParam {
    /**
     * 前端的请求参数
     * @return 请求参数
     */
    String value();
}
