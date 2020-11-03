package cn.leetechweb.summer.bean.definition;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * BeanDefinition的注册类
 * Project Name: summer
 * Create Time: 2020/11/4 0:37
 *
 * @author junyu lee
 **/
public final class BeanDefinitionRegistry implements Iterable<AbstractBeanDefinition> {

    private final List<AbstractBeanDefinition> beanDefinitions = new ArrayList<>();

    public void addBeanDefinition(AbstractBeanDefinition abstractBeanDefinition) {
        this.beanDefinitions.add(abstractBeanDefinition);
    }

    public AbstractBeanDefinition getBeanDefinition(String beanName) {
        for (AbstractBeanDefinition definition : this.beanDefinitions) {
            if (definition.getBeanName().equals(beanName)) {
                return definition;
            }
        }
        return null;
    }

    @Override
    public Iterator<AbstractBeanDefinition> iterator() {
        return new BeanDefinitionRegistryIter(this);
    }

    public List<AbstractBeanDefinition> getBeanDefinitions() {
        return beanDefinitions;
    }

    private static class BeanDefinitionRegistryIter implements Iterator<AbstractBeanDefinition> {

        BeanDefinitionRegistry registry;

        BeanDefinitionRegistryIter(BeanDefinitionRegistry registry) {
            this.registry = registry;
        }

        private int curr = 0;

        @Override
        public boolean hasNext() {
            return curr < registry.getBeanDefinitions().size();
        }

        @Override
        public AbstractBeanDefinition next() {
            return registry.getBeanDefinitions().get(curr++);
        }
    }
}
