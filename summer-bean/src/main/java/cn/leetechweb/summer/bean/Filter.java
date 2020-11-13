package cn.leetechweb.summer.bean;

/**
 * Project Name: summer
 * Create Time: 2020/11/13 21:25
 *
 * @author junyu lee
 **/
public interface Filter<T> {

    /**
     * 对数据作过滤
     * @param data 数据
     * @return 是否留下
     */
    boolean stay(T data);

}
