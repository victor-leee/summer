package cn.leetechweb.summer.bean.definition;

import cn.leetechweb.summer.bean.exception.AnnotationContainerInitializationException;
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

    private final Map<Class<?>, List<String>> beanClassList = new HashMap<>(256);

    public void addBeanDefinition(AbstractBeanDefinition abstractBeanDefinition) {
        Class<?> beanType = abstractBeanDefinition.beanType();
        String beanName = abstractBeanDefinition.getBeanName();
        this.beanDefinitions.put(beanName, abstractBeanDefinition);
        this.levelUpAddBeanDefinition(beanName, beanType);
    }

    private void levelUpAddBeanDefinition(String beanName, Class<?> beanType) {
        this.add(beanName, beanType);
        this.addInterfacesBeanDefinition(beanName, beanType);
        beanType = beanType.getSuperclass();
        while (!Object.class.equals(beanType)) {
            this.add(beanName, beanType);
            this.addInterfacesBeanDefinition(beanName, beanType);
            beanType = beanType.getSuperclass();
        }
    }

    private void addInterfacesBeanDefinition(String beanName, Class<?> beanType) {
        Class<?>[] interfaceTypes = beanType.getInterfaces();
        for (Class<?> interfaceType : interfaceTypes) {
            add(beanName, interfaceType);
        }
    }

    private void add(String beanName, Class<?> beanType) {
        if (!this.beanClassList.containsKey(beanType)) {
            this.beanClassList.put(beanType, new ArrayList<>());
        }
        this.beanClassList.get(beanType).add(beanName);
    }

    public AbstractBeanDefinition getBeanDefinition(String beanName) {
        AbstractBeanDefinition abstractBeanDefinition = this.beanDefinitions.get(beanName);
        if (abstractBeanDefinition == null) {
            throw new RuntimeException(StringUtils.format("beanName为:{}的beanDefinition为null", false,
                    beanName));
        }
        return abstractBeanDefinition;
    }

    /**
     * 返回parameter描述的bean对象
     * @param parameter 未实例化时bean的参数描述符
     * @return 这个参数描述的bean
     */
    public AbstractBeanDefinition getBeanDefinition(BeanDefinitionParameter parameter) {
        // 只有在引用的情况下该参数才能描述一个bean对象
        if (parameter.isReference()) {
            String beanName = parameter.getParameterValue();
            try {
                return getBeanDefinition(beanName);
            }catch (Exception ignored){}
            Class<?> paramType = parameter.getParameterType();
            List<String> autowireBeanNameList = this.beanClassList.get(paramType);
            if (autowireBeanNameList.size() > 1) {
                throw new AnnotationContainerInitializationException(StringUtils.format(
                        "检测到{}使用了@Autowired注入，但是summer container中有多个{}类型的bean，请使用@Resource指定具体的beanName注入", false,
                        beanName, paramType.getName()));
            }
            if (autowireBeanNameList.size() == 0) {
                throw new RuntimeException(StringUtils.format("beanName为:{}的beanDefinition为null", false,
                        beanName));
            }
            return this.beanDefinitions.get(autowireBeanNameList.get(0));
        }
        return null;
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
