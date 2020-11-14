package cn.leetechweb.summer.bean.creator.impl;

import cn.leetechweb.summer.bean.creator.BeanCreator;
import cn.leetechweb.summer.bean.creator.BeanCreatorDecorator;
import cn.leetechweb.summer.bean.util.ReflectionUtils;

import java.util.Map;

/**
 * Project Name: summer
 * Create Time: 2020/11/13 22:37
 *
 * @author junyu lee
 **/
public class FieldBeanCreatorDecoratorImpl extends BeanCreatorDecorator {

    public FieldBeanCreatorDecoratorImpl(BeanCreator beanCreator) {
        super(beanCreator);
    }

    @Override
    protected void fill(Object bean, Map<String, Object> paramMap) throws IllegalAccessException, IllegalAccessException {
        ReflectionUtils.setFieldParameters(bean, paramMap);
    }

}
