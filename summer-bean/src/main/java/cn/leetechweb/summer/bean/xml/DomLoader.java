package cn.leetechweb.summer.bean.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.InputStream;

/**
 * 加载xml供后续解析
 * Project Name: summer
 * Create Time: 2020/11/3 17:47
 *
 * @author junyu lee
 **/
public abstract class DomLoader {

    protected static DocumentBuilder DOCUMENT_BUILDER = null;

    static {
        try {
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            DOCUMENT_BUILDER = builderFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    /**
     * 从documentIs中获取Document对象
     * @param documentIs Xml文档流
     * @return Document对象
     */
    abstract Document loadDocument(InputStream documentIs);

    /**
     * 从类路径下加载resourceName
     * @param resourceName 资源名
     * @return Document对象
     */
    abstract Document loadDocument(String resourceName);

    /**
     * 根据资源名返回文档根节点
     * @param resourceName 资源名
     * @return 返回文档的根节点
     */
    public Element getRootElement(String resourceName) {
        Document document = loadDocument(resourceName);
        return document.getDocumentElement();
    }

    /**
     * 从输入流中返回xml根节点
     * @param in 资源输入流
     * @return 文档根节点
     */
    public Element getRootElement(InputStream in) {
        Document document = loadDocument(in);
        return document.getDocumentElement();
    }

}
