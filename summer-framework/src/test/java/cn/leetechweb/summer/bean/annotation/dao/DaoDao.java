package cn.leetechweb.summer.bean.annotation.dao;

import cn.leetechweb.summer.bean.annotation.Component;

/**
 * Project Name: summer
 * Create Time: 2020/11/15 12:09
 *
 * @author junyu lee
 **/
@Component(name = "daodao")
public class DaoDao implements BaseDao {

    Integer Hi = 123;

    @Override
    public String toString() {
        return "DaoDao{" +
                "Hi=" + Hi +
                '}';
    }
}
