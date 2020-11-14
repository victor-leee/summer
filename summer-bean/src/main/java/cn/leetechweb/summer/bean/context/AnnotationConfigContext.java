package cn.leetechweb.summer.bean.context;

import cn.leetechweb.summer.bean.Listener;
import cn.leetechweb.summer.bean.creator.BeanCreator;
import cn.leetechweb.summer.bean.creator.impl.DefaultConstructorBeanCreatorImpl;
import cn.leetechweb.summer.bean.creator.impl.FieldBeanCreatorDecoratorImpl;
import cn.leetechweb.summer.bean.annotation.reader.impl.SimpleBasePackageReader;
import cn.leetechweb.summer.bean.creator.impl.SetterInjectionBeanCreatorDecoratorImpl;
import cn.leetechweb.summer.bean.definition.BeanDefinitionRegistry;
import cn.leetechweb.summer.bean.factory.BeanFactory;
import cn.leetechweb.summer.bean.factory.impl.SimpleBeanFactory;
import cn.leetechweb.summer.bean.handler.BeanDefinitionConstantInjectionPostHandler;
import cn.leetechweb.summer.bean.handler.BeanDefinitionDependencyInjectionPostHandler;
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
        BeanCreator defaultCtorCreator = new DefaultConstructorBeanCreatorImpl();
        BeanCreator fieldInjection = new FieldBeanCreatorDecoratorImpl(defaultCtorCreator);
        BeanCreator setterInjection = new SetterInjectionBeanCreatorDecoratorImpl(fieldInjection);
        // bean依赖注入后处理器
        Listener<BeanDefinitionRegistry> postHandler = new BeanDefinitionDependencyInjectionPostHandler(
                beanFactory, setterInjection
        );
        // 常量注入后处理器
        Listener<BeanDefinitionRegistry> constantHandler = new BeanDefinitionConstantInjectionPostHandler(
                beanFactory
        );
        beanDefinitionLoader.addListener(postHandler);
        beanDefinitionLoader.addListener(constantHandler);
        loaderList.add(beanDefinitionLoader);

        initLoaders();
    }

}
