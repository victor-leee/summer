package cn.leetechweb.test;

import cn.leetechweb.summer.bean.annotation.Summer;
import cn.leetechweb.summer.bean.context.AnnotationConfigContext;
import cn.leetechweb.summer.bean.context.Context;


/**
 * Project Name: summer
 * Create Time: 2020/11/19 15:12
 *
 * @author junyu lee
 **/
@Summer("cn.leetechweb.test")
public class Entry {
    public static void main(String[] args) throws ClassNotFoundException {
        Context context = new AnnotationConfigContext(Entry.class);
    }
}
