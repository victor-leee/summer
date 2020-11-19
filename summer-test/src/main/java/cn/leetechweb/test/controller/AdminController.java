package cn.leetechweb.test.controller;

import cn.leetechweb.summer.mvc.annotation.Controller;
import cn.leetechweb.summer.mvc.annotation.Mapping;
import cn.leetechweb.summer.mvc.annotation.Restful;

import javax.servlet.http.HttpServletRequest;

/**
 * Project Name: summer
 * Create Time: 2020/11/19 15:13
 *
 * @author junyu lee
 **/
@Controller
@Restful
@Mapping(path = "/admin")
public class AdminController {
    @Mapping
    public String getMapping(HttpServletRequest httpServletRequest) {
        return httpServletRequest.getMethod();
    }
}