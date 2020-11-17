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

    @Mapping
    public String fuckDao(String haha, Integer two) {
        System.out.println(haha);
        System.out.println(two);
        return "redirect:/admin/here";
    }

    @Mapping(path = "here")
    public void process(String haha, Integer two) {
        System.err.println(haha);
        System.err.println("收到了" + two);
    }

}
