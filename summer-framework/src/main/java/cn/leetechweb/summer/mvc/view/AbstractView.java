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

    @Override
    public String[] get() {
        return this.paramMap.keySet().toArray(new String[0]);
    }

    protected Object getDefault() {
        if (paramMap.size() != 1) {
            return null;
        }
        return paramMap.get(View.SINGLE_OBJECT_TAG);
    }

    protected <T> T getDefault(Class<T> cast) {
        Object get = getDefault();
        if (get != null) {
            return (T) get;
        }
        return null;
    }

    public String getViewName() {
        return viewName;
    }
}
