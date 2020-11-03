package cn.leetechweb.summer.bean.loader.parser;

import org.w3c.dom.Node;

/**
 * 对summer-*.xml作解析的解析器
 * Project Name: summer
 * Create Time: 2020/11/3 18:10
 * @param <T> 将节点解析为哪一种对象
 * @author junyu lee
 **/
public interface XmlNodeParser<T> {

    /**
     * 当前Parser是否支持将node转换为T目标类
     * @param node DOM 节点
     * @return 是否支持将node转换为目标类
     */
    boolean supports(Node node);

    /**
     * 在确认能将node转换为clazz对象后作转换
     * @param node DOM 节点
     * @return 目标类
     */
    T parse(Node node);

    /**
     * 有一些特殊的node是否直接放弃处理
     * 例如：
     * <root>
     *     <bean>
     *         ...
     *     </bean>
     * </root>
     * 在 root 和 bean 节点中，还有一个#text节点，通过将这个text节点放在处理链之前，可以直接跳过对该节点的处理
     * @param node DOM 节点
     * @return 是否放弃处理该node节点
     */
    boolean discard(Node node);

}
