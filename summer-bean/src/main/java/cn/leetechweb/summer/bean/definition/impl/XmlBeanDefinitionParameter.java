package cn.leetechweb.summer.bean.definition.impl;

import cn.leetechweb.summer.bean.definition.BeanDefinitionParameter;

/**
 * Project Name: summer
 * Create Time: 2020/11/4 16:49
 *
 * @author junyu lee
 **/
public class XmlBeanDefinitionParameter implements BeanDefinitionParameter {

    private final boolean isReference;
    private final String parameterName;
    private final String parameterValue;

    public XmlBeanDefinitionParameter(String parameterName, String parameterValue, boolean isReference) {
        this.parameterName = parameterName;
        this.parameterValue = parameterValue;
        this.isReference = isReference;
    }

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

    @Override
    public Class<?> getParameterType() {
        return null;
    }

    @Override
    public boolean isConstructorParameter() {
        return false;
    }

}
