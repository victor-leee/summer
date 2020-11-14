package cn.leetechweb.summer.bean.loader;

import cn.leetechweb.summer.bean.annotation.Summer;
import cn.leetechweb.summer.bean.annotation.reader.Reader;
import cn.leetechweb.summer.bean.definition.impl.AnnotationBeanDefinitionImpl;
import cn.leetechweb.summer.bean.definition.impl.AnnotationBeanDefinitionParameter;
import cn.leetechweb.summer.bean.exception.AnnotationContainerInitializationException;
import cn.leetechweb.summer.bean.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Project Name: summer
 * Create Time: 2020/11/4 23:46
 *
 * @author junyu lee
 **/
public final class AnnotationConfigBeanDefinitionLoader extends BeanDefinitionLoader {

    /**
     * summer容器上下文加载的主类，用于获取容器元信息
     */
    private final Class<?> mainClass;

    /**
     * 将被summer容器管理的类
     */
    private final Set<Class<?>> loadingClasses;

    /**
     * 读取类文件的读取器
     */
    private final Reader classReader;


    /**
     * 要扫描的base packages
     */
    private String[] basePackages;

    public AnnotationConfigBeanDefinitionLoader(Class<?> mainClass, Reader reader) {
        this.mainClass = mainClass;
        this.loadingClasses = new HashSet<>(16);
        this.classReader = reader;
        reader.setClassSet(this.loadingClasses);
    }

    @Override
    public void load() throws ClassNotFoundException {
        this.loadHelper();
    }

    private void loadHelper() throws ClassNotFoundException {
        if (mainClass.isAnnotationPresent(Summer.class)) {
            String[] basePackages = mainClass.getAnnotation(Summer.class).value();
            if (basePackages.length == 0) {
                throw new AnnotationContainerInitializationException("basePackages长度为0");
            }
            for (String basePackage : basePackages) {
                this.classReader.read(basePackage);
            }

            constructClassMetaData();

            publish(beanRegistry);
        }
    }

    /**
     * 构建loadingClasses所有类的元信息
     */
    private void constructClassMetaData() {
        for (Class<?> clazz : loadingClasses) {

            Map<String, AnnotationBeanDefinitionParameter> parameterMap = new HashMap<>(256);

            getFieldsParameters(parameterMap, clazz);

            getMethodsParameters(parameterMap, clazz);

            AnnotationBeanDefinitionImpl beanDefinition = new AnnotationBeanDefinitionImpl(
                    parameterMap, clazz.getSimpleName(), clazz.getName()
            );
            beanRegistry.addBeanDefinition(beanDefinition);
        }
    }

    private void getFieldsParameters(Map<String, AnnotationBeanDefinitionParameter> parameterMap,
                                     Class<?> clazz) {
        List<Field> withAutowiredFields = ReflectionUtils.getFieldsNeedAutowired(clazz);
        for (Field field : withAutowiredFields) {
            Class<?> fieldClass = field.getType();
            AnnotationBeanDefinitionParameter parameter =
                    new AnnotationBeanDefinitionParameter(fieldClass.getSimpleName(), fieldClass);
            parameterMap.put(fieldClass.getSimpleName(), parameter);
        }
    }

    private void getMethodsParameters(Map<String, AnnotationBeanDefinitionParameter> parameterMap,
                                      Class<?> clazz) {
        List<Method> methods = ReflectionUtils.getMethodsNeedAutowired(clazz);
        for (Method method : methods) {
            Class<?>[] paramTypes = method.getParameterTypes();
            for (Class<?> type : paramTypes) {
                AnnotationBeanDefinitionParameter parameter =
                        new AnnotationBeanDefinitionParameter(type.getSimpleName(), type);
                parameterMap.put(type.getSimpleName(), parameter);
            }
        }
    }

}
