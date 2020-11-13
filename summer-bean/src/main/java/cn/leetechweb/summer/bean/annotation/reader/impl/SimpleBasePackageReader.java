package cn.leetechweb.summer.bean.annotation.reader.impl;

import cn.leetechweb.summer.bean.annotation.reader.Reader;
import cn.leetechweb.summer.bean.util.FileUtils;

import java.io.File;
import java.net.URL;
import java.util.Set;

/**
 * Project Name: summer
 * Create Time: 2020/11/13 20:29
 *
 * @author junyu lee
 **/
public class SimpleBasePackageReader implements Reader {

    private Set<Class<?>> classSet;

    @Override
    public void read(String basePackage) {
        // 获取classpath
        URL projectUrl = this.getClass().getResource("/");
        String classpath = projectUrl.getPath();
        String realBasePackage = classpath + File.separator + getFilePath(basePackage);
        FileUtils.scans(realBasePackage, classSet);
    }

    @Override
    public void setClassSet(Set<Class<?>> classSet) {
        this.classSet = classSet;
    }

    /**
     * 将类路径中的.替换为File.separator
     * @param classpath 类路径
     * @return 文件路径
     */
    private String getFilePath(String classpath) {
        return classpath.replaceAll("\\.", File.separator);
    }

}
