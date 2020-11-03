package cn.leetechweb.summer.bean.loader.validator.impl;

import cn.leetechweb.summer.bean.loader.validator.NodeAttrValidator;
import cn.leetechweb.summer.bean.util.StringUtils;
import cn.leetechweb.summer.bean.util.XmlUtils;
import org.w3c.dom.Node;

import java.util.List;
import java.util.stream.Collectors;

import static cn.leetechweb.summer.bean.loader.XmlNodeDefinition.*;

/**
 * Project Name: summer
 * Create Time: 2020/11/3 20:07
 *
 * @author junyu lee
 **/
public class ConstructorAttrValidator implements NodeAttrValidator {
    @Override
    public boolean validate(Node node) {
        if (node.getAttributes().getLength() > 0) {
            return false;
        }
        if (!StringUtils.equals(node.getNodeName(), CONSTRUCTOR_OF_BEAN)) {
            return false;
        }
        List<Node> paramsNode = XmlUtils.asList(node.getChildNodes())
                .stream().filter(child -> StringUtils.equals(child.getNodeName(), PARAM))
                .collect(Collectors.toList());
        return paramsNode.size() > 0;
    }
}
