package cn.leetechweb.summer.bean.definition.impl;

import cn.leetechweb.summer.bean.definition.AbstractBeanDefinition;
import cn.leetechweb.summer.bean.definition.BeanDefinitionParameter;
import cn.leetechweb.summer.bean.util.Assert;

import java.lang.reflect.Method;
import java.util.Map;


/**
 * Project Name: summer
 * Create Time: 2020/11/13 21:13
 *
 * @author junyu lee
 **/
public class AnnotationBeanDefinitionImpl implements AbstractBeanDefinition {

    /**
     * 构建这个bean需要的依赖
     */
    String[] dependsOn;

    /**
     * 构建这个bean的参数信息
     */
    Map<String, AnnotationBeanDefinitionParameter> parameterMap;

    /**
     * 这个bean的名称
     */
    String beanName;

    /**
     * 该方法是否是由带有@Bean的方法产生的
     */
    boolean isMethodProduce;

    /**
     * 如果isMethodProduce == true，则通过这个字段访问该方法
     */
    Method beanMethod;

    /**
     * 父bean定义，用于在@Bean生成bean对象时引用
     */
    AbstractBeanDefinition parentBeanDef;

    Class<?> beanType;

    public AnnotationBeanDefinitionImpl(Map<String, AnnotationBeanDefinitionParameter> parameterMap,
                                        String beanName, Class<?> beanType) {
        this.parameterMap = parameterMap;
        this.beanName = beanName;
        this.beanType = beanType;
        this.dependsOn = parameterMap.values().stream()
                .filter(BeanDefinitionParameter::isReference)
                .map(BeanDefinitionParameter::getParameterName)
                .toArray(String[]::new);
    }

    public AnnotationBeanDefinitionImpl(String beanName, Method beanMethod, AbstractBeanDefinition parentBeanDef,
                                        Map<String, AnnotationBeanDefinitionParameter> parameterMap,
                                        String[] dependsOn) {
        this.isMethodProduce = true;
        this.beanMethod = beanMethod;
        this.beanName = beanName;
        this.parentBeanDef = parentBeanDef;
        this.beanType = beanMethod.getReturnType();

        // 这里处理的时候注意，这个bean的构建还要依赖于父bean的构建完成
        parameterMap.put(parentBeanDef.getBeanName(),
                new AnnotationBeanDefinitionParameter(parentBeanDef.getBeanName(), parentBeanDef.beanType()));
        String[] thisDepends = new String[dependsOn.length + 1];
        System.arraycopy(dependsOn, 0, thisDepends, 0, dependsOn.length);
        thisDepends[dependsOn.length] = parentBeanDef.getBeanName();
        this.dependsOn = thisDepends;
        this.parameterMap = parameterMap;
    }

    @Override
    public String[] dependsOn() {
        return this.dependsOn;
    }

    @Override
    public AnnotationBeanDefinitionParameter getParameter(String parameterName) {
        return this.parameterMap.get(parameterName);
    }

    @Override
    public String[] getParameterNames() {
        return this.parameterMap.keySet().toArray(new String[0]);
    }

    @Override
    public String getBeanName() {
        return this.beanName;
    }

    @Override
    public boolean isDependencyInjection() {
        return this.dependsOn != null;
    }

    public Method getBeanMethod() {
        return beanMethod;
    }

    @Override
    public boolean isMethodProduce() {
        return isMethodProduce;
    }

    @Override
    public Class<?> beanType() {
        return this.beanType;
    }

    public AbstractBeanDefinition getParentBeanDef() {
        return parentBeanDef;
    }
}
