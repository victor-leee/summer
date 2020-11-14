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
     * 获取某一个对象上某个参数的包装对象
     * @param parameterName 参数名
     * @return 参数值
     */
    BeanDefinitionParameter getParameter(String parameterName);

    /**
     * 获取该beanDef的所有参数名
     * 这里对参数名做一下解释
     * 如果是xml配置文件，例如构造函数注入，则参数名是order的顺序 1,2,3...
     * 所以如果要在xml配置容器下访问依赖bean，需要取得参数后使用
     * @see #getParameter(String) 取得bean参数包装对象，获取实际的bean名称
     * 如果是注解，在bean依赖注入下，参数名是bean的名字（全局唯一）
     * 在常量值注入下，参数名是字段名
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
