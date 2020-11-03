package cn.leetechweb.summer.bean.loader.parser;

import org.w3c.dom.Node;

import java.util.Iterator;
import java.util.List;

/**
 * 处理Xml单个Node的parser注册表
 * parser接口：
 * @see XmlNodeParser
 * @param <T> 该节点处理注册表处理的结果类型
 *           一种结果类型对应一种parser注册表
 *           例如我们需要Bean的定义类(AbstractBeanDefinition)，那么必须将AbstractBeanDefinition的Xml节点处理器注册进来
 *           供注册表使用
 *
 * Project Name: summer
 * Create Time: 2020/11/3 20:53
 *
 * @author junyu lee
 **/
public final class XmlNodeParserRegistry<T> implements Iterable<XmlNodeParser<T>> {

    /**
     * Node的parser注册表
     */
    private final List<XmlNodeParser<T>> parserRegistry;

    /**
     * @param xmlNodeParser summer-*.xml的节点处理器
     * @param order 需要把这个节点处理器加在registry的哪个位置
     *              注意靠前的位置拥有较高的优先级处理
     */
    public void addRegistration(XmlNodeParser<T> xmlNodeParser, int order) {
        parserRegistry.add(order, xmlNodeParser);
    }

    /**
     * 移除order位置的处理器
     * @param order 位置下标
     */
    public void removeRegistration(int order) {
        this.parserRegistry.remove(order);
    }

    /**
     * @return 当前节点处理注册表的parser数量
     */
    public int length() {
        return this.parserRegistry.size();
    }

    /**
     * 使用Parsers处理Node节点
     * @param node Xml Node节点
     */
    public T parseNode(Node node) {
        for (XmlNodeParser<T> xmlNodeParser : this) {
            if (!xmlNodeParser.discard(node)) {
                if (xmlNodeParser.supports(node)) {
                    return xmlNodeParser.parse(node);
                }
            }else {
                break;
            }
        }
        return null;
    }

    public XmlNodeParserRegistry(List<XmlNodeParser<T>> parserRegistry) {
        this.parserRegistry = parserRegistry;
    }

    @Override
    public Iterator<XmlNodeParser<T>> iterator() {
        return new XmlNodeParserIter<>(this);
    }

    private static class XmlNodeParserIter<T> implements Iterator<XmlNodeParser<T>> {

        XmlNodeParserRegistry<T> registry;

        int curr = 0;

        @Override
        public boolean hasNext() {
            return curr < registry.length();
        }

        @Override
        public XmlNodeParser<T> next() {
            return registry.parserRegistry.get(curr++);
        }

        XmlNodeParserIter(XmlNodeParserRegistry<T> registry) {
            this.registry = registry;
        }

    }
}
