package cn.leetechweb.summer.bean.definition.impl;

import cn.leetechweb.summer.bean.definition.BeanDefinitionParameter;

/**
 * Project Name: summer
 * Create Time: 2020/11/13 21:18
 *
 * @author junyu lee
 **/
public class AnnotationBeanDefinitionParameter implements BeanDefinitionParameter {

    /**
     * 如果是primitive类型，则这个代表fieldName
     * 否则代表beanName
     */
    private final String parameterName;

    /**
     * 引用的类
     */
    private final Class<?> referenceClass;

    /**
     * 是否是引用类型参数
     */
    private final boolean isReference;

    /**
     * 如果是primitive类型，则这个代表实际的参数值
     * 否则它不用代表什么，因为我们使用beanName(parameterName)作依赖注入
     * 但是还是使用全限定类名做了填充
     */
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
