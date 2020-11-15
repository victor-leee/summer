package cn.leetechweb.summer.context.annotation.service;

import cn.leetechweb.summer.bean.annotation.Autowired;
import cn.leetechweb.summer.bean.annotation.Component;
import cn.leetechweb.summer.bean.annotation.Resource;
import cn.leetechweb.summer.context.annotation.dao.DaoDao;
import cn.leetechweb.summer.context.annotation.dao.FuckDao;

/**
 * Project Name: summer
 * Create Time: 2020/11/14 20:12
 *
 * @author junyu lee
 **/
@Component
public class SecondService {

    Service service;

    @Autowired
    FuckDao fuckDao;

    @Resource(name = "daodao")
    DaoDao daoDao;

    @Override
    public String toString() {
        return "SecondService{" +
                "service=" + service +
                ", fuckDao=" + fuckDao +
                ", daoDao=" + daoDao +
                '}';
    }

    public SecondService(Service service) {
        this.service = service;
    }
}
