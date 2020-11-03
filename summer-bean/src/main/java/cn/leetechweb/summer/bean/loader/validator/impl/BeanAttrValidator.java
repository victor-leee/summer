package cn.leetechweb.summer.bean.loader.validator.impl;

import cn.leetechweb.summer.bean.loader.validator.NodeAttrValidator;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import java.util.HashSet;
import java.util.Set;

import static cn.leetechweb.summer.bean.loader.XmlNodeDefinition.*;

/**
 * Project Name: summer
 * Create Time: 2020/11/3 20:15
 *
 * @author junyu lee
 **/
public class BeanAttrValidator implements NodeAttrValidator {

    private static final int BEAN_ATTR_COUNT = 2;

    private static final Set<String> PARAM_SET;

    @Override
    public boolean validate(Node node) {
        NamedNodeMap beanAttrMap = node.getAttributes();
        if (beanAttrMap.getLength() == BEAN_ATTR_COUNT) {
            Set<String> foundParamName = new HashSet<>(16);
            for (int i = 0; i < beanAttrMap.getLength(); i++) {
                foundParamName.add(beanAttrMap.item(i).getNodeName());
            }
            if (foundParamName.size() != PARAM_SET.size()) {
                return false;
            }
            return PARAM_SET.containsAll(foundParamName);
        }
        return false;
    }

    static {
        PARAM_SET = new HashSet<>(16);
        PARAM_SET.add(ID);
        PARAM_SET.add(CLASS);
    }
}
