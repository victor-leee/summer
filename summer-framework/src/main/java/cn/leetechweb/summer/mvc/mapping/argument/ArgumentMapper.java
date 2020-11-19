package cn.leetechweb.summer.mvc.mapping.argument;

/**
 * 参数mapper
 * 用于获取或者添加由页面传过来的参数
 * Project Name: summer
 * Create Time: 2020/11/17 22:06
 *
 * @author junyu lee
 **/
public interface ArgumentMapper {

    /**
     * 向参数映射表中新增键值对
     * @param parameterName 参数名
     * @param argumentDescriptor 参数值描述
     */
    void add(String parameterName, ArgumentDescriptor argumentDescriptor);

    /**
     * 根据参数名获取参数映射表中的参数值
     * @param parameterName 参数名
     * @return 获取参数映射表中的参数值列表
     */
    Object[] get(String parameterName);

    /**
     * 返回请求体参数
     * @return 请求体参数数据
     */
    String getRequestBody();

    /**
     * 设置请求体参数
     * @param requestBody 请求体
     */
    void setRequestBody(String requestBody);

//    /**
//     * 获取type对应的对象
//     * 用于一些特殊的对象获取
//     * 例如HttpServletRequest
//     * @param type 类类型
//     * @return 类对应的对象
//     */
//    Object getArgumentByClassType(Class<?> type);

}
