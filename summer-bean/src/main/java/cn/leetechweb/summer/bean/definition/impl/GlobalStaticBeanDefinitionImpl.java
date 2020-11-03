package cn.leetechweb.summer.bean.definition.impl;

import cn.leetechweb.summer.bean.definition.AbstractBeanDefinition;

import java.util.Map;

/**
 * 全局唯一的静态Bean注册项
 * Project Name: summer
 * Create Time: 2020/11/3 15:56
 *
 * @author junyu lee
 **/
public class GlobalStaticBeanDefinitionImpl implements AbstractBeanDefinition {

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
    private final Map<String, Object> paramMap;


    @Override
    public void setDependencies(String[] relies) {

    }

    @Override
    public Object getParameter(String parameterName) {
        return this.paramMap.get(parameterName);
    }

    @Override
    public Map<String, Object> getParameterMap() {
        return this.paramMap;
    }

    public GlobalStaticBeanDefinitionImpl(String beanName, String beanCompletePath,
                                          Map<String, Object> paramMap) {
        this.beanName = beanName;
        this.beanCompletePath = beanCompletePath;
        this.paramMap = paramMap;
    }

    @Override
    public String getBeanName() {
        return beanName;
    }

    @Override
    public String getBeanCompletePath() {
        return beanCompletePath;
    }
}
