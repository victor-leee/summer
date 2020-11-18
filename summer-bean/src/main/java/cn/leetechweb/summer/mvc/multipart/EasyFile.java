package cn.leetechweb.summer.mvc.multipart;

import java.io.File;

/**
 * 文件接口
 * Project Name: summer
 * Create Time: 2020/11/19 0:25
 *
 * @author junyu lee
 **/
public interface EasyFile {

    /**
     * 返回上传该文件时用的名字
     * @return 该文件在表单中的名字
     */
    String getFormName();

    /**
     * 获取文件的实际名字
     * @return 文件名字
     */
    String getFileName();

    /**
     * 获取文件大小
     * @return 文件大小
     */
    long size();

    /**
     * 将EasyFile的实体存储到dest中
     * @param dest 本地文件
     */
    void store(File dest) throws Exception;

}
