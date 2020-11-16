package cn.leetechweb.summer.bean.definition.impl;

import cn.leetechweb.summer.bean.definition.AbstractBeanDefinition;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 全局唯一的静态Bean注册项
 * Project Name: summer
 * Create Time: 2020/11/3 15:56
 *
 * @author junyu lee
 **/
public class XmlBeanDefinitionImpl implements AbstractBeanDefinition {

    /**
     * bean名称
     */
    private final String beanName;

    /**
     * bean 完整类路径
     */
    private final String beanCompletePath;

    /**
     * 参数映射
     */
    private final Map<String, XmlBeanDefinitionParameter> paramMap;

    /**
     * 依赖关系
     */
    private final String[] dependsOn;

    /**
     * 所有的参数名
     */
    private final String[] parameterNames;

    @Override
    public String[] getParameterNames() {
        return this.parameterNames;
    }

    @Override
    public XmlBeanDefinitionParameter getParameter(String parameterName) {
        return this.paramMap.get(parameterName);
    }


    public XmlBeanDefinitionImpl(String beanName, String beanCompletePath,
                                 Map<String, XmlBeanDefinitionParameter> paramMap) {
        this.beanName = beanName;
        this.beanCompletePath = beanCompletePath;
        this.paramMap = paramMap;
        List<String> dependsOn = new ArrayList<>();
        List<String> parameterNames = new ArrayList<>();
        paramMap.forEach((bn, parameter) -> {
            if (parameter.isReference()) {
                dependsOn.add(parameter.getParameterValue());
            }
            parameterNames.add(parameter.getParameterName());
        });
        this.dependsOn = dependsOn.toArray(new String[0]);
        this.parameterNames = parameterNames.toArray(new String[0]);
    }

    @Override
    public String getBeanName() {
        return beanName;
    }

    @Override
    public boolean isDependencyInjection() {
        return dependsOn != null;
    }

    @Override
    public boolean isMethodProduce() {
        return false;
    }

    @Override
    public Class<?> beanType() {
        return null;
    }

    @Override
    public String[] dependsOn() {
        return dependsOn;
    }

}
