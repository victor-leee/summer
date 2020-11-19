package cn.leetechweb.summer.bean.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Summer注解配置入口
 * Project Name: summer
 * Create Time: 2020/11/13 20:19
 *
 * @author junyu lee
 **/
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Summer {

    /**
     * 返回注解配置应该扫描的base packages
     * @return summer容器应该扫描的base packages
     */
    String[] value() default {};

}
