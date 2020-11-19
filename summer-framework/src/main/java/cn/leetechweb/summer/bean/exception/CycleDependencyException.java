package cn.leetechweb.summer.bean.exception;

/**
 * Project Name: summer
 * Create Time: 2020/11/4 18:52
 *
 * @author junyu lee
 **/
public class CycleDependencyException extends RuntimeException {
    public CycleDependencyException(String message) {
        super(message);
    }
}
