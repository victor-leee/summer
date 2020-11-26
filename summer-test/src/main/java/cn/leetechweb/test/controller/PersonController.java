package cn.leetechweb.test.controller;

import cn.leetechweb.summer.bean.annotation.Autowired;
import cn.leetechweb.summer.mvc.annotation.Controller;
import cn.leetechweb.summer.mvc.annotation.Mapping;
import cn.leetechweb.summer.mvc.annotation.RequestParam;
import cn.leetechweb.summer.mvc.annotation.Restful;
import cn.leetechweb.summer.mvc.support.HttpMethod;
import cn.leetechweb.summer.mvc.view.InternalView;
import cn.leetechweb.summer.mvc.view.View;
import cn.leetechweb.test.dao.PersonDao;
import cn.leetechweb.test.model.Person;


/**
 * Project Name: summer
 * Create Time: 2020/11/26 10:34
 *
 * @author junyu lee
 **/
@Controller
@Mapping(path = "/person")
public class PersonController {

    @Autowired
    PersonDao personDao;

    @Restful
    @Mapping(method = HttpMethod.POST)
    public String insertPerson(@RequestParam("name") String name, @RequestParam("age") int age) {
        personDao.insert(new Person(age, name));
        return "插入成功";
    }

    @Mapping
    public View get(@RequestParam("name") String name) {
        View view = new InternalView();
        Person person = personDao.find(name);
        view.setViewName("index");
        view.append("person", person);
        return view;
    }

}
