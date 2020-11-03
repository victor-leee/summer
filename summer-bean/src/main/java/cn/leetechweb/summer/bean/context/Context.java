package cn.leetechweb.summer.bean.context;

import cn.leetechweb.summer.bean.definition.AbstractBeanDefinition;
import cn.leetechweb.summer.bean.factory.BeanFactory;
import cn.leetechweb.summer.bean.factory.impl.XmlBeanFactory;
import cn.leetechweb.summer.bean.loader.BeanDefinitionLoader;
import cn.leetechweb.summer.bean.loader.BeanDefinitionPostHandler;
import cn.leetechweb.summer.bean.loader.Loader;
import cn.leetechweb.summer.bean.loader.parser.XmlNodeParser;
import cn.leetechweb.summer.bean.loader.parser.XmlNodeParserRegistry;
import cn.leetechweb.summer.bean.loader.parser.impl.SimpleBeanWithConstructorNodeParser;
import cn.leetechweb.summer.bean.loader.parser.impl.TextNodeParser;
import cn.leetechweb.summer.bean.xml.ClassPathDocumentLoader;
import cn.leetechweb.summer.bean.xml.DomLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Summer 应用容器上下文
 *
 * Project Name: summer
 * Create Time: 2020/11/3 22:01
 *
 * @author junyu lee
 **/
public class Context {

    private final List<Loader> loaderList = new ArrayList<>();

    private final List<BeanFactory> beanFactories = new ArrayList<>();

    public Context(String... resources) {
        // 共用一个domLoader加载xml配置文件
        DomLoader domLoader = new ClassPathDocumentLoader();

        // 初始化beanDefs的nodeParser
        List<XmlNodeParser<AbstractBeanDefinition>> beanDefsParsers = initBeanDefsNodeParsers();
        XmlNodeParserRegistry<AbstractBeanDefinition> registry = new XmlNodeParserRegistry<>(beanDefsParsers);

        // 初始化beanDefs的loader
        BeanDefinitionLoader beanDefsLoader = new BeanDefinitionLoader(registry, domLoader);
        // 初始化XmlBeanFactory
        BeanFactory xmlBeanFactory = new XmlBeanFactory();
        beanFactories.add(xmlBeanFactory);
        // 初始化beanDefs的监听器
        BeanDefinitionPostHandler beanDefsListener = new BeanDefinitionPostHandler(xmlBeanFactory);
        beanDefsLoader.addListener(beanDefsListener);
        loaderList.add(beanDefsLoader);

        // 初始化所有的loader
        initLoaders(resources);
    }

    public Object getBean(String beanName) {
        for (BeanFactory beanFactory : beanFactories) {
            if (beanFactory.hasBean(beanName)) {
                return beanFactory.getBean(beanName);
            }
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public <T> T getBean(String beanName, Class<T> clazz) {
        for (BeanFactory beanFactory : beanFactories) {
            if (beanFactory.hasBean(beanName)) {
                return beanFactory.getBean(beanName, clazz);
            }
        }
        return null;
    }

    private void initLoaders(String[] resources) {
        for (int i = 0; i < loaderList.size(); i++) {
            Loader loader = loaderList.get(i);
            String resourcePath = resources[i];
            loader.load(resourcePath);
            loader.parse();
        }
    }

    private List<XmlNodeParser<AbstractBeanDefinition>> initBeanDefsNodeParsers() {
        List<XmlNodeParser<AbstractBeanDefinition>> result = new ArrayList<>();
        result.add(new TextNodeParser());
        result.add(new SimpleBeanWithConstructorNodeParser());
        return result;
    }

}
