package cn.leetechweb.summer.bean.util;

import java.io.InputStream;

/**
 * 文件工具类
 * Project Name: summer
 * Create Time: 2020/11/3 17:50
 *
 * @author junyu lee
 **/
public final class FileUtils {

    /**
     * @param resourceName 资源名称
     * @return 资源输入流
     */
    public static InputStream getInputStreamFromClassPath(String resourceName) {
        return Thread.currentThread().getContextClassLoader().getResourceAsStream(resourceName);
    }
}
