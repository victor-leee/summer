package cn.leetechweb.summer.bean.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自动注入值注解
 * Project Name: summer
 * Create Time: 2020/11/14 15:32
 *
 * @author junyu lee
 **/
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Value {

    /**
     * 这个值会经过一次强转
     * @return 注入的值
     */
    String value() default "";

}
