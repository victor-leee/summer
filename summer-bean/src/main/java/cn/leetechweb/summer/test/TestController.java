package cn.leetechweb.summer.test;

import cn.leetechweb.summer.mvc.annotation.Controller;
import cn.leetechweb.summer.mvc.annotation.Mapping;
import cn.leetechweb.summer.mvc.annotation.Restful;
import cn.leetechweb.summer.mvc.support.HttpMethod;
import cn.leetechweb.summer.mvc.view.JspView;
import cn.leetechweb.summer.mvc.view.View;

/**
 * Project Name: summer
 * Create Time: 2020/11/18 15:12
 *
 * @author junyu lee
 **/
@Controller
@Mapping(path = "/test")
public class TestController {
    @Mapping
    @Restful
    public Person getView() {
        return new Person("李峻宇", 19);
    }
}
