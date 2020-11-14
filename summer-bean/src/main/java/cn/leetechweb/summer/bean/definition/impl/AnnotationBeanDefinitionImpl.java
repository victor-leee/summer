package cn.leetechweb.summer.bean.definition.impl;

import cn.leetechweb.summer.bean.definition.AbstractBeanDefinition;
import cn.leetechweb.summer.bean.definition.BeanDefinitionParameter;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
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
     * bean的类路径
     */
    String beanCompletePath;

    /**
     * 该方法是否是由带有@Bean的方法产生的
     */
    boolean isMethodProduce;

    /**
     * 如果isMethodProduce == true，则通过这个字段访问该方法
     */
    Method beanMethod;

    public AnnotationBeanDefinitionImpl(Map<String, AnnotationBeanDefinitionParameter> parameterMap,
                                        String beanName, String beanCompletePath) {
        this.parameterMap = parameterMap;
        this.beanName = beanName;
        this.beanCompletePath = beanCompletePath;
        this.dependsOn = parameterMap.values().stream()
                .filter(BeanDefinitionParameter::isReference)
                .map(BeanDefinitionParameter::getParameterName)
                .toArray(String[]::new);
    }

    public AnnotationBeanDefinitionImpl(String beanName, Method beanMethod) {
        this.isMethodProduce = true;
        this.beanMethod = beanMethod;
        this.beanName = beanName;
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
    public String getBeanCompletePath() {
        return this.beanCompletePath;
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
}
