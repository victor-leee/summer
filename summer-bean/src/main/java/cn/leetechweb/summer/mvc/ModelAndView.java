package cn.leetechweb.summer.mvc;

/**
 * Project Name: summer
 * Create Time: 2020/11/18 13:01
 *
 * @author junyu lee
 **/
public final class ModelAndView {

    private ModelMap modelMap;

    private View view;

    public void setModelMap(ModelMap modelMap) {
        this.modelMap = modelMap;
    }

    public void setView(View view) {
        this.view = view;
    }

    public ModelMap getModelMap() {
        return modelMap;
    }

    public View getView() {
        return view;
    }

    public void addParameter(String name, Object val) {
        this.modelMap.add(name, val);
    }
}
