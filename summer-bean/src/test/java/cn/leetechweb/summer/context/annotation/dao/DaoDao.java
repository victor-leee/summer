package cn.leetechweb.summer.context.annotation.dao;

import cn.leetechweb.summer.bean.annotation.Component;

/**
 * Project Name: summer
 * Create Time: 2020/11/15 12:09
 *
 * @author junyu lee
 **/
@Component(name = "daodao")
public class DaoDao {

    Integer Hi = 123;

    @Override
    public String toString() {
        return "DaoDao{" +
                "Hi=" + Hi +
                '}';
    }
}
