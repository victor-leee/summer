package cn.leetechweb.summer.mvc.mapping.argument;

/**
 * Project Name: summer
 * Create Time: 2020/11/17 22:11
 *
 * @author junyu lee
 **/
public final class ArgumentDescriptor {

    /**
     * 参数类型
     */
    private String argumentType;

    /**
     * 参数值
     */
    private Object argumentValue;


    public void setArgumentType(String argumentType) {
        this.argumentType = argumentType;
    }


    public void setArgumentValue(Object argumentValue) {
        this.argumentValue = argumentValue;
    }


    public Object getArgumentValue() {
        return argumentValue;
    }


    public String getArgumentType() {
        return argumentType;
    }
}
