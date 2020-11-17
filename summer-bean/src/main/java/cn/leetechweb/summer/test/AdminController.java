package cn.leetechweb.summer.test;

import cn.leetechweb.summer.mvc.annotation.Controller;
import cn.leetechweb.summer.mvc.annotation.Mapping;
import cn.leetechweb.summer.mvc.annotation.RequestParam;

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
    public String fuckDao(@RequestParam("haha") String haha, @RequestParam("two") Integer two) {
        System.out.println(haha);
        System.out.println(two);
        return "redirect:/admin/here";
    }

    @Mapping(path = "here")
    public void process(@RequestParam("haha") String haha, @RequestParam("two") Integer two) {
        System.err.println("process收到了 " + haha);
        System.err.println("process收到了 " + two);
    }

}
