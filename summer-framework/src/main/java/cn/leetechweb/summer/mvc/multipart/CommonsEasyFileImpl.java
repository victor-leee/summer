package cn.leetechweb.summer.mvc.multipart;

import org.apache.commons.fileupload.FileItem;

import java.io.File;

/**
 * 使用apache commons fileUpload的文件上传具体实现
 * Project Name: summer
 * Create Time: 2020/11/19 0:29
 *
 * @author junyu lee
 **/
public class CommonsEasyFileImpl implements EasyFile {

    private final FileItem fileItem;

    @Override
    public String getFormName() {
        return fileItem.getFieldName();
    }

    @Override
    public String getFileName() {
        return fileItem.getName();
    }

    @Override
    public long size() {
        return fileItem.getSize();
    }

    @Override
    public void store(File dest) throws Exception {
        fileItem.write(dest);
    }

    public CommonsEasyFileImpl(FileItem fileItem) {
        this.fileItem = fileItem;
    }

}
