package cn.leetechweb.summer.bean.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 表明这个类需要交给summer容器管理
 * Project Name: summer
 * Create Time: 2020/11/13 21:09
 *
 * @author junyu lee
 **/
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Component {

    /**
     * 返回这个bean的名称
     * @return 这个bean的名称
     */
    String name() default "";

}
