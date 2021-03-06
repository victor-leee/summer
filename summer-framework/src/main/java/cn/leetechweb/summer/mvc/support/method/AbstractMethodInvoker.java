package cn.leetechweb.summer.mvc.support.method;

import cn.leetechweb.summer.bean.ContainerAware;
import cn.leetechweb.summer.bean.factory.BeanFactory;
import cn.leetechweb.summer.mvc.handler.InvokeHandler;
import cn.leetechweb.summer.mvc.json.FastJsonParse;
import cn.leetechweb.summer.mvc.json.JsonParse;

import java.util.ArrayList;
import java.util.List;

/**
 * Project Name: summer
 * Create Time: 2020/11/18 0:29
 *
 * @author junyu lee
 **/
public abstract class AbstractMethodInvoker implements MethodInvoker, ContainerAware {

    protected BeanFactory beanFactory;

    protected List<InvokeHandler> invokeHandlers;

    protected JsonParse jsonParse;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
        this.initHandlers();
        this.jsonParse = beanFactory.getBean("json", FastJsonParse.class);
    }

    private void initHandlers() {
        this.invokeHandlers = new ArrayList<>();
        for (Object bean : this.beanFactory.getBeans()) {
            if (bean instanceof InvokeHandler) {
                this.invokeHandlers.add((InvokeHandler) bean);
            }
        }
    }
}
