package cn.leetechweb.summer.context.annotation;

import cn.leetechweb.summer.bean.annotation.Autowired;
import cn.leetechweb.summer.bean.annotation.Component;

/**
 * Project Name: summer
 * Create Time: 2020/11/14 2:05
 *
 * @author junyu lee
 **/
@Component
public class Service {
    @Autowired
    Dao dao;

    FuckDao fuckDao;

    private Integer b = 1;

    @Override
    public String toString() {
        return "Service{" +
                "dao=" + dao +
                ", fuckDao=" + fuckDao +
                ", b=" + b +
                '}';
    }

    @Autowired
    public void setFuckDao(FuckDao fuckDao) {
        this.fuckDao = fuckDao;
    }
}
