package cn.leetechweb.summer.test;

import cn.leetechweb.summer.bean.context.AnnotationConfigContext;
import cn.leetechweb.summer.bean.context.Context;

/**
 * Project Name: summer
 * Create Time: 2020/11/17 15:21
 *
 * @author junyu lee
 **/
public class ContainerTest {
    public static void main(String[] args) throws ClassNotFoundException {
        Context context = new AnnotationConfigContext(Config.class);
        System.out.println(context.getBean("DispatcherServlet"));
    }
}
