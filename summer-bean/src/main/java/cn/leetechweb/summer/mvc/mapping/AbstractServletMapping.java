package cn.leetechweb.summer.mvc.mapping;

import cn.leetechweb.summer.bean.util.Assert;
import cn.leetechweb.summer.mvc.support.HttpMethod;

import java.util.HashSet;
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

    protected Map<HttpMethod, Map<String, ServletDescriptor>> servletDescriptorMap = new ConcurrentHashMap<>(128);

    @Override
    public void addServletDescriptor(ServletDescriptor servletDescriptor, HttpMethod httpMethod) {
        Assert.isNotNull(servletDescriptor, "不能注册空的servlet");
        this.servletDescriptorMap.get(httpMethod).put(servletDescriptor.getMappingUrl(), servletDescriptor);
    }

    @Override
    public Set<String> getAllServletPaths(HttpMethod httpMethod) {
        return new HashSet<>(this.servletDescriptorMap.get(httpMethod).keySet());
    }

    protected AbstractServletMapping() {
        for (HttpMethod httpMethod : HttpMethod.values()) {
            this.servletDescriptorMap.put(httpMethod, new ConcurrentHashMap<>());
        }
    }
}
