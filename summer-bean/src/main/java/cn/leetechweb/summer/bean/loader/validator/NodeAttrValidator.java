package cn.leetechweb.summer.bean.loader.validator;

import org.w3c.dom.Node;

/**
 * 节点属性验证器,如果有不合规的属性会抛出错误，指明哪里有问题
 * 例如:
 * <test good="1" bad="2"/>
 * 只允许good的出现，则抛出错误
 * 'bad="2" is not allowed in the node'
 * Project Name: summer
 * Create Time: 2020/11/3 18:50
 *
 * @author junyu lee
 **/
public interface NodeAttrValidator {
    /**
     * 验证节点属性是否合规
     * @param node DOM 节点
     * @return 该节点的属性是否合法
     */
    boolean validate(Node node);
}
