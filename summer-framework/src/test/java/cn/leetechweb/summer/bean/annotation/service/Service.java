package cn.leetechweb.summer.bean.annotation.service;

import cn.leetechweb.summer.bean.annotation.Autowired;
import cn.leetechweb.summer.bean.annotation.Component;
import cn.leetechweb.summer.bean.annotation.Value;
import cn.leetechweb.summer.bean.annotation.dao.Dao;
import cn.leetechweb.summer.bean.annotation.dao.FuckDao;

/**
 * Project Name: summer
 * Create Time: 2020/11/14 2:05
 *
 * @author junyu lee
 **/
@Component(name = "service")
public class Service {
    Dao dao;

    FuckDao fuckDao;

    @Autowired
    SecondService secondService;

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
    public void fuckfuckDao(FuckDao fuckDao, Dao dao) {
        this.fuckDao = fuckDao;
        this.dao = dao;
    }

    public SecondService getSecondService() {
        return secondService;
    }
}
