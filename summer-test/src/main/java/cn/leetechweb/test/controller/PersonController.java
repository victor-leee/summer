package cn.leetechweb.test.controller;

import cn.leetechweb.summer.bean.annotation.Autowired;
import cn.leetechweb.summer.mvc.annotation.Controller;
import cn.leetechweb.summer.mvc.annotation.Mapping;
import cn.leetechweb.summer.mvc.annotation.RequestParam;
import cn.leetechweb.summer.mvc.annotation.Restful;
import cn.leetechweb.summer.mvc.multipart.EasyFile;
import cn.leetechweb.summer.mvc.support.HttpMethod;
import cn.leetechweb.summer.mvc.view.InternalView;
import cn.leetechweb.summer.mvc.view.View;
import cn.leetechweb.test.dao.PersonDao;
import cn.leetechweb.test.model.Person;

import java.io.File;
import java.util.List;


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

    File base = new File("D:\\");

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

    @Restful
    @Mapping(path = "/upload", method = HttpMethod.POST)
    public String uploadFile(@RequestParam("file") List<EasyFile> fileList) {
        for (EasyFile easyFile : fileList) {
            System.out.println("文件名:" + easyFile.getFileName());
            try {
                easyFile.store(new File(base, easyFile.getFileName()));
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "上传成功";
    }

    @Restful
    @Mapping(path = "/download")
    public File downloadFile(@RequestParam("name") String fileName) {
        return new File(base, fileName);
    }

    @Restful
    @Mapping(path = "/rest")
    public Person getPerson(@RequestParam("name") String name) {
        return personDao.find(name);
    }

}
