package cn.leetechweb.summer.mvc.mapping;

import cn.leetechweb.summer.mvc.MvcUtils;
import cn.leetechweb.summer.mvc.exception.NoServletMappingException;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

/**
 * Project Name: summer
 * Create Time: 2020/11/17 21:36
 *
 * @author junyu lee
 **/
public final class MaxPrefixServletMapping extends AbstractServletMapping {

    @Override
    public ServletDescriptor getMapping(String urlPattern) {
        String[] segments = MvcUtils.getUrlSegments(urlPattern);
        Set<String> allPaths = getAllServletPaths();
        Set<String> copy = new HashSet<>(allPaths);
        for (int i = 0; i < segments.length; i++) {
            String segment = segments[i];
            for (String path : copy) {
                String[] pathSegments = this.servletDescriptorMap.get(path).getUrlSegments();
                if (i == pathSegments.length) {
                    allPaths.remove(path);
                    continue;
                }
                if (!pathSegments[i].equals(segment)) {
                    allPaths.remove(path);
                }
            }
        }
        String shortest = allPaths.stream().min(Comparator.comparingInt(String::length)).orElse(null);
        if (shortest == null) {
            throw new NoServletMappingException("没有{}的处理器", urlPattern);
        }
        ServletDescriptor descriptor = this.servletDescriptorMap.get(shortest);
        if (descriptor == null) {
            throw new NoServletMappingException("没有{}的处理器", urlPattern);
        }
        return descriptor;
    }

}
