package cn.leetechweb.summer.bean.definition.impl;

import cn.leetechweb.summer.bean.definition.BeanDefinitionParameter;
import cn.leetechweb.summer.bean.util.BeanUtils;

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
     * 是否是引用类型参数
     */
    private final boolean isReference;

    /**
     * 如果是primitive类型，则这个代表实际的参数值
     * 否则依然是bean的名称
     */
    private final String parameterValue;

    private final Class<?> referenceClass;

    public Class<?> getReferenceClass() {
        return referenceClass;
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

    /**
     * 指定该参数依赖于其他对象
     * 参数名和参数值为依赖的对象beanName
     * 注意这里的beanName使用默认的beanName，也就是类的名称
     * @param referenceClass 依赖对象
     */
    public AnnotationBeanDefinitionParameter(Class<?> referenceClass) {
        this.parameterName = BeanUtils.getBeanName(referenceClass);
        this.isReference = true;
        this.parameterValue = BeanUtils.getBeanName(referenceClass);
        this.referenceClass = referenceClass;
    }

    /**
     * 显式指定依赖的bean名称
     * @param beanName 以来的bean名称
     */
    public AnnotationBeanDefinitionParameter(String beanName) {
        this.parameterName = beanName;
        this.isReference = true;
        this.parameterValue = beanName;
        this.referenceClass = null;
    }

    public AnnotationBeanDefinitionParameter(String parameterName, String parameterValue) {
        this.parameterName = parameterName;
        this.parameterValue = parameterValue;
        this.isReference = false;
        this.referenceClass = null;
    }

}
