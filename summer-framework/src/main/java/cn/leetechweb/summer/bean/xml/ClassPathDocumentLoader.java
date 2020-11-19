package cn.leetechweb.summer.bean.xml;

import cn.leetechweb.summer.bean.util.FileUtils;
import org.w3c.dom.Document;

import java.io.InputStream;

/**
 * 类路径Xml加载器，从classpath根目录下加载Xml文件
 * Project Name: summer
 * Create Time: 2020/11/3 17:49
 *
 * @author junyu lee
 **/
public class ClassPathDocumentLoader extends DomLoader {

    @Override
    public Document loadDocument(InputStream documentIs) {
        try {
            return DOCUMENT_BUILDER.parse(documentIs);
        }catch (Exception e) {
            throw new RuntimeException("资源处理失败");
        }
    }

    @Override
    public Document loadDocument(String resourceName) {
        try {
            return loadDocument(FileUtils.getInputStreamFromClassPath(resourceName));
        }catch (Exception e) {
            throw new RuntimeException("资源处理失败");
        }
    }

}
