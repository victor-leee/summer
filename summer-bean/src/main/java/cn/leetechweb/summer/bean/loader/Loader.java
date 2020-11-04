package cn.leetechweb.summer.bean.loader;

/**
 * 加载器接口，用于初始化上下文容器
 * 一个容器(Context)可能有多个加载器，用于加载不同类型的数据接口
 * Project Name: summer
 * Create Time: 2020/11/3 21:57
 *
 * @author junyu lee
 **/
public interface Loader {
    /**
     * 加载资源接口
     */
    void load();
}
