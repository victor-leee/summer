package cn.leetechweb.summer.mvc.support;

/**
 * Project Name: summer
 * Create Time: 2020/11/17 0:02
 *
 * @author junyu lee
 **/
public interface EmbeddedServer {

    /**
     * 使用serverConfig运行内嵌服务器
     * @param serverConfig 服务配置
     */
    void run(ServerConfig serverConfig);

}
