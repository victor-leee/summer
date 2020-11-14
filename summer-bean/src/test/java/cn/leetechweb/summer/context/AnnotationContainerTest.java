package cn.leetechweb.summer.context;

import cn.leetechweb.summer.bean.context.AnnotationConfigContext;
import cn.leetechweb.summer.bean.context.Context;
import cn.leetechweb.summer.context.annotation.dao.FuckDao;
import cn.leetechweb.summer.context.annotation.service.SecondService;
import cn.leetechweb.summer.context.annotation.service.Service;

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
    }
}
