package cn.leetechweb.summer.context;

import cn.leetechweb.summer.bean.context.Context;
import cn.leetechweb.summer.bean.context.XmlContext;

/**
 * Project Name: summer
 * Create Time: 2020/11/3 22:26
 *
 * @author junyu lee
 **/
public class ContextTest {
    public static void main(String[] args) throws ClassNotFoundException {
        Context context = new XmlContext("summer-bean.xml");
        TestBean testBean = context.getBean("first_bean", TestBean.class);
        System.out.println(testBean);
        TestBean another = context.getBean("second_bean", TestBean.class);
        System.out.println(another);
    }
}
