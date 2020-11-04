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
    private final TestBean thr;

    public TestBean(String first, Float sec, TestBean ref) {
        this.first = first;
        this.sec = sec;
        this.thr = ref;
    }

    public String getFirst() {
        return first;
    }

    public Float getSec() {
        return sec;
    }

    public TestBean getThr() {
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
