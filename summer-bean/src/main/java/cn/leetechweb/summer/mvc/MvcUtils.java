package cn.leetechweb.summer.mvc;

import cn.leetechweb.summer.mvc.annotation.Controller;
import cn.leetechweb.summer.mvc.annotation.Mapping;

/**
 * Project Name: summer
 * Create Time: 2020/11/17 17:30
 *
 * @author junyu lee
 **/
public abstract class MvcUtils {

    /**
     * 根据@Mapping注解返回该bean的映射路径
     * 如果该bean继承于其他bean，则返回嵌套路径
     * @param beanType bean类型
     * @return 映射到该bean的映射路径
     */
    public static String getBaseMappingUrl(Class<?> beanType) {
        if (beanType.isAnnotationPresent(Controller.class)) {
            String mappingUrl = getBaseMappingUrl(beanType.getSuperclass());
            if (!mappingUrl.endsWith(Constant.URL_SEPARATOR)) {
                mappingUrl = mappingUrl + Constant.URL_SEPARATOR;
            }
            if (beanType.isAnnotationPresent(Mapping.class)) {
                String currentMappingUrl = beanType.getAnnotation(Mapping.class).path();
                mappingUrl = mappingUrl + currentMappingUrl;
            }
            return mappingUrl;
        }
        return "";
    }

}
