package cn.leetechweb.summer.bean.context;

import cn.leetechweb.summer.bean.Listener;
import cn.leetechweb.summer.bean.creator.BeanCreator;
import cn.leetechweb.summer.bean.creator.InstanceCreator;
import cn.leetechweb.summer.bean.creator.impl.ConstructorInstanceCreatorImpl;
import cn.leetechweb.summer.bean.creator.impl.FieldInstanceCreatorDecoratorImpl;
import cn.leetechweb.summer.bean.annotation.reader.impl.SimpleBasePackageReader;
import cn.leetechweb.summer.bean.creator.impl.SetterInjectionInstanceCreatorDecoratorImpl;
import cn.leetechweb.summer.bean.definition.BeanDefinitionRegistry;
import cn.leetechweb.summer.bean.factory.BeanFactory;
import cn.leetechweb.summer.bean.factory.impl.SimpleBeanFactory;
import cn.leetechweb.summer.bean.handler.*;
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
        // 定义Bean定义表的加载器，通过指定的根路径加载
        BeanDefinitionLoader beanDefinitionLoader = new AnnotationConfigBeanDefinitionLoader(
            baseClass, new SimpleBasePackageReader(), this.classFilters()
        );
        BeanFactory beanFactory = new SimpleBeanFactory();
        setBeanFactory(beanFactory);
        // 创建实例构造器
        InstanceCreator ctorInjection = new ConstructorInstanceCreatorImpl();
        InstanceCreator fieldInjection = new FieldInstanceCreatorDecoratorImpl(ctorInjection);
        InstanceCreator setterInjection = new SetterInjectionInstanceCreatorDecoratorImpl(fieldInjection);
        BeanCreator beanCreator = new BeanCreator(setterInjection, beanFactory);
        // bean依赖注入处理器
        Listener<BeanDefinitionRegistry> dependencyHandler = new BeanDefinitionCtorDependencyInjectionHandler(
                beanFactory, beanCreator
        );
        // 常量注入处理器
        Listener<BeanDefinitionRegistry> constantHandler = new BeanDefinitionConstantInjectionHandler(
                beanFactory, beanCreator
        );
        // 其他bean依赖注入处理器
        Listener<BeanDefinitionRegistry> restDependencyHandler = new BeanDefinitionFieldDependencyInjectionHandler(
                beanFactory, beanCreator
        );
        // ContainerAware方法注入器
        Listener<BeanDefinitionRegistry> containerAwareHandler = new BeanDefinitionContainerAwareInjectionHandler(
                beanFactory, beanCreator
        );
        // PostBeanProcessor方法注入器
        Listener<BeanDefinitionRegistry> postBeanProcessorHandler = new BeanDefinitionPostBeanProcessor(
                beanFactory, beanCreator
        );
        beanDefinitionLoader.addListener(dependencyHandler);
        beanDefinitionLoader.addListener(constantHandler);
        beanDefinitionLoader.addListener(restDependencyHandler);
        beanDefinitionLoader.addListener(containerAwareHandler);
        beanDefinitionLoader.addListener(postBeanProcessorHandler);
        loaderList.add(beanDefinitionLoader);

        initLoaders();
    }

}
