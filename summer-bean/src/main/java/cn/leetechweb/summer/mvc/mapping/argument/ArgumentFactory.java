package cn.leetechweb.summer.mvc.mapping.argument;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Project Name: summer
 * Create Time: 2020/11/18 0:40
 *
 * @author junyu lee
 **/
public final class ArgumentFactory {

    public static ArgumentMapper getArgumentMapper(HttpServletRequest servletRequest) {
        Map<String, String[]> parameterMap = servletRequest.getParameterMap();
        ArgumentMapper argumentMapper = new SimpleArgumentMapper();
        parameterMap.forEach((name, parameter) -> {
            for (String p : parameter) {
                argumentMapper.add(name, new ArgumentDescriptor("text", p));
            }
        });
        return argumentMapper;
    }

}
