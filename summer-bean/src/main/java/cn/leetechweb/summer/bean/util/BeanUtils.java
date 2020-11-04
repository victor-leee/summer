package cn.leetechweb.summer.bean.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Parameter;
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
     * @param beanClassPath bean的类路径
     * @param constructorArgs 构造函数参数map
     * @return 实例化的bean
     */
    public static Object createBeanByConstructor(String beanClassPath, Map<String, Object> constructorArgs) {
        try {
            Class<?> clazz = Thread.currentThread().getContextClassLoader().loadClass(beanClassPath);
            Constructor<?>[] constructors = clazz.getDeclaredConstructors();
            // 根据参数数组长度来决定使用哪一个构造函数
            ConstructorBindingStrategy bindingStrategy = new OrderedConstructorBinding(constructors,
                    constructorArgs);
            Constructor<?> constructor = bindingStrategy.getConstructor();
            ReflectionUtils.makeAccessible(constructor);
            return bindingStrategy.initializeObject(constructor, constructorArgs);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
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

    static final class NamedConstructorBinding extends ConstructorBindingStrategy {

        private final Set<String> paramSet;

        @Override
        Object initializeObject(Constructor<?> constructor, Map<String, Object> constructorArgs) {
            List<Object> orderedParamValues = new ArrayList<>();
            Parameter[] parameters = constructor.getParameters();
            for (Parameter parameter : parameters) {
                orderedParamValues.add(constructorArgs.get(parameter.getName()));
            }
            Object inst = null;
            try {
                inst = constructor.newInstance(orderedParamValues.toArray());
            }catch (Exception e) {
                e.printStackTrace();
            }
            return inst;
        }

        @Override
        protected Constructor<?> get() {
            for (Constructor<?> constructor : super.constructors) {
                Parameter[] constructorParams = constructor.getParameters();
                Set<String> params = new HashSet<>();
                int paramCount = 0;
                for (Parameter parameter : constructorParams) {
                    paramCount++;
                    params.add(parameter.getName());
                }

                if (paramCount == paramSet.size()) {
                    if (paramSet.containsAll(params)) {
                        return constructor;
                    }
                }
            }
            return null;
        }

        public NamedConstructorBinding(Constructor<?>[] constructors, Map<String, Object> params) {
            super(constructors, params);
            this.paramSet = params.keySet();
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

}
