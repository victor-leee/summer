package cn.leetechweb.summer.bean;

/**
 * Project Name: summer
 * Create Time: 2020/11/3 22:16
 *
 * @author junyu lee
 **/
public interface Publisher<T> {
    /**
     * 发布者像监听者发送数据
     * @param data 发布的数据
     */
    void publish(T data);

    /**
     * 新增监听者
     * @param listener 监听者
     */
    void addListener(Listener<T> listener);
}
