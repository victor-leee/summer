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

    private final Map<String, List<String>> beanClassList = new HashMap<>(256);

    public void addBeanDefinition(AbstractBeanDefinition abstractBeanDefinition) {
        String completePath = abstractBeanDefinition.getBeanCompletePath();
        String beanName = abstractBeanDefinition.getBeanName();
        this.beanDefinitions.put(beanName, abstractBeanDefinition);
        if (!this.beanClassList.containsKey(completePath)) {
            this.beanClassList.put(completePath, new ArrayList<>());
        }
        this.beanClassList.get(completePath).add(beanName);
    }

    public AbstractBeanDefinition getBeanDefinition(String beanName) {
        AbstractBeanDefinition abstractBeanDefinition = this.beanDefinitions.get(beanName);
        if (abstractBeanDefinition == null) {
            throw new RuntimeException(StringUtils.format("beanName为:{}的beanDefinition为null", false,
                    beanName));
        }
        return abstractBeanDefinition;
    }

//    /**
//     * 返回parameter描述的bean对象
//     * @param parameter 未实例化时bean的参数描述符
//     * @return 这个参数描述的bean
//     */
//    public AbstractBeanDefinition getBeanDefinition(BeanDefinitionParameter parameter) {
//        // 只有在引用的情况下该参数才能描述一个bean对象
//        AbstractBeanDefinition definition;
//        if (parameter.isReference()) {
//            String beanName = parameter.getParameterValue();
//            if ((definition = getBeanDefinition(beanName)) != null) {
//                return definition;
//            }
//            Class<?> paramType = parameter.getParameterType();
//            List<String> autowireBeanNameList = this.beanClassList.get(paramType.getName());
//            if (autowireBeanNameList.size() > 1) {
//                throw new AnnotationContainerInitializationException(StringUtils.format(
//                        "检测到{}使用了@Autowired注入，但是summer container中有多个{}类型的bean，请使用@Resource指定具体的beanName注入", false,
//                        beanName, paramType.getName()));
//            }
//            if (autowireBeanNameList.size() == 0) {
//                throw new RuntimeException(StringUtils.format("beanName为:{}的beanDefinition为null", false,
//                        beanName));
//            }
//            return this.beanDefinitions.get(autowireBeanNameList.get(0));
//        }
//        return null;
//    }

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
