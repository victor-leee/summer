package cn.leetechweb.summer.test;

import cn.leetechweb.summer.bean.annotation.Summer;
import cn.leetechweb.summer.bean.context.AnnotationConfigContext;
import cn.leetechweb.summer.bean.context.Context;

/**
 * Project Name: summer
 * Create Time: 2020/11/18 15:11
 *
 * @author junyu lee
 **/
@Summer("cn.leetechweb.summer.test")
public class Application {
    public static void main(String[] args) throws ClassNotFoundException {
        Context context = new AnnotationConfigContext(Application.class);
    }
}
