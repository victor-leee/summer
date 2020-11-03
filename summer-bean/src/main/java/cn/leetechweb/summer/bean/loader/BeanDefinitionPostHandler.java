package cn.leetechweb.summer.bean.loader;

import cn.leetechweb.summer.bean.Listener;
import cn.leetechweb.summer.bean.definition.AbstractBeanDefinition;
import cn.leetechweb.summer.bean.definition.BeanDefinitionRegistry;
import cn.leetechweb.summer.bean.factory.BeanFactory;
import cn.leetechweb.summer.bean.util.BeanUtils;

import java.util.Map;

/**
 * 在容器初始化所有的BeanDefinition后，BeanDefinitionPostHandler负责处理注册表中的信息
 * Project Name: summer
 * Create Time: 2020/11/3 22:22
 *
 * @author junyu lee
 **/
public final class BeanDefinitionPostHandler implements Listener<BeanDefinitionRegistry> {

    private final BeanFactory beanFactory;

    @Override
    public void onEvent(BeanDefinitionRegistry data) {
        for (AbstractBeanDefinition beanDefinition : data) {
            String beanName = beanDefinition.getBeanName();
            String className = beanDefinition.getBeanCompletePath();
            Map<String, Object> constructorArgs = beanDefinition.getParameterMap();
            Object bean = BeanUtils.createBeanByConstructor(className, constructorArgs);
            beanFactory.addBean(beanName, bean);
        }
    }

    public BeanDefinitionPostHandler(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

}
