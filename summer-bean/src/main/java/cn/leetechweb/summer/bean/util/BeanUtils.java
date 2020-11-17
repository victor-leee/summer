package cn.leetechweb.summer.bean.util;

import cn.leetechweb.summer.bean.Constant;
import cn.leetechweb.summer.bean.annotation.Bean;
import cn.leetechweb.summer.bean.annotation.Component;
import cn.leetechweb.summer.bean.annotation.Resource;
import cn.leetechweb.summer.bean.exception.AnnotationContainerInitializationException;

import java.lang.reflect.*;
import java.util.*;

/**
 * 对于Bean的工具类，提供Bean的实例化等工具
 * Project Name: summer
 * Create Time: 2020/11/4 1:13
 *
 * @author junyu lee
 **/
public abstract class BeanUtils {

    /**
     * 使用构造函数创建bean
     * @param clazz 实例化的类对象
     * @param constructorArgs 构造函数参数map
     * @return 实例化的bean
     */
    public static Object createBeanByConstructor(Class<?> clazz, Map<String, Object> constructorArgs) {
        try {
            Constructor<?>[] constructors = clazz.getDeclaredConstructors();

            ConstructorBindingStrategy bindingStrategy = new NamedBeanConstructorBidingStrategy(constructors,
                    constructorArgs);
            Constructor<?> constructor = bindingStrategy.getConstructor();
            ReflectionUtils.makeAccessible(constructor);
            return bindingStrategy.initializeObject(constructor, constructorArgs);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Object createBeanByConstructor(String beanPath, Map<String,Object> constructorArgs) {
        try {
            Class<?> clazz = Thread.currentThread().getContextClassLoader().loadClass(beanPath);
            return createBeanByConstructor(clazz, constructorArgs);
        }catch (Exception e) {
            e.printStackTrace();
        }
        throw new RuntimeException();
    }

    /**
     * 按照Parameter的类型，对args作转换，通常来说args是字符串数组
     * @param parameters 参数列表
     * @param args 实际的参数列表
     */
    public static void convertParametersToItsActualTypes(Parameter[] parameters, Object[] args) {
        for (int i = 0; i < args.length; i++) {
            Class<?> type = parameters[i].getType();
            args[i] = ConvertUtils.convert(type, args[i]);
        }
    }

    /**
     * 构造函数绑定策略，根据不同的策略返回构造函数，同时提供实例化的接口
     * 如果需要其他策略，override此类并提供get()方法即可
     * 注意如果返回的Constructor为空会抛出RuntimeException错误
     */
    static abstract class ConstructorBindingStrategy {

        protected final Constructor<?>[] constructors;

        protected final Map<String, Object> params;

        protected ConstructorBindingStrategy(Constructor<?>[] constructors, Map<String, Object> params) {
            this.constructors = constructors;
            this.params = params;
        }

        /**
         * 返回构造函数，如果构造函数为空抛出错误
         * @return 构造函数
         */
        Constructor<?> getConstructor() {
            Constructor<?> constructor = this.get();
            if (constructor == null) {
                throw new RuntimeException("获取构造函数发生错误，请检查参数名是否设置正确，无法找到对应构造函数");
            }
            return constructor;
        }

        /**
         * 根据构造函数初始化对象
         * @param constructor 构造函数
         * @param constructorArgs 构造函数参数
         * @return 初始化后的Bean
         */
        abstract Object initializeObject(Constructor<?> constructor, Map<String, Object> constructorArgs);

        /**
         * 按照某种策略获取构造函数
         * @return 构造函数
         */
        protected abstract Constructor<?> get();
    }

    static class NamedBeanConstructorBidingStrategy extends ConstructorBindingStrategy {

        protected NamedBeanConstructorBidingStrategy(Constructor<?>[] constructors, Map<String, Object> params) {
            super(constructors, params);
        }

        @Override
        Object initializeObject(Constructor<?> constructor, Map<String, Object> args) {
            List<Object> paramValues = new ArrayList<>();
            Parameter[] parameters = constructor.getParameters();
            for (Parameter parameter : parameters) {
                String beanName = getBeanName(parameter);
                Object thisParam = null;
                if (params.containsKey(beanName)) {
                    thisParam = params.get(beanName);
                }
                if (thisParam == null) {
                    thisParam = params.values()
                            .stream()
                            .filter(object -> object.getClass().equals(parameter.getType()))
                            .findAny()
                            .orElse(null);
                }
                paramValues.add(thisParam);
            }
            try {
                return constructor.newInstance(paramValues.toArray());
            }catch (Exception e) {
                e.printStackTrace();
                throw new AnnotationContainerInitializationException("无法通过构造函数创建bean");
            }
        }

        @Override
        protected Constructor<?> get() {
            if (constructors.length != 1) {
                throw new AnnotationContainerInitializationException("检测到容器中某个bean的构造函数过多");
            }
            return constructors[0];
        }
    }

    /**
     * 使用规定好的参数顺序绑定参数，注意构造函数的长度不能有重复的
     */
    static final class OrderedConstructorBinding extends ConstructorBindingStrategy {

        private final int count;

        @Override
        Object initializeObject(Constructor<?> constructor, Map<String, Object> constructorArgs) {
            Object[] paramValues = constructorArgs.entrySet().stream()
                    .sorted(Map.Entry.comparingByKey())
                    .map(Map.Entry::getValue).toArray();
            Object result;
            Parameter[] parameters = constructor.getParameters();
            BeanUtils.convertParametersToItsActualTypes(parameters, paramValues);
            try {
                result = constructor.newInstance(paramValues);
            }catch (Exception e) {
                throw new RuntimeException("初始化Bean失败");
            }
            return result;
        }

        @Override
        protected Constructor<?> get() {
            for (Constructor<?> constructor : super.constructors) {
                if (constructor.getParameterCount() == this.count) {
                    return constructor;
                }
            }
            return null;
        }

        OrderedConstructorBinding(Constructor<?>[] constructors, Map<String, Object> params) {
            super(constructors, params);
            this.count = params.size();
        }
    }

    public static Class<?> loadClassFromPath(String beanPath) {
        try {
            return Thread.currentThread().getContextClassLoader().loadClass(beanPath);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 返回该类对象的bean名称
     * @param clazz 类对象
     * @return 该类对象的bean名称
     */
    public static String getBeanName(Class<?> clazz) {
        String beanName = Constant.EMPTY_STRING;
        if (clazz.isAnnotationPresent(Component.class)) {
            beanName = clazz.getAnnotation(Component.class).name();
        }
        if (!Constant.EMPTY_STRING.equals(beanName)) {
            return beanName;
        }
        return clazz.getSimpleName();
    }

    /**
     * 返回规则：
     * 如果该字段带有@Resource注解，则使用@Resource注解上指定的beanName
     * 否则获取返回类型的beanName
     * @param field 字段
     * @return 该需要注入的字段的beanName
     */
    public static String getBeanName(Field field) {
        if (field.isAnnotationPresent(Resource.class)) {
            return field.getAnnotation(Resource.class).name();
        }
        return getBeanName(field.getType());
    }

    public static String getBeanName(Parameter parameter) {
        if (parameter.isAnnotationPresent(Resource.class)) {
            return parameter.getDeclaredAnnotation(Resource.class).name();
        }
        return getBeanName(parameter.getType());
    }

    public static String getBeanName(Method method) {
        if (method.isAnnotationPresent(Bean.class)) {
            String beanName = method.getAnnotation(Bean.class).name();
            if (Constant.EMPTY_STRING.equals(beanName)) {
                return getBeanName(method.getReturnType());
            }
            return beanName;
        }
        throw new IllegalArgumentException(StringUtils.format("方法名：{}的返回值似乎没有标记为@Bean", false, method.getName()));
    }

}
