package cn.leetechweb.summer.bean.loader;

import cn.leetechweb.summer.bean.annotation.Component;
import cn.leetechweb.summer.bean.annotation.Summer;
import cn.leetechweb.summer.bean.annotation.Value;
import cn.leetechweb.summer.bean.annotation.reader.Reader;
import cn.leetechweb.summer.bean.definition.BeanDefinitionParameter;
import cn.leetechweb.summer.bean.definition.impl.AnnotationBeanDefinitionImpl;
import cn.leetechweb.summer.bean.definition.impl.AnnotationBeanDefinitionParameter;
import cn.leetechweb.summer.bean.exception.AnnotationContainerInitializationException;
import cn.leetechweb.summer.bean.exception.BeanNameAlreadyExistsException;
import cn.leetechweb.summer.bean.util.BeanUtils;
import cn.leetechweb.summer.bean.util.ReflectionUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
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

            this.loadingClasses.removeIf(
                    loadingClass -> !loadingClass.isAnnotationPresent(Component.class)
            );

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

            // 寻找需要注入的字段
            getFieldsParameters(parameterMap, clazz);

            // 寻找需要注入的方法
            getMethodsParameters(parameterMap, clazz);

            // 寻找需要注入的构造函数的参数
            getConstructorParams(parameterMap, clazz);

            AnnotationBeanDefinitionImpl beanDefinition = new AnnotationBeanDefinitionImpl(
                    parameterMap, BeanUtils.getBeanName(clazz), clazz.getName()
            );
            beanRegistry.addBeanDefinition(beanDefinition);
        }
    }

    private void getConstructorParams(Map<String, AnnotationBeanDefinitionParameter> parameterMap,
                                      Class<?> clazz) {
        if (clazz.getDeclaredConstructors().length != 1) {
            throw new AnnotationContainerInitializationException("构造函数只能有1个");
        }
        Constructor<?> ctor = clazz.getDeclaredConstructors()[0];
        Parameter[] ctorParams = ctor.getParameters();
        for (Parameter parameter : ctorParams) {
            String parameterBeanName = BeanUtils.getBeanName(parameter);
            AnnotationBeanDefinitionParameter beanParam = new AnnotationBeanDefinitionParameter(
                    parameterBeanName
            );
            put(parameterBeanName, beanParam, parameterMap);
        }
    }

    private void getFieldsParameters(Map<String, AnnotationBeanDefinitionParameter> parameterMap,
                                     Class<?> clazz) {
        List<Field> withAutowiredFields = ReflectionUtils.getFieldsNeedAutowired(clazz);
        List<Field> withValuesFields = ReflectionUtils.getFieldsNeedInjectionValue(clazz);

        // 所有需要自动bean依赖注入的字段,也就是使用了@Autowired的字段或@Resource的字段
        for (Field field : withAutowiredFields) {
            String fieldBeanName = BeanUtils.getBeanName(field);
            AnnotationBeanDefinitionParameter parameter = new AnnotationBeanDefinitionParameter(
                    fieldBeanName
            );
            put(fieldBeanName, parameter, parameterMap);
        }

        // 所有需要常量注入的字段，也就是使用了@Value的字段
        for (Field field : withValuesFields) {
            String annotationValue = field.getAnnotation(Value.class).value();
            AnnotationBeanDefinitionParameter parameter =
                    new AnnotationBeanDefinitionParameter(field.getName(), annotationValue);
            put(parameter.getParameterName(), parameter, parameterMap);
        }
    }

    private void getMethodsParameters(Map<String, AnnotationBeanDefinitionParameter> parameterMap,
                                      Class<?> clazz) {
        List<Method> methods = ReflectionUtils.getMethodsNeedAutowired(clazz);
        List<Method> producingBeanMethods = ReflectionUtils.getMethodsProducingBeans(clazz);
        methods.addAll(producingBeanMethods);
        for (Method method : methods) {
            Parameter[] parameters = method.getParameters();
            for (Parameter parameter : parameters) {
                String parameterBeanName = BeanUtils.getBeanName(parameter);
                AnnotationBeanDefinitionParameter beanParam = new AnnotationBeanDefinitionParameter(
                        parameterBeanName
                );
                put(parameterBeanName, beanParam, parameterMap);
            }
        }
    }

    private void put(String beanName, AnnotationBeanDefinitionParameter parameter,
                     Map<String, AnnotationBeanDefinitionParameter> parameterMap) {
        if (parameterMap.containsKey(beanName)) {
            throw new BeanNameAlreadyExistsException("bean名称:{}已经存在", beanName);
        }
        parameterMap.put(beanName, parameter);
    }

}
