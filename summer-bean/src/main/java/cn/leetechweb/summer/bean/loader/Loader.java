package cn.leetechweb.summer.bean.loader;

/**
 * 加载器接口，用于上下文加载容器
 * Project Name: summer
 * Create Time: 2020/11/3 21:57
 *
 * @author junyu lee
 **/
public interface Loader {
    /**
     * 从classpath下查找文件
     * @param resourceName 资源名
     */
    void load(String resourceName);

    /**
     * 加载完毕资源后处理该资源
     */
    void parse();
}
