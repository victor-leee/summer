package cn.leetechweb.summer.bean.creator.impl;

import cn.leetechweb.summer.bean.creator.BeanCreator;
import cn.leetechweb.summer.bean.creator.BeanCreatorDecorator;
import cn.leetechweb.summer.bean.util.Assert;
import cn.leetechweb.summer.bean.util.ReflectionUtils;
import cn.leetechweb.summer.bean.util.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

/**
 * 构造器注入Decorator，对已有bean示例作setter注入工作
 * Project Name: summer
 * Create Time: 2020/11/14 13:00
 *
 * @author junyu lee
 **/
public class SetterInjectionBeanCreatorDecoratorImpl extends BeanCreatorDecorator {

    public SetterInjectionBeanCreatorDecoratorImpl(BeanCreator beanCreator) {
        super(beanCreator);
    }

    @Override
    protected void fill(Object bean, Map<String, Object> paramMap) throws InvocationTargetException, IllegalAccessException {
        List<Method> methods = ReflectionUtils.getMethodsNeedAutowired(bean);
        for (Method method : methods) {
            ReflectionUtils.makeAccessible(method);
            Class<?>[] paramTypes = method.getParameterTypes();
            Object[] methodParamValues = new Object[method.getParameterCount()];
            for (int i = 0; i < methodParamValues.length; i++) {
                Object setterBean = paramMap.get(paramTypes[i].getSimpleName());
                Assert.isNotNull(setterBean, StringUtils.format("构造{}发生注入错误, 第{}个参数为null", false,
                        bean.getClass().getName(), i));
                methodParamValues[i] = setterBean;
            }
            method.invoke(bean, methodParamValues);
        }
    }

}
