package cn.leetechweb.summer.context.annotation.service;

import cn.leetechweb.summer.bean.annotation.Autowired;
import cn.leetechweb.summer.bean.annotation.Component;
import cn.leetechweb.summer.bean.annotation.Resource;
import cn.leetechweb.summer.context.annotation.dao.BaseDao;
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


    FuckDao fuckDao;

    BaseDao baseDao;

    @Override
    public String toString() {
        return "SecondService{" +
                "fuckDao=" + fuckDao +
                ", baseDao=" + baseDao +
                '}';
    }

    public SecondService(Service service) {
        this.service = service;
    }

    @Autowired
    public void setFuckDao(FuckDao fuckDao, @Resource(name = "daodao") BaseDao baseDao) {
        this.fuckDao = fuckDao;
        this.baseDao = baseDao;
    }

    public Service getService() {
        return service;
    }
}
