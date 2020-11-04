package cn.leetechweb.summer.bean.loader;

import cn.leetechweb.summer.bean.definition.AbstractBeanDefinition;
import cn.leetechweb.summer.bean.loader.parser.XmlNodeParserRegistry;
import cn.leetechweb.summer.bean.util.XmlUtils;
import cn.leetechweb.summer.bean.xml.DomLoader;
import org.w3c.dom.Node;

/**
 * Project Name: summer
 * Create Time: 2020/11/4 13:02
 *
 * @author junyu lee
 **/
public final class ClassPathXmlBeanDefinitionLoader extends BeanDefinitionLoader {

    /**
     * Bean定义类(AbstractBeanDefinition)的Xml节点处理注册表
     */
    private final XmlNodeParserRegistry<AbstractBeanDefinition> parserRegistry;

    /**
     * 用来获取根节点的loader，与resource配合使用即可
     */
    private final DomLoader domLoader;

    /**
     * xml文件名
     */
    private final String resource;


    private void loadHelper(Node element) {
        AbstractBeanDefinition beanDefinition = parserRegistry.parseNode(element);
        if (beanDefinition != null) {
            this.beanRegistry.addBeanDefinition(beanDefinition);
        }
        XmlUtils.asList(element.getChildNodes()).forEach(this::loadHelper);
    }

    @Override
    public void load() {
        Node beanDefRootElement = domLoader.getRootElement(resource);
        loadHelper(beanDefRootElement);
        publish(this.beanRegistry);
    }

    public ClassPathXmlBeanDefinitionLoader(DomLoader domLoader,
                                            String classPathXmlResource,
                                            XmlNodeParserRegistry<AbstractBeanDefinition> registry) {
        this.domLoader = domLoader;
        this.parserRegistry = registry;
        this.resource = classPathXmlResource;
    }
}
