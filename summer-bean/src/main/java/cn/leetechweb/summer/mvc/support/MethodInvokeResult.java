package cn.leetechweb.summer.mvc.support;

/**
 * Project Name: summer
 * Create Time: 2020/11/17 22:04
 *
 * @author junyu lee
 **/
public final class MethodInvokeResult {

    /**
     * 是否重定向到其他的mapper
     */
    boolean isRedirect;

    /**
     * 是否forward到指定的mapper
     */
    boolean isForward;

    /**
     * 如果要重定向forward，这个就是要到达的目的地址
     */
    String mapperContextUrl;

}
