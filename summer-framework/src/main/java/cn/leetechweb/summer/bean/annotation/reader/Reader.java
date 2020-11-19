package cn.leetechweb.summer.bean.annotation.reader;

import java.util.Set;

/**
 * 注解容器扫描器
 * Project Name: summer
 * Create Time: 2020/11/13 20:27
 *
 * @author junyu lee
 **/
public interface Reader {

    /**
     * 扫描basePackage下的所有类
     * @param basePackage 扫描根目录
     */
    void read(String basePackage) throws ClassNotFoundException;

    /**
     * 读取后要存放的类集合
     * @param classSet 类集合
     */
    void setClassSet(Set<Class<?>> classSet);

}
