package cn.leetechweb.summer.bean.loader;

/**
 * summer-bean.xml节点定义类
 * Project Name: summer
 * Create Time: 2020/11/3 17:40
 *
 * @author junyu lee
 **/
public final class XmlNodeDefinition {
    /**
     * 根节点定义
     */
    public static final String BEANS = "beans";

    /**
     * 单个bean节点开始
     */
    public static final String SIMPLE_BEAN_DEF = "bean";

    /**
     * ID定义
     */
    public static final String ID = "id";

    /**
     * bean属于哪一个class
     */
    public static final String CLASS = "class";

    /**
     * bean构造器节点定义
     */
    public static final String CONSTRUCTOR_OF_BEAN = "constructor";

    /**
     * 参数定义
     */
    public static final String PARAM = "param";

    /**
     * 与VALUE配合使用
     */
    public static final String ORDER = "order";

    /**
     * 与ORDER配合使用
     */
    public static final String VALUE = "value";

    /**
     * 文本节点
     */
    public static final String TEXT_NODE = "#text";
}
