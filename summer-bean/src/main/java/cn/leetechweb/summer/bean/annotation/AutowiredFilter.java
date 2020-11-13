package cn.leetechweb.summer.bean.annotation;

import cn.leetechweb.summer.bean.Filter;

import java.lang.reflect.Field;

/**
 * Project Name: summer
 * Create Time: 2020/11/13 21:26
 *
 * @author junyu lee
 **/
public class AutowiredFilter implements Filter<Field> {
    @Override
    public boolean stay(Field data) {
        return data.isAnnotationPresent(Autowired.class);
    }
}
