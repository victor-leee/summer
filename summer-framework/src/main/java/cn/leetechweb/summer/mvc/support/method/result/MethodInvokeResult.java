package cn.leetechweb.summer.mvc.support.method.result;

import cn.leetechweb.summer.mvc.support.HttpStatus;
import cn.leetechweb.summer.mvc.view.View;

/**
 * Project Name: summer
 * Create Time: 2020/11/22 15:28
 *
 * @author junyu lee
 **/
public interface MethodInvokeResult {

    /**
     * 该方法返回是否进行转发
     * @return 是否重定向
     */
    boolean isRedirect();

    /**
     * 返回执行该servlet的结果响应视图
     * @return 响应视图
     */
    View getView();

    /**
     * 返回执行该方法结果的HTTP状态码，默认200
     * @return http状态码
     */
    HttpStatus getStatus();

    /**
     * 设置HTTP响应状态码
     * @param httpStatus HTTP状态码
     */
    void setStatus(HttpStatus httpStatus);

}
