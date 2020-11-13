package cn.leetechweb.summer.context.annotation;

import cn.leetechweb.summer.bean.annotation.Component;

/**
 * Project Name: summer
 * Create Time: 2020/11/14 2:05
 *
 * @author junyu lee
 **/
@Component
public class Dao {

    private String haha = "123";

    public Dao() {

    }

    @Override
    public String toString() {
        return "Dao{" +
                "haha='" + haha + '\'' +
                '}';
    }
}
