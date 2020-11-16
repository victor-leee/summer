package cn.leetechweb.summer.bean.creator;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * 实例构建器
 * 通过构造函数，并经过一定的修饰，构造一个示例对象
 * 构造函数的直接实现：
 * @see cn.leetechweb.summer.bean.creator.impl.ConstructorInstanceCreatorImpl
 * @see cn.leetechweb.summer.bean.creator.impl.DefaultConstructorInstanceCreatorImpl
 * 修饰器基类：
 * @see InstanceCreatorDecorator
 * 修饰器实现类：
 * 1. 字段注入修饰器：
 * @see cn.leetechweb.summer.bean.creator.impl.FieldInstanceCreatorDecoratorImpl
 * 2. setter注入修饰器：
 * @see cn.leetechweb.summer.bean.creator.impl.SetterInjectionInstanceCreatorDecoratorImpl
 *
 * Project Name: summer
 * Create Time: 2020/11/13 22:27
 *
 * @author junyu lee
 **/
public interface InstanceCreator {

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
