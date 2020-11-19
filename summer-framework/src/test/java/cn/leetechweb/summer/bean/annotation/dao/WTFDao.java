package cn.leetechweb.summer.bean.annotation.dao;

/**
 * Project Name: summer
 * Create Time: 2020/11/15 13:20
 *
 * @author junyu lee
 **/
public class WTFDao {

    private InnerBean innerBean;

    @Override
    public String toString() {
        return "WTFDao{" +
                "innerBean=" + innerBean +
                '}';
    }

    WTFDao(InnerBean innerBean) {
        this.innerBean = innerBean;
    }

}
