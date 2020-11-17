package cn.leetechweb.summer.mvc;

import cn.leetechweb.summer.bean.ContainerAware;
import cn.leetechweb.summer.bean.Listener;
import cn.leetechweb.summer.bean.factory.BeanFactory;
import cn.leetechweb.summer.mvc.mapping.ServletMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

/**
 * Project Name: summer
 * Create Time: 2020/11/17 0:20
 *
 * @author junyu lee
 **/
public abstract class SummerServletBean extends HttpServlet implements ContainerAware, Listener<ServletMapping> {

    protected Logger logger = Logger.getGlobal();

    /**
     * bean工厂
     * 由容器初始化后注入进来供summer-mvc进行初始化工作
     */
    protected BeanFactory beanFactory;

    /**
     * 映射表
     */
    protected ServletMapping servletMapping;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    /**
     * 由子类实现的dispatch
     * @param request http请求
     * @param response http响应
     */
    protected abstract void doInternalDispatch(HttpServletRequest request, HttpServletResponse response);


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doInternalDispatch(req, resp);
    }

    @Override
    protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doInternalDispatch(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doInternalDispatch(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doInternalDispatch(req, resp);
    }

    @Override
    protected void doHead(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doInternalDispatch(req, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doInternalDispatch(req, resp);
    }

    @Override
    protected void doTrace(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doInternalDispatch(req, resp);
    }

    @Override
    public void onEvent(ServletMapping data) {
        this.servletMapping = data;
    }
}
