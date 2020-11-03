package cn.leetechweb.summer.bean.definition;

import java.util.Map;

/**
 * 抽象Bean定义类
 * Project Name: summer
 * Create Time: 2020/11/3 15:51
 *
 * @author junyu lee
 **/
public interface AbstractBeanDefinition {
    /**
     * 设置实例化该bean的依赖列表，必须确保依赖列表中的bean先初始化
     * @param relies 实例化该bean的依赖bean名称列表
     */
    void setDependencies(String[] relies);

    /**
     * 获取该Bean的参数
     * @param parameterName 参数名
     * @return 参数值
     */
    Object getParameter(String parameterName);

    /**
     * 返回这个Bean的所有参数和参数值的映射关系
     * @return 参数key->value映射关系
     */
    Map<String, Object> getParameterMap();

    /**
     * 获取bean名称
     * @return bean 名
     */
    String getBeanName();

    /**
     * 例如Bean为java/cn/leetechweb/summer/Test.java
     * 返回java/cn/leetechweb/summer/Test.java
     * @return bean的完整classpath
     */
    String getBeanCompletePath();
}
