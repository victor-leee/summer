package cn.leetechweb.test.controller;

import cn.leetechweb.summer.mvc.annotation.Controller;
import cn.leetechweb.summer.mvc.annotation.Mapping;
import cn.leetechweb.summer.mvc.annotation.RequestBody;
import cn.leetechweb.summer.mvc.annotation.Restful;
import cn.leetechweb.summer.mvc.support.HttpMethod;
import cn.leetechweb.summer.mvc.view.InternalView;
import cn.leetechweb.summer.mvc.view.View;
import cn.leetechweb.test.Person;

import javax.servlet.http.HttpServletRequest;

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
    public View getJsp() {
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
}
