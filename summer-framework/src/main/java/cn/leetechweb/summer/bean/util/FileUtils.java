package cn.leetechweb.summer.bean.util;


import java.io.InputStream;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.Set;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

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
    public static void scans(String basePackage, Set<Class<?>> classSet) throws ClassNotFoundException {
        try {
            JarFile jarFile = new JarFile(getJarLocation().getPath());
            Enumeration<JarEntry> entryEnumeration = jarFile.entries();
            URLClassLoader urlClassLoader = new URLClassLoader(new URL[]{getJarLocation()});
            while (entryEnumeration.hasMoreElements()) {
                JarEntry entry = entryEnumeration.nextElement();
                if (getEntryName(entry).startsWith(basePackage)) {
                    if (entry.getName().endsWith(CLASS_SUFFIX)) {
                        Class<?> clazz = urlClassLoader.loadClass(
                                getEntryName(entry)
                        );
                        classSet.add(clazz);
                    }
                }
            }
        }catch (Exception e) {
            throw new RuntimeException();
        }
    }

    private static URL getJarLocation() {
        return FileUtils.class.getProtectionDomain().getCodeSource().getLocation();
    }

    private static String getEntryName(JarEntry jarEntry) {
        return jarEntry.getName().replaceAll("/", "\\.")
                .replaceAll(".class", "");
    }

}
