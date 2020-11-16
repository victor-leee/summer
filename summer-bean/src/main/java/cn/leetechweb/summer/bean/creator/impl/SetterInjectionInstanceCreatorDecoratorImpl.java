package cn.leetechweb.summer.bean.creator.impl;

import cn.leetechweb.summer.bean.creator.InstanceCreator;
import cn.leetechweb.summer.bean.creator.InstanceCreatorDecorator;
import cn.leetechweb.summer.bean.util.BeanUtils;
import cn.leetechweb.summer.bean.util.ReflectionUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.List;
import java.util.Map;

/**
 * 构造器注入Decorator，对已有bean实例作setter注入工作
 * Project Name: summer
 * Create Time: 2020/11/14 13:00
 *
 * @author junyu lee
 **/
public class SetterInjectionInstanceCreatorDecoratorImpl extends InstanceCreatorDecorator {

    public SetterInjectionInstanceCreatorDecoratorImpl(InstanceCreator instanceCreator) {
        super(instanceCreator);
    }

    @Override
    protected void fill(Object bean, Map<String, Object> paramMap) throws InvocationTargetException, IllegalAccessException {
        List<Method> methods = ReflectionUtils.getMethodsNeedAutowired(bean);
        for (Method method : methods) {
            ReflectionUtils.makeAccessible(method);
            Parameter[] parameters = method.getParameters();
            Object[] args = new Object[parameters.length];
            for (int i = 0; i < args.length; i++) {
                Parameter parameter = parameters[i];
                String beanName = BeanUtils.getBeanName(parameter);
                args[i] = paramMap.get(beanName);
            }
            method.invoke(bean, args);
        }
    }

}
