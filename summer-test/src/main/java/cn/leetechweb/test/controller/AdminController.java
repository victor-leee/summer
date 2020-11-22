package cn.leetechweb.test.controller;

import cn.leetechweb.summer.mvc.annotation.*;
import cn.leetechweb.summer.mvc.support.HttpMethod;
import cn.leetechweb.summer.mvc.view.InternalView;
import cn.leetechweb.summer.mvc.view.View;
import cn.leetechweb.test.Person;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

/**
 * Project Name: summer
 * Create Time: 2020/11/19 15:13
 *
 * @author junyu lee
 **/
@Controller
@Mapping(path = "/admin")
public class AdminController {
    @Mapping
    @Restful
    public String getMapping(HttpServletRequest httpServletRequest) {
        return httpServletRequest.getMethod();
    }

    @Mapping(path = "/jsp")
    public String getJsp() {
        return "forward:/admin/forward";
    }

    @Mapping(path = "/forward")
    public View forwardJsp() {
        View jspView = new InternalView();
        jspView.setViewName("index");
        jspView.append("name", "李峻宇");
        return jspView;
    }

    @Restful
    @Mapping(path = "/person", method = HttpMethod.POST)
    public Person same(@RequestBody Person person) {
        return person;
    }

    @Mapping(path = "/redirect")
    public String doRedirect(@RequestParam("url") String to) {
        return "redirect:" + to;
    }

    @Mapping(path = "/file")
    public File getMdFile() {
        return new File("D:\\Developer\\Projects\\summer\\README.md");
    }
}
