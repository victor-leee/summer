package cn.leetechweb.summer.mvc.view;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

/**
 * Project Name: summer
 * Create Time: 2020/11/18 14:41
 *
 * @author junyu lee
 **/
public abstract class AbstractView implements View {

    protected Logger logger = Logger.getLogger(this.getClass().getName());

    protected String viewName;

    protected Map<String, Object> paramMap = new ConcurrentHashMap<>(16);

    @Override
    public void setViewName(String viewName) {
        this.viewName = viewName;
    }

    @Override
    public void append(String name, Object val) {
        this.paramMap.put(name, val);
    }
}
