package cn.leetechweb.summer.bean.util;

import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Set;
import java.util.regex.Matcher;

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
        doScans(new StringBuilder(basePackage), classSet);
    }

    private static void doScans(StringBuilder basePackage, Set<Class<?>> classSet) throws ClassNotFoundException {
        String directoryPath = getAbsoluteFilePath(basePackage.toString());
        File directoryFile = new File(directoryPath);
        File[] children = directoryFile.listFiles();
        if (children != null && children.length > 0) {
            for (File subFile : children) {
                if (subFile.isFile()) {
                    // 检查该文件是否是class文件
                    if (subFile.getName().endsWith(CLASS_SUFFIX)) {
                        String className = subFile.getName();
                        className = className.substring(0, className.length() - CLASS_SUFFIX.length());
                        Class<?> clazz = loadClass(basePackage.toString(), className);
                        if (clazz != null) {
                            classSet.add(clazz);
                        }
                    }
                }else {
                    basePackage.append(PACKAGE_SEPARATOR).append(subFile.getName());
                    doScans(basePackage, classSet);
                    basePackage.delete(basePackage.length() - (PACKAGE_SEPARATOR + subFile.getName()).length(),
                            basePackage.length());
                }
            }
        }
    }

    public static Class<?> loadClass(String basePackage, String className) throws ClassNotFoundException {
        return Thread.currentThread().getContextClassLoader().loadClass(basePackage + PACKAGE_SEPARATOR + className);
    }

    private static String getAbsoluteFilePath(String packageName) {
        String classpath = FileUtils.class.getResource("/").getPath();
        return classpath + getFilePath(packageName);
    }


    /**
     * 将类路径中的.替换为File.separator
     * @param classpath 类路径
     * @return 文件路径
     */
    private static String getFilePath(String classpath) {
        return classpath.replaceAll("\\.", Matcher.quoteReplacement(File.separator));
    }

}
