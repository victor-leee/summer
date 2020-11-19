package cn.leetechweb.summer.mvc.json;

/**
 * JSON解析接口
 * Project Name: summer
 * Create Time: 2020/11/18 21:31
 *
 * @author junyu lee
 **/
public interface JsonParse {

    /**
     * 解析text的JSON数据，并转换为clazz类型的对象
     * @param text JSON文本
     * @param clazz 要转换到的类别信息
     * @param <T> 类别泛型
     * @return 转换后的对象
     */
    <T> T parse(String text, Class<T> clazz);

    /**
     * 将object使用JSON格式描述
     * @param object 对象
     * @return JSON描述的对象
     */
    String toText(Object object);

}
