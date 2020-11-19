package cn.leetechweb.summer.mvc.mapping.argument;

import cn.leetechweb.summer.bean.util.Assert;
import cn.leetechweb.summer.mvc.exception.MissingArgumentException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 简易的Web参数查询器
 * 当一个request请求到来时，会实例化这个对象供请求使用
 * Project Name: summer
 * Create Time: 2020/11/18 0:17
 *
 * @author junyu lee
 **/
public class SimpleArgumentMapper implements ArgumentMapper {

    private final Map<String, List<ArgumentDescriptor>> descriptorMap = new ConcurrentHashMap<>(8);

    private String requestBody;

    @Override
    public void add(String parameterName, ArgumentDescriptor argumentDescriptor) {
        Assert.isNotNull(parameterName, "参数名不能为空");
        if (!descriptorMap.containsKey(parameterName)) {
            this.descriptorMap.put(parameterName, new ArrayList<>());
        }
        this.descriptorMap.get(parameterName).add(argumentDescriptor);
    }

    @Override
    public Object[] get(String parameterName) {
        List<ArgumentDescriptor> descriptors = this.descriptorMap.get(parameterName);
        if (descriptors == null) {
            throw new MissingArgumentException("参数缺失:{}", parameterName);
        }
        return descriptors.stream().map(ArgumentDescriptor::getArgumentValue).toArray();
    }

    @Override
    public String getRequestBody() {
        return this.requestBody;
    }

    @Override
    public void setRequestBody(String requestBody) {
        this.requestBody = requestBody;
    }
}
