package cn.leetechweb.summer.bean.loader;

import cn.leetechweb.summer.bean.Listener;
import cn.leetechweb.summer.bean.Publisher;
import cn.leetechweb.summer.bean.definition.BeanDefinitionRegistry;
import cn.leetechweb.summer.bean.xml.DomLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * BeanDefinition的加载器
 * 使用parser/XmlNodeParserRegistry处理由DomLoader提供的Element并返回BeanDefinition
 * @see DomLoader
 * @see cn.leetechweb.summer.bean.loader.parser.XmlNodeParserRegistry
 * @see cn.leetechweb.summer.bean.definition.AbstractBeanDefinition
 *
 * Project Name: summer
 * Create Time: 2020/11/3 21:06
 *
 * @author junyu lee
 **/
public abstract class BeanDefinitionLoader implements Loader, Publisher<BeanDefinitionRegistry> {

    /**
     * beanDef注册表
     */
    protected final BeanDefinitionRegistry beanRegistry = new BeanDefinitionRegistry();

    /**
     * 监听loader初始化的listeners
     * 当初始化工作完毕后，listeners会进行下一步的实例化工作
     */
    protected final List<Listener<BeanDefinitionRegistry>> listeners = new ArrayList<>();

    @Override
    public void publish(BeanDefinitionRegistry data) {
        this.listeners.forEach(listener -> listener.onEvent(data));
    }

    @Override
    public void addListener(Listener<BeanDefinitionRegistry> listener) {
        this.listeners.add(listener);
    }
}
