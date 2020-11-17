package cn.leetechweb.summer.mvc;

import cn.leetechweb.summer.bean.ContainerAware;
import cn.leetechweb.summer.bean.factory.BeanFactory;
import cn.leetechweb.summer.bean.loader.Loader;
import cn.leetechweb.summer.mvc.support.EmbeddedServer;
import cn.leetechweb.summer.mvc.support.ServerConfig;
import cn.leetechweb.summer.mvc.support.TomcatEmbeddedServerImpl;

/**
 * Project Name: summer
 * Create Time: 2020/11/17 15:38
 *
 * @author junyu lee
 **/
public class ServletLoader implements Loader, ContainerAware {

    private BeanFactory beanFactory;

    @Override
    public void load() throws ClassNotFoundException {
        this.loadHelper();
    }

    private void loadHelper() {
        ServerConfig serverConfig = this.beanFactory.getBean("ServerConfig", ServerConfig.class);
        EmbeddedServer embeddedServer = beanFactory.getBean("TomcatEmbeddedServerImpl", TomcatEmbeddedServerImpl.class);
        embeddedServer.run(serverConfig);
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }
}
