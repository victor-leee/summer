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

    @Override
    public boolean isReference() {
        return true;
    }

    @Override
    public String getParameterName() {
        return this.parameterName;
    }

    @Override
    public String getParameterValue() {
        return this.referenceClass.getName();
    }

    public Class<?> getReferenceClass() {
        return referenceClass;
    }

    public AnnotationBeanDefinitionParameter(String parameterName, Class<?> referenceClass) {
        this.parameterName = parameterName;
        this.referenceClass = referenceClass;
    }
}
