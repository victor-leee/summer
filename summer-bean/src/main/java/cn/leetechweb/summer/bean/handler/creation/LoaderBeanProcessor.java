package cn.leetechweb.summer.bean.handler.creation;

import cn.leetechweb.summer.bean.loader.Loader;

/**
 * Project Name: summer
 * Create Time: 2020/11/17 15:55
 *
 * @author junyu lee
 **/
public class LoaderBeanProcessor extends AbstractBeanPostProcessor {
    @Override
    public void invoke() {
        for (Object bean : this.beanFactory.getBeans()) {
            if (bean instanceof Loader) {
                try {
                    ((Loader) bean).load();
                }catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
