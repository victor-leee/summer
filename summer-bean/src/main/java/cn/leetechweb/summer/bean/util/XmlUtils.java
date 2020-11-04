package cn.leetechweb.summer.bean.util;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static cn.leetechweb.summer.bean.loader.XmlNodeDefinition.*;

/**
 * Xml处理工具类
 * Project Name: summer
 * Create Time: 2020/11/3 18:32
 *
 * @author junyu lee
 **/
public abstract class XmlUtils {

    public static List<Node> asList(NodeList nodeList) {
        List<Node> result = new ArrayList<>();
        for (int i = 0; i < nodeList.getLength(); i++) {
            result.add(nodeList.item(i));
        }
        return result;
    }

    public static Node firstNonTextChildNode(Node node) {
        return asList(node.getChildNodes())
                .stream()
                .filter(childNode -> !StringUtils.equals(childNode.getNodeName(), TEXT_NODE))
                .findFirst().orElse(null);
    }

    public static List<Node> asListExcludeTextNodes(NodeList nodeList) {
        return asList(nodeList).stream()
                .filter(node -> !StringUtils.equals(node.getNodeName(), TEXT_NODE))
                .collect(Collectors.toList());
    }

}
