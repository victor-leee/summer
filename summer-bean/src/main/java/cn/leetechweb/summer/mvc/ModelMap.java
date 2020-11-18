package cn.leetechweb.summer.mvc;

/**
 * Project Name: summer
 * Create Time: 2020/11/18 13:02
 *
 * @author junyu lee
 **/
public interface ModelMap {

    /**
     * 向返回结果映射表中新增键值对
     * @param paramName 参数名
     * @param val 参数值
     */
    void add(String paramName, Object val);

    /**
     * 根据参数名获取对应的参数值
     * @param paramName 参数名
     * @return 参数值
     */
    Object get(String paramName);

}
