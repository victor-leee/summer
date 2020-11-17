package cn.leetechweb.summer.bean;

import cn.leetechweb.summer.bean.context.AnnotationConfigContext;
import cn.leetechweb.summer.bean.context.Context;
import cn.leetechweb.summer.bean.annotation.dao.FuckDao;
import cn.leetechweb.summer.bean.annotation.dao.InnerBean;
import cn.leetechweb.summer.bean.annotation.dao.WTFDao;
import cn.leetechweb.summer.bean.annotation.service.SecondService;
import cn.leetechweb.summer.bean.annotation.service.Service;

/**
 * Project Name: summer
 * Create Time: 2020/11/13 21:07
 *
 * @author junyu lee
 **/
public class AnnotationContainerTest {
    public static void main(String[] args) throws ClassNotFoundException {
        Context context = new AnnotationConfigContext(TestBBB.class);
        Service service = context.getBean("service", Service.class);
        System.out.println(service);
        FuckDao fuckDao = context.getBean("FuckDao", FuckDao.class);
        System.out.println(fuckDao);
        SecondService secondService = context.getBean("SecondService", SecondService.class);
        System.out.println(secondService);
        InnerBean innerBean = context.getBean("inner", InnerBean.class);
        System.out.println(innerBean);
        WTFDao wtfDao = context.getBean("wtf", WTFDao.class);
        System.out.println(wtfDao);

        System.out.println(service.getSecondService() == secondService);
        System.out.println(secondService.getService() == service);
    }
}
