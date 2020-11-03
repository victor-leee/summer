package cn.leetechweb.summer.bean.loader.parser.impl;

import cn.leetechweb.summer.bean.definition.AbstractBeanDefinition;
import cn.leetechweb.summer.bean.loader.parser.XmlNodeParser;
import cn.leetechweb.summer.bean.util.StringUtils;
import org.w3c.dom.Node;

import static cn.leetechweb.summer.bean.loader.XmlNodeDefinition.*;

/**
 * 处理文本节点，现在暂时不需要这个
 * Project Name: summer
 * Create Time: 2020/11/3 20:29
 *
 * @author junyu lee
 **/
public final class TextNodeParser implements XmlNodeParser<AbstractBeanDefinition> {
    @Override
    public boolean supports(Node node) {
        return false;
    }

    @Override
    public AbstractBeanDefinition parse(Node node) {
        return null;
    }

    @Override
    public boolean discard(Node node) {
        return StringUtils.equals(TEXT_NODE, node.getNodeName());
    }
}
