package cn.leetechweb.summer.bean.definition;

/**
 * Bean definition的参数接口
 * Project Name: summer
 * Create Time: 2020/11/4 16:46
 *
 * @author junyu lee
 **/
public interface BeanDefinitionParameter {

    /**
     * 返回该参数是否引用了其他bean
     * 如果返回true，则参数值代表其他的bean的标识
     * 如果返回false，参数值代表primitive values
     * @return 该参数是否是引用其他bean的参数
     */
    boolean isReference();

    /**
     * 返回参数名
     * @return 参数名
     */
    String getParameterName();

    /**
     * 返回参数值
     * @return 参数值
     */
    String getParameterValue();

    /**
     * 返回参数类型
     * @return 参数类型
     */
    Class<?> getParameterType();
}
