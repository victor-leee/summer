package cn.leetechweb.summer.mvc.support;

/**
 * Project Name: summer
 * Create Time: 2020/11/18 14:28
 *
 * @author junyu lee
 **/
public enum HttpStatus {

    /**
     * 响应成功
     */
    OK(200),

    /**
     * 资源未找到
     */
    NOT_FOUND(404),

    /**
     * 服务器发生内部错误
     */
    INTERNAL_SERVER_ERROR(500),

    /**
     * 重定向
     */
    REDIRECT(302);

    /**
     * 对应的响应状态码
     */
    private final int value;

    HttpStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
