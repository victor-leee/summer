package cn.leetechweb.summer.bean.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Summer Bean注解，该注解应该只能在方法上使用
 * 表明该方法返回的对象应该交由summer bean管理
 * Project Name: summer
 * Create Time: 2020/11/14 20:56
 *
 * @author junyu lee
 **/
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Bean {

    /**
     * 返回该bean的名称
     * @return Bean名称
     */
    String name() default "";

}
