package cn.leetechweb.summer.mvc.json;

import cn.leetechweb.summer.bean.annotation.Component;
import com.alibaba.fastjson.JSON;

/**
 * Project Name: summer
 * Create Time: 2020/11/18 21:48
 *
 * @author junyu lee
 **/
@Component(name = "json")
public class FastJsonParse implements JsonParse {


    @Override
    public <T> T parse(String text, Class<T> clazz) {
        return JSON.parseObject(text, clazz);
    }

    @Override
    public String toText(Object object) {
        return JSON.toJSONString(object);
    }
}
