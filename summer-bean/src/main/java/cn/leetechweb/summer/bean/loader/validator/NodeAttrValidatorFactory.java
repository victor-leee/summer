package cn.leetechweb.summer.bean.loader.validator;

import cn.leetechweb.summer.bean.loader.validator.impl.BeanAttrValidator;
import cn.leetechweb.summer.bean.loader.validator.impl.BeansAttrValidator;
import cn.leetechweb.summer.bean.loader.validator.impl.ConstructorAttrValidator;
import cn.leetechweb.summer.bean.loader.validator.impl.ConstructorParamValidator;
import cn.leetechweb.summer.bean.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

import static cn.leetechweb.summer.bean.loader.XmlNodeDefinition.*;


/**
 * 节点验证工厂类
 * Project Name: summer
 * Create Time: 2020/11/3 18:50
 *
 * @author junyu lee
 **/
public final class NodeAttrValidatorFactory {

    private static final Map<String, NodeAttrValidator> NODE_ATTR_VALIDATOR_MAP;

    /**
     * 该类无需实例化
     */
    private NodeAttrValidatorFactory() {

    }

    public static NodeAttrValidator getValidator(String nodeKey) {
        NodeAttrValidator validator = NODE_ATTR_VALIDATOR_MAP.get(nodeKey);
        if (validator == null) {
            throw new IllegalArgumentException(StringUtils.format("没有nodeKey为{}的NodeAttr验证器",
                    false, nodeKey));
        }
        return validator;
    }


    static {
        NODE_ATTR_VALIDATOR_MAP = new HashMap<>(16);
        // 根节点校验
        NODE_ATTR_VALIDATOR_MAP.put(BEANS, new BeansAttrValidator());
        // bean节点校验
        NODE_ATTR_VALIDATOR_MAP.put(SIMPLE_BEAN_DEF, new BeanAttrValidator());
        // constructor节点校验
        NODE_ATTR_VALIDATOR_MAP.put(CONSTRUCTOR_OF_BEAN, new ConstructorAttrValidator());
        // constructor param节点校验
        NODE_ATTR_VALIDATOR_MAP.put(PARAM, new ConstructorParamValidator());
    }
}
