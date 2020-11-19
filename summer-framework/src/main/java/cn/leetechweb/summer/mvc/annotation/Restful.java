package cn.leetechweb.summer.mvc.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * RESTFUL注解，标识该方法/类是标准的restful风格，返回结果应该使用JSON
 * Project Name: summer
 * Create Time: 2020/11/18 21:36
 *
 * @author junyu lee
 **/
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Restful {
}
