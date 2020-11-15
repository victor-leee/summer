package cn.leetechweb.summer.context.annotation.dao;

import cn.leetechweb.summer.bean.annotation.Value;

/**
 * Project Name: summer
 * Create Time: 2020/11/14 21:38
 *
 * @author junyu lee
 **/
public class InnerBean {

    Float shit = 1234.5f;

    BaseDao baseDao;

    @Override
    public String toString() {
        return "InnerBean{" +
                "shit=" + shit +
                ", baseDao=" + baseDao +
                '}';
    }

    public InnerBean(BaseDao baseDao) {
        this.baseDao = baseDao;
    }
}
