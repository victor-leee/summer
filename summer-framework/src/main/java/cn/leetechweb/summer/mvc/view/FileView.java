package cn.leetechweb.summer.mvc.view;

import cn.leetechweb.summer.bean.util.StringUtils;
import cn.leetechweb.summer.mvc.Constant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * 处理文件下载view
 * Project Name: summer
 * Create Time: 2020/11/19 1:40
 *
 * @author junyu lee
 **/
public class FileView extends AbstractView {
    @Override
    public void render(HttpServletRequest request, HttpServletResponse response) {
        File file = getDefault(File.class);
        prepareDownload(response, file);
        try (InputStream is = new FileInputStream(file);
             OutputStream os = response.getOutputStream()){
            byte[] bytes = new byte[1024];
            int len;
            while ((len = is.read(bytes)) != -1) {
                os.write(bytes, 0, len);
            }
        }catch (Exception e) {
            logger.warning(StringUtils.format("下载{}发生错误", false, file.getName()));
        }
    }

    private void prepareDownload(HttpServletResponse response, File file) {
        String filename = file.getName();
        response.setHeader("content-disposition", "attachment;filename=" + filename);
        response.setHeader("content-type", Constant.MEDIA_TYPE_ALL);
    }
}
