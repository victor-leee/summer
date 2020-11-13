package cn.leetechweb.summer.bean.context;

import cn.leetechweb.summer.bean.factory.BeanFactory;
import cn.leetechweb.summer.bean.loader.Loader;

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

    protected void initLoaders() {
        this.loaderList.forEach(Loader::load);
    }

    protected void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

}
