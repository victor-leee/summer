package cn.leetechweb.summer.context;

import cn.leetechweb.summer.bean.TestBean;
import cn.leetechweb.summer.bean.context.AnnotationConfigContext;
import cn.leetechweb.summer.bean.context.Context;
import cn.leetechweb.summer.context.annotation.Service;

import java.lang.reflect.Field;

/**
 * Project Name: summer
 * Create Time: 2020/11/13 21:07
 *
 * @author junyu lee
 **/
public class AnnotationContainerTest {
    public static void main(String[] args) throws ClassNotFoundException {
        Context context = new AnnotationConfigContext(TestBBB.class);
        Service service = context.getBean("cn.leetechweb.summer.context.annotation.Service", Service.class);
        System.out.println(service);
    }
}
