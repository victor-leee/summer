package cn.leetechweb.summer.mvc.support.method.result;

import cn.leetechweb.summer.mvc.Constant;
import cn.leetechweb.summer.mvc.annotation.Restful;
import cn.leetechweb.summer.mvc.mapping.ServletDescriptor;
import cn.leetechweb.summer.mvc.view.InternalView;

import java.io.File;

/**
 * Project Name: summer
 * Create Time: 2020/11/22 15:29
 *
 * @author junyu lee
 **/
public final class MethodInvokeResultFactory {

    /**
     * @param methodResult 方法执行结果
     * @return 方法结果
     */
    public static MethodInvokeResult wrapResult(Object methodResult, ServletDescriptor servletDescriptor) {
        if (isFile(methodResult)) {
            return new FileMethodInvokeResult((File) methodResult);
        }else if (isForward(methodResult, servletDescriptor)) {
            return new ForwardMethodInvokeResult(((String) methodResult).substring(
                    Constant.FORWARD_RETURN_PREFIX.length()
            ));
        }else if (isRedirect(methodResult, servletDescriptor)) {
            return new RedirectMethodInvokeResult(((String) methodResult).substring(
                    Constant.REDIRECT_RETURN_PREFIX.length()
            ));
        }else if (isInternalResource(methodResult, servletDescriptor)) {
            return new InternalResourceResult((InternalView) methodResult);
        }else if (isJspResource(methodResult, servletDescriptor)) {
            InternalView jspView = new InternalView();
            jspView.setViewName((String) methodResult);
            return new InternalResourceResult(jspView);
        }else if (isRestfulView(servletDescriptor)){
            return new JsonMethodInvokeResult(methodResult);
        }
        throw new IllegalArgumentException("发生错误");
    }

    private static boolean isFile(Object methodResult) {
        return methodResult instanceof File;
    }

    private static boolean isForward(Object resultObject, ServletDescriptor descriptor) {
        if (resultObject instanceof String) {
            if (!descriptor.isAnnotationPresentAnyway(Restful.class)) {
                String viewDest = (String) resultObject;
                return viewDest.startsWith(Constant.FORWARD_RETURN_PREFIX);
            }
        }
        return false;
    }

    private static boolean isRedirect(Object methodResult, ServletDescriptor descriptor) {
        if (methodResult instanceof String) {
            if (!descriptor.isAnnotationPresentAnyway(Restful.class)) {
                String reDest = (String) methodResult;
                return reDest.startsWith(Constant.REDIRECT_RETURN_PREFIX);
            }
        }
        return false;
    }

    private static boolean isInternalResource(Object methodResult, ServletDescriptor descriptor) {
        if (methodResult instanceof InternalView) {
            return !descriptor.isAnnotationPresentAnyway(Restful.class);
        }
        return false;
    }

    private static boolean isJspResource(Object methodResult, ServletDescriptor descriptor) {
        if (methodResult instanceof String) {
            return !descriptor.isAnnotationPresentAnyway(Restful.class);
        }
        return false;
    }

    private static boolean isRestfulView(ServletDescriptor servletDescriptor) {
        return servletDescriptor.isAnnotationPresentAnyway(Restful.class);
    }

}
