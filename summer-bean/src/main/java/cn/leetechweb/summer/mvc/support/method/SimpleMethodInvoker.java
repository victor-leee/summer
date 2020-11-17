package cn.leetechweb.summer.mvc.support.method;

import cn.leetechweb.summer.bean.util.ConvertUtils;
import cn.leetechweb.summer.mvc.annotation.RequestParam;
import cn.leetechweb.summer.mvc.handler.InvokeHandler;
import cn.leetechweb.summer.mvc.mapping.ServletDescriptor;
import cn.leetechweb.summer.mvc.mapping.argument.ArgumentMapper;
import cn.leetechweb.summer.mvc.support.MethodInvokeResult;

import java.lang.reflect.Parameter;

/**
 * Project Name: summer
 * Create Time: 2020/11/17 22:14
 *
 * @author junyu lee
 **/
public class SimpleMethodInvoker extends AbstractMethodInvoker {

    @Override
    public MethodInvokeResult doInvoke(ServletDescriptor servletDescriptor, ArgumentMapper argumentMapper) {
        Parameter[] methodParameters = servletDescriptor.getParameters();
        Object[] args = new Object[servletDescriptor.getMethod().getParameterCount()];
        for (int i = 0; i < args.length; i++) {
            args[i] = resolveEach(methodParameters[i], argumentMapper);
        }

        // 执行前置切面
        for (InvokeHandler invokeResult : this.invokeHandlers) {
            invokeResult.preHandle(args, servletDescriptor);
        }

        MethodInvokeResult invokeResult = new MethodInvokeResult();
        Object result = servletDescriptor.invoke(args);

        // 执行后置切面
        for (InvokeHandler invokeHandler : this.invokeHandlers) {
            invokeHandler.postHandle(result, invokeResult);
        }

        return invokeResult;
    }

    /**
     * 根据argParameter的需求，在argumentMapper中寻找最佳的参数
     * @param argParameter 参数实体
     * @param argumentMapper 参数映射表
     * @return parameter对应的最佳参数
     */
    private Object resolveEach(Parameter argParameter, ArgumentMapper argumentMapper) {
        if (argParameter.isAnnotationPresent(RequestParam.class)) {
            String parameterName = argParameter.getAnnotation(RequestParam.class).value();
            Object[] params = argumentMapper.get(parameterName);
            if (params.length == 1) {
                return ConvertUtils.convert(argParameter.getType(), params[0]);
            }
            return params;
        }
        return null;
    }

}
