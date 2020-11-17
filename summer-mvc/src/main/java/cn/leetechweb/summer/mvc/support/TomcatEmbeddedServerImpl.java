package cn.leetechweb.summer.mvc.support;

import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;

/**
 * Project Name: summer
 * Create Time: 2020/11/17 0:05
 *
 * @author junyu lee
 **/
public final class TomcatEmbeddedServerImpl implements EmbeddedServer {
    @Override
    public void run(ServerConfig serverConfig) {
        Tomcat embeddedTomcat = new Tomcat();

        // 配置tomcat基础上下文
        Context tomcatContext = embeddedTomcat.addContext(serverConfig.getContext(), null);
        embeddedTomcat.setPort(serverConfig.getPort());

        // 配置DispatcherServlet

    }
}
