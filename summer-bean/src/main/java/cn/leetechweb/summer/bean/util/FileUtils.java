package cn.leetechweb.summer.bean.util;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Set;

import static cn.leetechweb.summer.bean.Constant.*;

/**
 * 文件工具类
 * Project Name: summer
 * Create Time: 2020/11/3 17:50
 *
 * @author junyu lee
 **/
public abstract class FileUtils {

    /**
     * @param resourceName 资源名称
     * @return 资源输入流
     */
    public static InputStream getInputStreamFromClassPath(String resourceName) {
        return Thread.currentThread().getContextClassLoader().getResourceAsStream(resourceName);
    }

    /**
     * 读取basePackage下的所有类信息，加到classSet当中
     * @param basePackage 基础包地址
     * @param classSet 类集合
     */
    public static void scans(String basePackage, Set<Class<?>> classSet) {
        File baseFile = new File(basePackage);
        File[] subFiles = baseFile.listFiles();
        if (subFiles != null && subFiles.length > 0) {
            for (File subFile : subFiles) {
                if (subFile.isFile()) {
                    // 检查该文件是否是java文件
                    if (subFile.getName().endsWith(JAVA_SUFFIX)) {
                        Class<?> clazz = loadClassFromUrl(subFile.getParent(), subFile.getName());
                        if (clazz != null) {
                            classSet.add(clazz);
                        }
                    }
                }else {
                    scans(basePackage + subFile.getName() + File.separator, classSet);
                }
            }
        }
    }

    /**
     * 从绝对路径上加载类文件
     * @param url 文件绝对路径
     * @return 该文件对应的类
     */
    public static Class<?> loadClassFromUrl(String url, String className) {
        try {
            URL classUrl = new URL(url);
            ClassLoader urlClassLoader = URLClassLoader.newInstance(new URL[]{classUrl});
            return urlClassLoader.loadClass(className);
        }catch (Exception e) {
            System.err.println("加载类文件出现错误");
        }
        return null;
    }

}
