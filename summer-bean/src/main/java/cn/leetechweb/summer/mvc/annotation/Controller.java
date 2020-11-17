package cn.leetechweb.summer.mvc.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 表明该类是一个servlet映射类
 * Project Name: summer
 * Create Time: 2020/11/17 17:24
 *
 * @author junyu lee
 **/
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Controller {
}
