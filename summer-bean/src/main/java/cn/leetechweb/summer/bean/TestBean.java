package cn.leetechweb.summer.bean;

/**
 * Project Name: summer
 * Create Time: 2020/11/4 1:50
 *
 * @author junyu lee
 **/
public class TestBean {
    private final String first;
    private final Float sec;
    private final String thr;

    public TestBean(String first, Float sec, String thr) {
        this.first = first;
        this.sec = sec;
        this.thr = thr;
    }

    public String getFirst() {
        return first;
    }

    public Float getSec() {
        return sec;
    }

    public String getThr() {
        return thr;
    }

    @Override
    public String toString() {
        return "TestBean{" +
                "first='" + first + '\'' +
                ", sec=" + sec +
                ", thr='" + thr + '\'' +
                '}';
    }
}
