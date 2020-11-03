package cn.leetechweb.summer.bean.loader;

import cn.leetechweb.summer.bean.Listener;
import cn.leetechweb.summer.bean.Publisher;
import cn.leetechweb.summer.bean.definition.AbstractBeanDefinition;
import cn.leetechweb.summer.bean.definition.BeanDefinitionRegistry;
import cn.leetechweb.summer.bean.loader.parser.XmlNodeParserRegistry;
import cn.leetechweb.summer.bean.util.XmlUtils;
import cn.leetechweb.summer.bean.xml.DomLoader;
import org.w3c.dom.Node;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * BeanDefinition的加载器
 * 使用parser/XmlNodeParserRegistry处理由DomLoader提供的Element并返回BeanDefinition
 * @see DomLoader
 * @see cn.leetechweb.summer.bean.loader.parser.XmlNodeParserRegistry
 * @see cn.leetechweb.summer.bean.definition.AbstractBeanDefinition
 *
 * Project Name: summer
 * Create Time: 2020/11/3 21:06
 *
 * @author junyu lee
 **/
public final class BeanDefinitionLoader implements Loader, Publisher<BeanDefinitionRegistry> {

    /**
     * Bean定义类(AbstractBeanDefinition)的Xml节点处理注册表
     */
    private final XmlNodeParserRegistry<AbstractBeanDefinition> parserRegistry;

    /**
     * 用来获取根节点的loader
     */
    private final DomLoader domLoader;

    /**
     * Bean Def的根节点
     * <beans>
     *     ...
     * </beans>
     */
    private Node beanDefRootElement;

    /**
     * beanDef注册表
     */
    private final BeanDefinitionRegistry beanRegistry = new BeanDefinitionRegistry();

    private final List<Listener<BeanDefinitionRegistry>> listeners = new ArrayList<>();

    public BeanDefinitionLoader(XmlNodeParserRegistry<AbstractBeanDefinition> parserRegistry,
                                DomLoader domLoader) {
        this.parserRegistry = parserRegistry;
        this.domLoader = domLoader;
    }

    @Override
    public void load(String resource) {
        InputStream xmlIs = Thread.currentThread().getContextClassLoader().getResourceAsStream(resource);
        this.beanDefRootElement = this.domLoader.getRootElement(xmlIs);
    }

    @Override
    public void parse() {
        parse0(beanDefRootElement);
        // 向监听处理器发送本次处理的结果
        this.publish(beanRegistry);
    }

    private void parse0(Node element) {
        AbstractBeanDefinition beanDefinition = parserRegistry.parseNode(element);
        if (beanDefinition != null) {
            this.beanRegistry.addBeanDefinition(beanDefinition);
        }
        XmlUtils.asList(element.getChildNodes()).forEach(this::parse0);
    }

    @Override
    public void publish(BeanDefinitionRegistry data) {
        this.listeners.forEach(listener -> listener.onEvent(data));
    }

    @Override
    public void addListener(Listener<BeanDefinitionRegistry> listener) {
        this.listeners.add(listener);
    }
}
