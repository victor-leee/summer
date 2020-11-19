package cn.leetechweb.summer.mvc.support;

import cn.leetechweb.summer.bean.ContainerAware;
import cn.leetechweb.summer.bean.factory.BeanFactory;
import cn.leetechweb.summer.mvc.DispatcherServlet;
import cn.leetechweb.summer.mvc.exception.WebServerStartException;
import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;

import java.io.File;
import java.util.logging.Logger;

/**
 * Project Name: summer
 * Create Time: 2020/11/17 0:05
 *
 * @author junyu lee
 **/
public final class TomcatEmbeddedServerImpl implements EmbeddedServer, ContainerAware {

    private DispatcherServlet dispatcherServlet = null;

    private Logger logger = Logger.getLogger(TomcatEmbeddedServerImpl.class.getName());

    @Override
    public void run(ServerConfig serverConfig) {
        Tomcat embeddedTomcat = new Tomcat();
        String docBase = new File(".").getAbsolutePath();

        // 配置tomcat基础上下文
        Context tomcatContext = embeddedTomcat.addContext(serverConfig.getContext(), docBase);
        embeddedTomcat.setPort(serverConfig.getPort());

        // 配置DispatcherServlet
        embeddedTomcat.addServlet("", DispatcherServlet.SERVLET_NAME, dispatcherServlet);
        tomcatContext.addServletMappingDecoded("/", DispatcherServlet.SERVLET_NAME);

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
