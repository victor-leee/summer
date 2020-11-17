package cn.leetechweb.summer.test;

import cn.leetechweb.summer.mvc.annotation.Controller;
import cn.leetechweb.summer.mvc.annotation.Mapping;

/**
 * Project Name: summer
 * Create Time: 2020/11/17 18:16
 *
 * @author junyu lee
 **/
@Controller
@Mapping(path = "/admin")
public class AdminController {

    @Mapping(path = "fuckdao")
    public void fuckDao() {

    }

}
