package cn.leetechweb.summer.context.annotation.dao;

import cn.leetechweb.summer.bean.annotation.Bean;
import cn.leetechweb.summer.bean.annotation.Component;

/**
 * Project Name: summer
 * Create Time: 2020/11/14 14:34
 *
 * @author junyu lee
 **/
@Component
public class FuckDao {

    private Integer fuck = 12;

    @Override
    public String toString() {
        return "FuckDao{" +
                "fuck=" + fuck +
                '}';
    }


    @Bean(name = "inner")
    public InnerBean getInnerBean() {
        return new InnerBean();
    }
}
