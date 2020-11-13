package cn.leetechweb.summer.bean.context;

import cn.leetechweb.summer.bean.ConstructorBeanCreator;
import cn.leetechweb.summer.bean.definition.AbstractBeanDefinition;
import cn.leetechweb.summer.bean.factory.BeanFactory;
import cn.leetechweb.summer.bean.factory.impl.SimpleBeanFactory;
import cn.leetechweb.summer.bean.loader.BeanDefinitionLoader;
import cn.leetechweb.summer.bean.handler.BeanDefinitionPostHandler;
import cn.leetechweb.summer.bean.loader.ClassPathXmlBeanDefinitionLoader;
import cn.leetechweb.summer.bean.loader.parser.XmlNodeParser;
import cn.leetechweb.summer.bean.loader.parser.XmlNodeParserRegistry;
import cn.leetechweb.summer.bean.loader.parser.impl.SimpleBeanWithConstructorNodeParser;
import cn.leetechweb.summer.bean.loader.parser.impl.TextNodeParser;
import cn.leetechweb.summer.bean.xml.ClassPathDocumentLoader;
import cn.leetechweb.summer.bean.xml.DomLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * 使用classpath的配置文件生成的容器上下文
 * Project Name: summer
 * Create Time: 2020/11/4 13:13
 *
 * @author junyu lee
 **/
public final class XmlContext extends Context {

    public XmlContext(String xmlResource) throws ClassNotFoundException {
        // 定义domLoader加载资源
        DomLoader domLoader = new ClassPathDocumentLoader();

        // 初始化beanDefs的nodeParser
        List<XmlNodeParser<AbstractBeanDefinition>> beanDefsParsers = initBeanDefsNodeParsers();
        XmlNodeParserRegistry<AbstractBeanDefinition> parserRegistry = new XmlNodeParserRegistry<>(beanDefsParsers);

        // 初始化由xml定义的beanDefs的loader
        BeanDefinitionLoader beanDefsLoader = new ClassPathXmlBeanDefinitionLoader(domLoader,
                xmlResource,
                parserRegistry);
        // 初始化XmlBeanFactory
        BeanFactory xmlBeanFactory = new SimpleBeanFactory();
        setBeanFactory(xmlBeanFactory);
        // 初始化beanDefs的监听器，beanDefs初始化完毕后交由postHandler完成剩下的bean初始化工作
        BeanDefinitionPostHandler postBeanInitializedListener = new BeanDefinitionPostHandler(
                xmlBeanFactory, new ConstructorBeanCreator());
        beanDefsLoader.addListener(postBeanInitializedListener);
        loaderList.add(beanDefsLoader);

        // 初始化所有的loader
        initLoaders();
    }

    /**
     * 返回一些默认的xml节点处理器，用于鉴别每一个xml标签是否可处理以及怎么处理
     * @return Xml节点处理器
     */
    private List<XmlNodeParser<AbstractBeanDefinition>> initBeanDefsNodeParsers() {
        List<XmlNodeParser<AbstractBeanDefinition>> result = new ArrayList<>();
        result.add(new TextNodeParser());
        result.add(new SimpleBeanWithConstructorNodeParser());
        return result;
    }

}
