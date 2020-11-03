package cn.leetechweb.summer.context;

import cn.leetechweb.summer.bean.TestBean;
import cn.leetechweb.summer.bean.context.Context;
import cn.leetechweb.summer.bean.xml.ClassPathDocumentLoader;
import cn.leetechweb.summer.bean.xml.DomLoader;
import org.w3c.dom.Node;

/**
 * Project Name: summer
 * Create Time: 2020/11/3 22:26
 *
 * @author junyu lee
 **/
public class ContextTest {
    public static void main(String[] args) {
        Context context = new Context("summer-bean.xml");
        TestBean testBean = context.getBean("first_bean", TestBean.class);
        System.out.println(testBean);
    }
}
