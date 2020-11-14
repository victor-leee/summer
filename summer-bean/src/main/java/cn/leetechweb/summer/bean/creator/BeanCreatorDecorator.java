package cn.leetechweb.summer.bean.creator;

import cn.leetechweb.summer.bean.util.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * Bean构造器的装饰器
 * 用于处理构造Bean过后的额外工作
 * 例如通过setter或者字段注入一些依赖
 * @see cn.leetechweb.summer.bean.creator.impl.FieldBeanCreatorDecoratorImpl
 * @see cn.leetechweb.summer.bean.creator.impl.SetterInjectionBeanCreatorDecoratorImpl
 * Project Name: summer
 * Create Time: 2020/11/14 13:01
 *
 * @author junyu lee
 **/
public abstract class BeanCreatorDecorator implements BeanCreator {

    /**
     * 使用构造函数的bean creator，用于构造基本的bean
     */
    private final BeanCreator beanCreator;

    public BeanCreatorDecorator(BeanCreator beanCreator) {
        this.beanCreator = beanCreator;
    }

    @Override
    public Object create(Class<?> clazz, Map<String, Object> paramMap) throws InstantiationException, InvocationTargetException, IllegalAccessException {
        Object bean = this.beanCreator.create(clazz, paramMap);
        this.fill(bean, paramMap);
        return bean;
    }

    @Override
    public Object create(Class<?> clazz) throws IllegalAccessException, InvocationTargetException, InstantiationException {
        return this.beanCreator.create(clazz);
    }

    @Override
    public Object create(String beanPath, Map<String, Object> paramMap) throws IllegalAccessException, InvocationTargetException, InstantiationException {
        return this.create(BeanUtils.loadClassFromPath(beanPath), paramMap);
    }

    /**
     * 使用paramMap对bean上的setter和field作一次遍历，将可以注入的注入上去
     * @param bean bean实体
     * @param paramMap 参数列表
     * @throws InvocationTargetException method执行发生错误
     * @throws IllegalAccessException 非法访问字段
     */
    protected abstract void fill(Object bean, Map<String, Object> paramMap) throws InvocationTargetException, IllegalAccessException;

}
