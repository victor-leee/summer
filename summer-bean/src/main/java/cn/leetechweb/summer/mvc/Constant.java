package cn.leetechweb.summer.mvc;

/**
 * Project Name: summer
 * Create Time: 2020/11/17 17:27
 *
 * @author junyu lee
 **/
public abstract class Constant {
    /**
     * URL分隔符
     */
    public static final String URL_SEPARATOR = "/";

    /**
     * 重定向请求头
     */
    public static final String REDIRECT_RESPONSE_HEADER = "Location";

    /**
     * 重定向返回字符串前缀
     */
    public static final String REDIRECT_RETURN_PREFIX = "redirect:";

    /**
     * 转发返回字符串前缀
     */
    public static final String FORWARD_RETURN_PREFIX = "forward:";

    /**
     * JSP文件后缀
     * 如果一个servlet直接返回了xxx.jsp，则转发到响应的JSP页面进行渲染返回
     */
    public static final String JSP_SUFFIX = ".jsp";
}
