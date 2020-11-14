package cn.leetechweb.summer.bean.util;

/**
 * Project Name: summer
 * Create Time: 2020/11/13 22:44
 *
 * @author junyu lee
 **/
public abstract class Assert {

    public static void isNotNull(Object o) {
        isNotNull(o, "该参数不能为null");
    }

    public static void isNotNull(Object o, String message) {
        if (o == null) {
            throw new NullPointerException(message);
        }
    }

}
