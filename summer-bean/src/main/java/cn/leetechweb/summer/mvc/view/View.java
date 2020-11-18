package cn.leetechweb.summer.mvc.view;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 方法的返回视图
 * 视图可以是JSP，也可以是基Restful形式的结果
 * Project Name: summer
 * Create Time: 2020/11/18 14:39
 *
 * @author junyu lee
 **/
public interface View {

    /**
     * 设置视图名字
     * @param name 视图名字
     */
    void setViewName(String name);

    /**
     * 向这个视图增加参数
     * @param name 参数名
     * @param val 参数值
     */
    void append(String name, Object val);

    /**
     * 从视图中获取对应的参数
     * @param name 参数名
     * @return 参数值
     */
    Object get(String name);

    /**
     * 执行渲染工作
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     */
    void render(HttpServletRequest request, HttpServletResponse response);

}
