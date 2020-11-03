package cn.leetechweb.summer.bean.loader.parser.impl;

import cn.leetechweb.summer.bean.definition.AbstractBeanDefinition;
import cn.leetechweb.summer.bean.definition.impl.GlobalStaticBeanDefinitionImpl;
import cn.leetechweb.summer.bean.loader.parser.XmlNodeParser;
import cn.leetechweb.summer.bean.loader.validator.NodeAttrValidatorFactory;
import cn.leetechweb.summer.bean.util.StringUtils;
import cn.leetechweb.summer.bean.util.XmlUtils;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static cn.leetechweb.summer.bean.loader.XmlNodeDefinition.*;

/**
 * 处理通过构造器初始化bean的节点
 * 这个处理类会生成BeanRegistry也就是bean注册项
 * Project Name: summer
 * Create Time: 2020/11/3 18:19
 * 当前类支持处理的节点eg.
 * <bean>
 *     <constructor>
 *         <param name="hello" value="world"/>
 *     </constructor>
 * </bean>
 *
 * @author junyu lee
 **/
public final class SimpleBeanWithConstructorNodeParser implements XmlNodeParser<AbstractBeanDefinition> {
    @Override
    public boolean supports(Node node) {
        // 检查当前节点是否是<bean>节点
        if (!NodeAttrValidatorFactory.getValidator(SIMPLE_BEAN_DEF).validate(node)) {
            return false;
        }

        // 检查子节点是否有且仅有<constructor>节点
        Node constructorNode = XmlUtils.asList(node.getChildNodes())
                .stream().filter(childNode -> !StringUtils.equals(childNode.getNodeName(), TEXT_NODE))
                .filter(childNode -> StringUtils.equals(childNode.getNodeName(), CONSTRUCTOR_OF_BEAN))
                .findFirst().orElse(null);
        if (constructorNode != null) {
            if (!NodeAttrValidatorFactory.getValidator(CONSTRUCTOR_OF_BEAN).validate(constructorNode)) {
                return false;
            }
            // 现在只需要检查<constructor>下是否都是<param>参数即可
            List<Node> paramNodes = XmlUtils.asList(constructorNode.getChildNodes())
                    .stream().filter(paramNode -> StringUtils.equals(paramNode.getNodeName(), PARAM))
                    .collect(Collectors.toList());
            for (Node paramNode : paramNodes) {
                if (!NodeAttrValidatorFactory.getValidator(PARAM).validate(paramNode)) {
                    return false;
                }
            }
        }

        return true;

    }

    @Override
    public AbstractBeanDefinition parse(Node node) {
        // 获取该BeanDefinition的ID
        String beanDefId = node.getAttributes().getNamedItem(ID).getNodeValue();
        // 获取该BeanDefinition的类路径
        String beanClassPath = node.getAttributes().getNamedItem(CLASS).getNodeValue();
        // 获取该BeanDefinition的所有构造函数的参数列表
        Node constructorNode = XmlUtils.firstNonTextChildNode(node);
        List<Node> paramNodes = XmlUtils.asListExcludeTextNodes(constructorNode.getChildNodes());
        Map<String, Object> paramMap = new HashMap<>(16);
        for (Node paramNode : paramNodes) {
            NamedNodeMap attrMap = paramNode.getAttributes();
            String paramName = attrMap.getNamedItem(ORDER).getNodeValue();
            // TODO: 2020/11/3 这个可以是依赖，不一定是字符串参数
            String paramValue = attrMap.getNamedItem(VALUE).getNodeValue();
            paramMap.put(paramName, paramValue);
        }
        return new GlobalStaticBeanDefinitionImpl(beanDefId, beanClassPath, paramMap);
    }

    @Override
    public boolean discard(Node node) {
        return false;
    }

}
