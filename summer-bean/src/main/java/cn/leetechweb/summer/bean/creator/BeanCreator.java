package cn.leetechweb.summer.bean.creator;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * Bean 构造器
 * 提供多种不同的bean构造方式
 * 包括构造函数构造，setter injection构造，field injection构造
 * Project Name: summer
 * Create Time: 2020/11/13 22:27
 *
 * @author junyu lee
 **/
public interface BeanCreator {

    /**
     * 使用默认构造函数构造一个clazz类型的对象
     * @param clazz 类
     * @return 类示例
     * @throws InstantiationException 初始化异常
     * @throws InvocationTargetException 方法执行异常
     * @throws IllegalAccessException 访问field异常
     */
    Object create(Class<?> clazz) throws InstantiationException, InvocationTargetException, IllegalAccessException;

    /**
     * 使用setter/constructor/field injection构造一个clazz对象
     * @param clazz 类
     * @param paramMap 参数map
     * @return 类对象
     * @throws InstantiationException 初始化异常
     * @throws InvocationTargetException 方法执行异常
     * @throws IllegalAccessException 访问field异常
     */
    Object create(Class<?> clazz, Map<String,Object> paramMap) throws InstantiationException, InvocationTargetException, IllegalAccessException;

    /**
     * 按照bean路径构造对象
     * @param beanPath bean的类路径
     * @param paramMap 参数列表
     * @return bean对象
     * @throws InstantiationException 初始化异常
     * @throws InvocationTargetException 方法执行异常
     * @throws IllegalAccessException 访问field异常
     */
    Object create(String beanPath, Map<String,Object> paramMap) throws IllegalAccessException, InvocationTargetException, InstantiationException;

}
