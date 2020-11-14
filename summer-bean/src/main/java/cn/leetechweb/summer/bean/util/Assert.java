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

    public static void isNotNull(Object o, String message, Object... args) {
        message = StringUtils.format(message, false, args);
        if (o == null) {
            throw new NullPointerException(message);
        }
    }

    public static void allNotNull(String message, Object... o) {
        if (o == null) {
            throw new NullPointerException(message);
        }
        for (Object each : o) {
            if (each == null) {
                throw new NullPointerException(message);
            }
        }
    }

}
