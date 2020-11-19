package cn.leetechweb.summer.mvc.view;

import cn.leetechweb.summer.bean.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

/**
 * Project Name: summer
 * Create Time: 2020/11/18 14:40
 *
 * @author junyu lee
 **/
public class JspView extends AbstractView {

    @Override
    public void render(HttpServletRequest request, HttpServletResponse response) {
        paramMap.forEach(request::setAttribute);
        try {
            request.getRequestDispatcher(this.viewName).forward(request, response);
        }catch (Exception e) {
            logger.severe(Arrays.toString(e.getStackTrace()));
            logger.severe(StringUtils.format("JSP视图渲染发生错误:{}", false, e.getMessage()));
        }
    }

}
