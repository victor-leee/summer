package cn.leetechweb.summer.bean.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 按照JSR标准定义的资源注入类
 * 可以完成同类型不同beanName的资源注入
 * @see Autowired
 * Project Name: summer
 * Create Time: 2020/11/15 12:02
 *
 * @author junyu lee
 **/
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface Resource {

    /**
     * 需要注入的beanName
     * @return beanName
     */
    String name();

}
