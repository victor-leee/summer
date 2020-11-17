package cn.leetechweb.summer.bean.annotation.dao;

import cn.leetechweb.summer.bean.annotation.Component;

/**
 * Project Name: summer
 * Create Time: 2020/11/14 2:05
 *
 * @author junyu lee
 **/
@Component
public class Dao implements BaseDao{

    private String haha = "123";

//    /**
//     * 循环依赖检查
//     */
//    @Autowired
//    Service service;

    public Dao() {

    }

    @Override
    public String toString() {
        return "Dao{" +
                "haha='" + haha + '\'' +
                '}';
    }
}
