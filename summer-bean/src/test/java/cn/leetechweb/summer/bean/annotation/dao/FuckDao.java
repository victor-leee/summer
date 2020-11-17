package cn.leetechweb.summer.bean.annotation.dao;

import cn.leetechweb.summer.bean.annotation.Bean;
import cn.leetechweb.summer.bean.annotation.Component;
import cn.leetechweb.summer.bean.annotation.Resource;
import cn.leetechweb.summer.bean.annotation.Value;

/**
 * Project Name: summer
 * Create Time: 2020/11/14 14:34
 *
 * @author junyu lee
 **/
@Component
public class FuckDao {

    @Value("123")
    private Integer fuck;

    @Override
    public String toString() {
        return "FuckDao{" +
                "fuck=" + fuck +
                '}';
    }


    @Bean(name = "inner")
    public InnerBean getInnerBean(@Resource(name = "Dao") BaseDao baseDao) {
        return new InnerBean(baseDao);
    }

    @Bean(name = "wtf")
    public WTFDao getWtfDao(InnerBean innerBean) {
        return new WTFDao(innerBean);
    }
}
