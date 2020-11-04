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

    protected final List<BeanFactory> beanFactories = new ArrayList<>();

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

    protected void initLoaders() {
        this.loaderList.forEach(Loader::load);
    }

}
