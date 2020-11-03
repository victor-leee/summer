package cn.leetechweb.summer.bean.loader.validator.impl;

import cn.leetechweb.summer.bean.loader.XmlNodeDefinition;
import cn.leetechweb.summer.bean.loader.validator.NodeAttrValidator;
import cn.leetechweb.summer.bean.util.StringUtils;
import org.w3c.dom.Node;

/**
 * Project Name: summer
 * Create Time: 2020/11/3 18:57
 *
 * @author junyu lee
 **/
public class BeansAttrValidator implements NodeAttrValidator {
    @Override
    public boolean validate(Node node) {
        if (node.getAttributes().getLength() > 0) {
            return false;
        }
        return StringUtils.equals(node.getNodeName(), XmlNodeDefinition.BEANS);
    }
}
