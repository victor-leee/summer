package cn.leetechweb.summer.bean.creator.impl;

import cn.leetechweb.summer.bean.creator.InstanceCreator;
import cn.leetechweb.summer.bean.creator.InstanceCreatorDecorator;
import cn.leetechweb.summer.bean.util.Assert;
import cn.leetechweb.summer.bean.util.BeanUtils;
import cn.leetechweb.summer.bean.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;


/**
 * Project Name: summer
 * Create Time: 2020/11/13 22:37
 *
 * @author junyu lee
 **/
public class FieldInstanceCreatorDecoratorImpl extends InstanceCreatorDecorator {

    public FieldInstanceCreatorDecoratorImpl(InstanceCreator instanceCreator) {
        super(instanceCreator);
    }

    @Override
    protected void fill(Object bean, Map<String, Object> paramMap) throws IllegalAccessException, IllegalAccessException {
        Assert.isNotNull(bean);
        List<Field> fields = ReflectionUtils.getFieldsNeedAutowired(bean.getClass());
        // 设置@Autowired字段和@Resource字段
        for (Field field : fields) {
            Object fieldVal = paramMap.get(BeanUtils.getBeanName(field));
            ReflectionUtils.setFieldParam(bean, field, fieldVal);
        }
    }

}
