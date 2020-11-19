package cn.leetechweb.summer.bean.creator;


import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * 实例构建器
 * 通过构造函数，并经过一定的修饰，构造一个示例对象
 * 构造函数的直接实现：
 * @see cn.leetechweb.summer.bean.creator.impl.ConstructorInstanceCreatorImpl
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
     * @param bean bean定义
     * @param isCreated 是否已经被创建
     * @return 类示例
     * @throws InstantiationException 初始化异常
     * @throws InvocationTargetException 方法执行异常
     * @throws IllegalAccessException 访问field异常
     */
    Object create(Object bean, boolean isCreated) throws InstantiationException, InvocationTargetException, IllegalAccessException;

    /**
     * 使用setter/constructor/field injection构造一个clazz对象
     * @param bean bean定义
     * @param isCreated 是否已经被创建
     * @param paramMap 参数map
     * @return 类对象
     * @throws InstantiationException 初始化异常
     * @throws InvocationTargetException 方法执行异常
     * @throws IllegalAccessException 访问field异常
     */
    Object create(Object bean, boolean isCreated, Map<String,Object> paramMap) throws InstantiationException, InvocationTargetException, IllegalAccessException;

}
