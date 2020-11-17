package cn.leetechweb.summer.mvc.mapping;

import cn.leetechweb.summer.bean.util.Assert;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Project Name: summer
 * Create Time: 2020/11/17 21:36
 *
 * @author junyu lee
 **/
public abstract class AbstractServletMapping implements ServletMapping {

    protected Map<String, ServletDescriptor> servletDescriptorMap = new ConcurrentHashMap<>(128);

    @Override
    public void addServletDescriptor(ServletDescriptor servletDescriptor) {
        Assert.isNotNull(servletDescriptor, "不能注册空的servlet");
        this.servletDescriptorMap.put(servletDescriptor.getMappingUrl(), servletDescriptor);
    }

    @Override
    public Set<String> getAllServletPaths() {
        return this.servletDescriptorMap.keySet();
    }
}
