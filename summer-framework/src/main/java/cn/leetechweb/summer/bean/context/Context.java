package cn.leetechweb.summer.bean.context;

import cn.leetechweb.summer.bean.ContainerAware;
import cn.leetechweb.summer.bean.annotation.Component;
import cn.leetechweb.summer.bean.factory.BeanFactory;
import cn.leetechweb.summer.bean.handler.creation.PostCreationProcessor;
import cn.leetechweb.summer.bean.loader.Loader;
import cn.leetechweb.summer.bean.util.ReflectionUtils;
import cn.leetechweb.summer.mvc.annotation.Controller;
import cn.leetechweb.summer.mvc.handler.InvokeHandler;
import cn.leetechweb.summer.mvc.support.method.MethodInvoker;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

/**
 * Summer 应用容器上下文
 *
 * Project Name: summer
 * Create Time: 2020/11/3 22:01
 *
 * @author junyu lee
 **/
public abstract class Context {

    protected final List<Loader> loaderList = new ArrayList<>();

    protected BeanFactory beanFactory;

    public Object getBean(String beanName) {
        return this.beanFactory.getBean(beanName);
    }

    @SuppressWarnings("unchecked")
    public <T> T getBean(String beanName, Class<T> clazz) {
        return this.beanFactory.getBean(beanName, clazz);
    }

    protected void initLoaders() throws ClassNotFoundException {
        for (Loader loader : this.loaderList) {
            loader.load();
        }
    }

    protected void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    protected List<Predicate<Class<?>>> classFilters() {
        List<Predicate<Class<?>>> result = new ArrayList<>();
        Set<Class<?>> allowedInterfaces = new HashSet<>();
        allowedInterfaces.add(ContainerAware.class);
        allowedInterfaces.add(PostCreationProcessor.class);
        allowedInterfaces.add(InvokeHandler.class);
        allowedInterfaces.add(MethodInvoker.class);
        result.add(pClass -> pClass.isAnnotationPresent(Component.class));
        result.add(pClass -> pClass.isAnnotationPresent(Controller.class));
        result.add(pClass -> {
            if (pClass.isInterface()) {
                return false;
            }
            List<Class<?>> interfaces = ReflectionUtils.getInterfaces(pClass);
            for (Class<?> i : interfaces) {
                if (allowedInterfaces.contains(i)) {
                    return true;
                }
            }
            return false;
        });
        return result;
    }

}
