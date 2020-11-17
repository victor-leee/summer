package cn.leetechweb.summer.mvc.support;

import cn.leetechweb.summer.mvc.mapping.ServletDescriptor;
import cn.leetechweb.summer.mvc.mapping.argument.ArgumentMapper;

import java.lang.reflect.Parameter;

/**
 * Project Name: summer
 * Create Time: 2020/11/17 22:14
 *
 * @author junyu lee
 **/
public class SimpleMethodInvoker implements MethodInvoker {

    @Override
    public MethodInvokeResult doInvoke(ServletDescriptor servletDescriptor, ArgumentMapper argumentMapper) {
        Parameter[] methodParameters = servletDescriptor.getParameters();
        Object[] args = new Object[servletDescriptor.getMethod().getParameterCount()];
        for (int i = 0; i < args.length; i++) {
            args[i] = resolveEach(methodParameters[i], argumentMapper);
        }
        MethodInvokeResult invokeResult = new MethodInvokeResult();
        servletDescriptor.invoke(args);
        return invokeResult;
    }

    /**
     * 根据argParameter的需求，在argumentMapper中寻找最佳的参数
     * @param argParameter 参数实体
     * @param argumentMapper 参数映射表
     * @return parameter对应的最佳参数
     */
    private Object resolveEach(Parameter argParameter, ArgumentMapper argumentMapper) {
        return null;
    }

}
