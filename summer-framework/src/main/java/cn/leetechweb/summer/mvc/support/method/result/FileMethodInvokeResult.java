package cn.leetechweb.summer.mvc.support.method.result;

import cn.leetechweb.summer.mvc.support.HttpStatus;
import cn.leetechweb.summer.mvc.view.FileView;
import cn.leetechweb.summer.mvc.view.View;

import java.io.File;

/**
 * Project Name: summer
 * Create Time: 2020/11/22 15:46
 *
 * @author junyu lee
 **/
public class FileMethodInvokeResult extends AbstractMethodInvokeResult {

    private final File file;

    @Override
    public View getView() {
        View fileView = new FileView();
        fileView.append(View.SINGLE_OBJECT_TAG, this.file);
        return fileView;
    }

    public FileMethodInvokeResult(File file) {
        this.file = file;
    }
}
