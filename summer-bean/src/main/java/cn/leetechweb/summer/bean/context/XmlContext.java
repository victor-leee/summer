package cn.leetechweb.summer.bean.context;


/**
 * 使用classpath的配置文件生成的容器上下文
 * Project Name: summer
 * Create Time: 2020/11/4 13:13
 *
 * @author junyu lee
 **/
public final class XmlContext extends Context {

    public XmlContext(String xmlResource) throws ClassNotFoundException {
        throw new UnsupportedOperationException();
//        // 定义domLoader加载资源
//        DomLoader domLoader = new ClassPathDocumentLoader();
//
//        // 初始化beanDefs的nodeParser
//        List<XmlNodeParser<AbstractBeanDefinition>> beanDefsParsers = initBeanDefsNodeParsers();
//        XmlNodeParserRegistry<AbstractBeanDefinition> parserRegistry = new XmlNodeParserRegistry<>(beanDefsParsers);
//
//        // 初始化由xml定义的beanDefs的loader
//        BeanDefinitionLoader beanDefsLoader = new ClassPathXmlBeanDefinitionLoader(domLoader,
//                xmlResource,
//                parserRegistry);
//        // 初始化XmlBeanFactory
//        BeanFactory xmlBeanFactory = new SimpleBeanFactory();
//        setBeanFactory(xmlBeanFactory);
//        // 初始化beanDefs的监听器，beanDefs初始化完毕后交由postHandler完成剩下的bean初始化工作
//        BeanDefinitionDependencyInjectionHandler postBeanInitializedListener = new BeanDefinitionDependencyInjectionHandler(
//                xmlBeanFactory, new ConstructorBeanCreatorImpl());
//        beanDefsLoader.addListener(postBeanInitializedListener);
//        loaderList.add(beanDefsLoader);
//
//        // 初始化所有的loader
//        initLoaders();
    }

}
