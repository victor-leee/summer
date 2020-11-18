package cn.leetechweb.summer.mvc.view;

import cn.leetechweb.summer.mvc.Constant;
import cn.leetechweb.summer.mvc.json.FastJsonParse;
import cn.leetechweb.summer.mvc.json.JsonParse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用于处理restful风格的请求，将返回内容包装到json中返回
 * Project Name: summer
 * Create Time: 2020/11/18 21:27
 *
 * @author junyu lee
 **/
public class RestfulJsonView extends AbstractView {

    @Override
    public void render(HttpServletRequest request, HttpServletResponse response) {
        JsonParse jsonParse = new FastJsonParse();
        String[] params = get();
        String writeResult;
        if (params.length == 1 && View.SINGLE_OBJECT_TAG.equals(params[0])) {
            // 如果只有一个默认对象的情况下
            writeResult = jsonParse.toText(paramMap.get(params[0]));
        }else {
            writeResult = jsonParse.toText(paramMap);
        }
        response.setHeader(Constant.CONTENT_TYPE, Constant.CONTENT_TYPE_JSON);
        response.setCharacterEncoding(Constant.DEFAULT_CHARACTER_SET);
        try {
            response.getWriter().write(writeResult);
        }catch (Exception e) {
            logger.warning(e.getMessage());
        }
    }

}
