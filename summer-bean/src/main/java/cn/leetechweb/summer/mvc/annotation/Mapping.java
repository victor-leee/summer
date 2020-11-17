package cn.leetechweb.summer.mvc.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Project Name: summer
 * Create Time: 2020/11/17 17:25
 *
 * @author junyu lee
 **/
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Mapping {

    /**
     * 返回该类/方法的映射路径
     * @return 映射路径
     */
    String path();

}
