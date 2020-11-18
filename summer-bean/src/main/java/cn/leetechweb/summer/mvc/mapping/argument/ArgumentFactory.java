package cn.leetechweb.summer.mvc.mapping.argument;

import cn.leetechweb.summer.bean.util.ClassUtils;
import cn.leetechweb.summer.bean.util.StringUtils;
import cn.leetechweb.summer.mvc.Constant;
import cn.leetechweb.summer.mvc.multipart.CommonsEasyFileImpl;
import cn.leetechweb.summer.mvc.multipart.EasyFile;
import cn.leetechweb.summer.mvc.support.HttpMethod;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Project Name: summer
 * Create Time: 2020/11/18 0:40
 *
 * @author junyu lee
 **/
public final class ArgumentFactory {

    private static final Logger logger = Logger.getLogger(ArgumentFactory.class.getName());

    private static final DiskFileItemFactory factory = new DiskFileItemFactory();

    private static final String UPLOAD_FILE_TEMP_DIR = "javax.servlet.context.tempdir";

    public static ArgumentMapper getArgumentMapper(HttpServletRequest servletRequest) {
        Map<String, String[]> parameterMap = servletRequest.getParameterMap();
        ArgumentMapper argumentMapper = new SimpleArgumentMapper();
        parameterMap.forEach((name, parameter) -> {
            for (String p : parameter) {
                argumentMapper.add(name, new ArgumentDescriptor(
                        ClassUtils.getClassName(String.class), p));
            }
        });

        processRequestBody(argumentMapper, servletRequest);

        processMultipart(argumentMapper, servletRequest);

        return argumentMapper;
    }

    private static void processRequestBody(ArgumentMapper argumentMapper, HttpServletRequest servletRequest) {
        // 如果请求不是GET，读取可能的请求体数据
        if (!HttpMethod.GET.equals(HttpMethod.methodOf(servletRequest.getMethod()))) {
            if (Constant.CONTENT_TYPE_JSON.equals(servletRequest.getContentType())) {
                try {
                    BufferedReader reader = servletRequest.getReader();
                    StringBuilder builder = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        builder.append(line);
                    }
                    argumentMapper.setRequestBody(builder.toString());
                } catch (Exception e) {
                    logger.warning(e.getMessage());
                }
            }
        }
    }

    private static void processMultipart(ArgumentMapper argumentMapper, HttpServletRequest request) {
        ServletFileUpload fileUpload = initFileUpload(request);
        List<FileItem> fileItems = null;
        try {
            fileItems = fileUpload.parseRequest(request);
        }catch (Exception e) {
            logger.warning(StringUtils.format("文件上传发生错误:{}", false, e.getMessage()));
        }
        if (fileItems == null) {
            return;
        }
        for (FileItem fileItem : fileItems) {
            if (!fileItem.isFormField()) {
                processFileField(fileItem, argumentMapper);
            }
        }
    }

    private static void processFileField(FileItem fileItem, ArgumentMapper argumentMapper) {
        EasyFile easyFile = new CommonsEasyFileImpl(fileItem);
        argumentMapper.add(easyFile.getFormName(), new ArgumentDescriptor
                (ClassUtils.getClassName(fileItem), easyFile));
    }

    private static ServletFileUpload initFileUpload(HttpServletRequest request) {
        ServletContext servletContext = request.getServletContext();
        File repo = (File) servletContext.getAttribute(UPLOAD_FILE_TEMP_DIR);
        factory.setRepository(repo);
        return new ServletFileUpload(factory);
    }

}
