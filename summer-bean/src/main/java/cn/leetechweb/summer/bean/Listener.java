package cn.leetechweb.summer.bean;

/**
 * Project Name: summer
 * Create Time: 2020/11/3 22:18
 *
 * @author junyu lee
 **/
public interface Listener<T> {
    /**
     * 监听者对data作出相应的接口
     * @param data 发布者发送的数据
     */
    void onEvent(T data);
}
