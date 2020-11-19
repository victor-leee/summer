package cn.leetechweb.summer.mvc.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 表明该参数需要获取请求体的内容
 * Project Name: summer
 * Create Time: 2020/11/19 11:03
 *
 * @author junyu lee
 **/
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestBody {
}
