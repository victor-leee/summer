package cn.leetechweb.summer.bean.factory;

/**
 * Project Name: summer
 * Create Time: 2020/11/4 1:03
 *
 * @author junyu lee
 **/
public interface BeanFactory {

    /**
     * 根据名称返回对应的bean实体
     * @param beanName Bean名称
     * @return beanName对应的bean实体
     */
    Object getBean(String beanName);

    /**
     * 做一次强制转换后将bean返回
     * @param beanName bean名称
     * @param clazz 这个bean所属的类
     * @param <T> 类别
     * @return 将这个bean强制转换为clazz类别后返回
     */
    <T> T getBean(String beanName, Class<T> clazz);

    /**
     * 根据bean的名称在容器中查找该bean,如果找到返回true，否则返回false
     * @param beanName bean名称
     * @return 该容器中是否含有该bean
     */
    boolean hasBean(String beanName);

    /**
     * 添加一个新的bean到beanFactory中
     * @param beanName bean名称
     * @param bean bean实体
     */
    void addBean(String beanName, Object bean);
}
