package cn.leetechweb.summer.test;

import cn.leetechweb.summer.mvc.annotation.Controller;
import cn.leetechweb.summer.mvc.annotation.Mapping;
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
    public View getView() {
        View view = new JspView();
        view.setViewName("index.jsp");
        return view;
    }
}
