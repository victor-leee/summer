package cn.leetechweb.summer.mvc.context;

/**
 * Project Name: summer
 * Create Time: 2020/11/19 10:36
 *
 * @author junyu lee
 **/
public abstract class RequestContextHolder {

    private static final ThreadLocal<RequestAttributes> requestAttrHolder =
            new ThreadLocal<>();

    public static void setRequestAttrHolder(RequestAttributes attrHolder) {
        requestAttrHolder.remove();
        requestAttrHolder.set(attrHolder);
    }

    public static RequestAttributes getRequestAttrHolder() {
        return requestAttrHolder.get();
    }

}
