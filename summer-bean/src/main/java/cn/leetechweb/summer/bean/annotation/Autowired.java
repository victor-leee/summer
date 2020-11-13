package cn.leetechweb.summer.bean.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 表明该字段应该使用summer容器的bean实现自动注入
 * Project Name: summer
 * Create Time: 2020/11/13 21:19
 *
 * @author junyu lee
 **/
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Autowired {
}
