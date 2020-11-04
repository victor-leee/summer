package cn.leetechweb.summer.bean.definition;

import cn.leetechweb.summer.bean.util.StringUtils;

import java.util.*;

/**
 * BeanDefinition的注册类
 * Project Name: summer
 * Create Time: 2020/11/4 0:37
 *
 * @author junyu lee
 **/
public final class BeanDefinitionRegistry implements Iterable<AbstractBeanDefinition> {

    private final Map<String, AbstractBeanDefinition> beanDefinitions = new HashMap<>(256);

    public void addBeanDefinition(AbstractBeanDefinition abstractBeanDefinition) {
        this.beanDefinitions.put(abstractBeanDefinition.getBeanName(), abstractBeanDefinition);
    }

    public AbstractBeanDefinition getBeanDefinition(String beanName) {
        AbstractBeanDefinition abstractBeanDefinition = this.beanDefinitions.get(beanName);
        if (abstractBeanDefinition == null) {
            throw new RuntimeException(StringUtils.format("beanName为:{}的beanDefinition为null", false,
                    beanName));
        }
        return abstractBeanDefinition;
    }

    Map<String, AbstractBeanDefinition> getBeanDefinitions() {
        return beanDefinitions;
    }

    @Override
    public Iterator<AbstractBeanDefinition> iterator() {
        return new BeanDefinitionRegistryIter(this);
    }

    private static class BeanDefinitionRegistryIter implements Iterator<AbstractBeanDefinition> {

        BeanDefinitionRegistry registry;

        String[] keys;

        int curr = 0;

        BeanDefinitionRegistryIter(BeanDefinitionRegistry registry) {
            this.registry = registry;
            this.keys = registry.getBeanDefinitions().keySet().toArray(new String[0]);
        }

        @Override
        public boolean hasNext() {
            return curr < keys.length;
        }

        @Override
        public AbstractBeanDefinition next() {
            return registry.getBeanDefinition(keys[curr++]);
        }
    }
}
