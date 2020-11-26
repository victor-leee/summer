package cn.leetechweb.summer.mvc.support;

import cn.leetechweb.summer.bean.ContainerAware;
import cn.leetechweb.summer.bean.factory.BeanFactory;
import cn.leetechweb.summer.mvc.Constant;
import cn.leetechweb.summer.mvc.DispatcherServlet;
import cn.leetechweb.summer.mvc.exception.WebServerStartException;
import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;
import org.apache.jasper.runtime.JspFactoryImpl;
import org.apache.jasper.servlet.JspServlet;

import javax.servlet.jsp.JspFactory;
import java.io.*;
import java.util.logging.Logger;

/**
 * Project Name: summer
 * Create Time: 2020/11/17 0:05
 *
 * @author junyu lee
 **/
public final class TomcatEmbedServerImpl implements EmbedServer, ContainerAware {

    private DispatcherServlet dispatcherServlet = null;

    private final Logger logger = Logger.getLogger(TomcatEmbedServerImpl.class.getName());

    @Override
    public void run(ServerConfig serverConfig) {
        Tomcat embeddedTomcat = new Tomcat();
        String docBase = new File("classes").getAbsolutePath();

        // 配置tomcat基础上下文
        Context tomcatContext = embeddedTomcat.addContext(serverConfig.getContext(), docBase);
        embeddedTomcat.setPort(serverConfig.getPort());


        // 配置DispatcherServlet
        embeddedTomcat.addServlet("", DispatcherServlet.SERVLET_NAME, dispatcherServlet);
        tomcatContext.addServletMappingDecoded("/", DispatcherServlet.SERVLET_NAME);

        // 配置jasper的JspServlet
        JspServlet jspServlet = new JspServlet();
        embeddedTomcat.addServlet("", Constant.JSP_SERVLET, jspServlet);
        tomcatContext.addServletMappingDecoded("*.jsp", Constant.JSP_SERVLET);

        JspFactory.setDefaultFactory(new JspFactoryImpl());

        // 启动tomcat
        try {
            embeddedTomcat.start();
            logger.info("内置tomcat启动成功");
        }catch (Exception e) {
            throw new WebServerStartException("tomcat启动发生错误，原因:{}", e.getMessage());
        }
        embeddedTomcat.getServer().await();
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) {
        this.dispatcherServlet = beanFactory.getBean(DispatcherServlet.SERVLET_NAME, DispatcherServlet.class);
    }

}
