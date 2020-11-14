package cn.leetechweb.summer.bean.definition.impl;

import cn.leetechweb.summer.bean.definition.BeanDefinitionParameter;

/**
 * Project Name: summer
 * Create Time: 2020/11/13 21:18
 *
 * @author junyu lee
 **/
public class AnnotationBeanDefinitionParameter implements BeanDefinitionParameter {

    private final String parameterName;

    private final Class<?> referenceClass;

    private final boolean isReference;

    private final String parameterValue;

    @Override
    public boolean isReference() {
        return this.isReference;
    }

    @Override
    public String getParameterName() {
        return this.parameterName;
    }

    @Override
    public String getParameterValue() {
        return this.parameterValue;
    }

    public Class<?> getReferenceClass() {
        return referenceClass;
    }

    public AnnotationBeanDefinitionParameter(String parameterName, Class<?> referenceClass) {
        this.parameterName = parameterName;
        this.referenceClass = referenceClass;
        this.isReference = true;
        this.parameterValue = referenceClass.getName();
    }

    public AnnotationBeanDefinitionParameter(String parameterName, String parameterValue) {
        this.parameterName = parameterName;
        this.parameterValue = parameterValue;
        this.isReference = false;
        this.referenceClass = null;
    }

}
