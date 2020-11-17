package cn.leetechweb.summer.mvc.support;

/**
 * 服务器配置类
 * Project Name: summer
 * Create Time: 2020/11/17 0:03
 *
 * @author junyu lee
 **/
public final class ServerConfig {

    /**
     * 要运行的端口号
     */
    private int port = 8080;

    /**
     * 项目路径
     */
    private String context = "";

    public void setPort(int port) {
        this.port = port;
    }

    public int getPort() {
        return port;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }
}
