package cn.leetechweb.summer.bean.definition.impl;

import cn.leetechweb.summer.bean.definition.AbstractBeanDefinition;
import cn.leetechweb.summer.bean.definition.BeanDefinitionParameter;

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

    public AnnotationBeanDefinitionImpl(Map<String, AnnotationBeanDefinitionParameter> parameterMap,
                                        String beanName) {
        this.parameterMap = parameterMap;
        this.beanName = beanName;
        List<String> depends = new ArrayList<>();
        for (BeanDefinitionParameter parameter : parameterMap.values()) {
            depends.add(parameter.getParameterValue());
        }
        this.dependsOn = depends.toArray(new String[0]);
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
        return null;
    }
}
