package cn.leetechweb.summer.bean.annotation.reader.impl;

import cn.leetechweb.summer.bean.annotation.reader.Reader;
import cn.leetechweb.summer.bean.util.FileUtils;

import java.io.File;
import java.util.Set;
import java.util.regex.Matcher;

/**
 * Project Name: summer
 * Create Time: 2020/11/13 20:29
 *
 * @author junyu lee
 **/
public class SimpleBasePackageReader implements Reader {

    private Set<Class<?>> classSet;

    @Override
    public void read(String basePackage) throws ClassNotFoundException {
        FileUtils.scans(basePackage, classSet);
    }

    @Override
    public void setClassSet(Set<Class<?>> classSet) {
        this.classSet = classSet;
    }

}
