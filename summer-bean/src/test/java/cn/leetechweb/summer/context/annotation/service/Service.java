package cn.leetechweb.summer.context.annotation.service;

import cn.leetechweb.summer.bean.annotation.Autowired;
import cn.leetechweb.summer.bean.annotation.Component;
import cn.leetechweb.summer.bean.annotation.Value;
import cn.leetechweb.summer.context.annotation.dao.Dao;
import cn.leetechweb.summer.context.annotation.dao.FuckDao;

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

    @Value(value = "1")
    private Integer b;

    @Value(value = "true")
    private Boolean isError;

    @Override
    public String toString() {
        return "Service{" +
                "dao=" + dao +
                ", fuckDao=" + fuckDao +
                ", b=" + b +
                ", isError=" + isError +
                '}';
    }

    @Autowired
    public void setFuckDao(FuckDao fuckDao) {
        this.fuckDao = fuckDao;
    }
}
