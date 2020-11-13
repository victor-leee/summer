package cn.leetechweb.summer.bean.context;

import cn.leetechweb.summer.bean.FieldBeanCreator;
import cn.leetechweb.summer.bean.annotation.reader.impl.SimpleBasePackageReader;
import cn.leetechweb.summer.bean.factory.BeanFactory;
import cn.leetechweb.summer.bean.factory.impl.SimpleBeanFactory;
import cn.leetechweb.summer.bean.handler.BeanDefinitionPostHandler;
import cn.leetechweb.summer.bean.loader.AnnotationConfigBeanDefinitionLoader;
import cn.leetechweb.summer.bean.loader.BeanDefinitionLoader;

/**
 * 使用注解配置的Summer 应用容器上下文
 * Project Name: summer
 * Create Time: 2020/11/4 23:42
 *
 * @author junyu lee
 **/
public final class AnnotationConfigContext extends Context {

    public AnnotationConfigContext(Class<?> baseClass) throws ClassNotFoundException {
        BeanDefinitionLoader beanDefinitionLoader = new AnnotationConfigBeanDefinitionLoader(
            baseClass, new SimpleBasePackageReader()
        );
        BeanFactory beanFactory = new SimpleBeanFactory();
        setBeanFactory(beanFactory);
        BeanDefinitionPostHandler postHandler = new BeanDefinitionPostHandler(
                beanFactory, new FieldBeanCreator()
        );
        beanDefinitionLoader.addListener(postHandler);
        loaderList.add(beanDefinitionLoader);

        initLoaders();
    }

}
