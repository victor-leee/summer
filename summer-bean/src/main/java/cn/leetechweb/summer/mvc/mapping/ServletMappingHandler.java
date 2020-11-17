package cn.leetechweb.summer.mvc.mapping;

import cn.leetechweb.summer.bean.ContainerAware;
import cn.leetechweb.summer.bean.Listener;
import cn.leetechweb.summer.bean.Publisher;
import cn.leetechweb.summer.bean.factory.BeanFactory;
import cn.leetechweb.summer.bean.handler.creation.PostCreationProcessor;
import cn.leetechweb.summer.mvc.DispatcherServlet;
import cn.leetechweb.summer.mvc.MvcUtils;
import cn.leetechweb.summer.mvc.annotation.Controller;

/**
 * 将bean工厂中的Controller类做一下初始化工作
 * 提供基本的映射关系
 * Project Name: summer
 * Create Time: 2020/11/17 17:17
 *
 * @author junyu lee
 **/
public class ServletMappingHandler implements ContainerAware, PostCreationProcessor, Publisher<ServletMapping> {

    /**
     * Bean工厂
     * 用于处理映射关系
     * 扫描所有的bean，并初始化映射信息
     */
    private BeanFactory beanFactory;

    /**
     * 映射信息监听器，ServletMappingHandler将映射信息处理完毕后发布信息
     */
    private Listener<ServletMapping> servletMappingListener;

    @Override
    public void setBeanFactory(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    @Override
    public void invoke() {
        this.servletMappingListener = this.beanFactory.getBean(DispatcherServlet.SERVLET_NAME, DispatcherServlet.class);
        ServletMapping servletMapping = new SimpleServletMapping();
        for (Object bean : this.beanFactory.getBeans()) {
            Class<?> beanType = bean.getClass();
            if (beanType.isAnnotationPresent(Controller.class)) {
                // 处理这个bean的所有映射关系
                String baseMappingUrl = MvcUtils.getBaseMappingUrl(beanType);

            }
        }
    }

    @Override
    public void publish(ServletMapping data) {
        this.servletMappingListener.onEvent(data);
    }

    @Override
    public void addListener(Listener<ServletMapping> listener) {

    }
}
