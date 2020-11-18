package cn.leetechweb.summer.test;

import cn.leetechweb.summer.bean.util.StringUtils;
import cn.leetechweb.summer.mvc.annotation.Controller;
import cn.leetechweb.summer.mvc.annotation.Mapping;
import cn.leetechweb.summer.mvc.annotation.RequestParam;
import cn.leetechweb.summer.mvc.annotation.Restful;
import cn.leetechweb.summer.mvc.multipart.EasyFile;
import cn.leetechweb.summer.mvc.support.HttpMethod;

import java.util.List;

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

    @Mapping(path = "/upload", method = HttpMethod.POST)
    @Restful
    public Person upload(@RequestParam("file") List<EasyFile> fileList) {
        for (EasyFile easyFile : fileList) {
            System.out.println(StringUtils.format("文件名:{},form域:{}", false,
                    easyFile.getFileName(), easyFile.getFormName()));
        }
        return new Person("李峻宇", 19);
    }
}
