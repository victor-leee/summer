package cn.leetechweb.summer.bean.loader.validator.impl;

import cn.leetechweb.summer.bean.loader.validator.NodeAttrValidator;
import cn.leetechweb.summer.bean.util.StringUtils;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import static cn.leetechweb.summer.bean.loader.XmlNodeDefinition.*;

/**
 * Project Name: summer
 * Create Time: 2020/11/3 20:22
 *
 * @author junyu lee
 **/
public class ConstructorParamValidator implements NodeAttrValidator {

    @Override
    public boolean validate(Node node) {
        if (!StringUtils.equals(node.getNodeName(), PARAM)) {
            return false;
        }
        NamedNodeMap attrMap = node.getAttributes();
        return attrMap.getNamedItem(ORDER) != null;
    }
}
