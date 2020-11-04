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
     * 返回创建该bean时的依赖
     * @return 返回依赖
     */
    String[] dependsOn();

    /**
     * 获取该Bean的参数
     * @param parameterName 参数名
     * @return 参数值
     */
    BeanDefinitionParameter getParameter(String parameterName);

    /**
     * 获取该beanDef的所有参数名
     * @return 所有的参数名
     */
    String[] getParameterNames();

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
