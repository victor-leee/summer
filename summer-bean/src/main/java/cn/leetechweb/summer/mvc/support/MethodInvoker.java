package cn.leetechweb.summer.mvc.support;

import cn.leetechweb.summer.mvc.mapping.argument.ArgumentMapper;
import cn.leetechweb.summer.mvc.mapping.ServletDescriptor;

/**
 * 方法执行接口
 * 用于执行Mvc的方法
 * Project Name: summer
 * Create Time: 2020/11/17 22:03
 *
 * @author junyu lee
 **/
public interface MethodInvoker {

    /**
     * 根据servlet描述和参数映射表执行对应的方法并获取返回结果
     * @param servletDescriptor servlet描述
     * @param argumentMapper 参数映射表
     * @return 执行结果
     */
    MethodInvokeResult doInvoke(ServletDescriptor servletDescriptor, ArgumentMapper argumentMapper);

}
