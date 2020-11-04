package cn.leetechweb.summer.bean.util;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Project Name: summer
 * Create Time: 2020/11/4 15:04
 *
 * @author junyu lee
 **/
public abstract class ConvertUtils {

    private static final Map<Class<?>, Function<Object, ?>> converterMap;

    private static final String TRUE = "true";

    static {

        Function<Object, Double> doubleFunction = x -> Double.parseDouble(String.valueOf(x));
        Function<Object, Integer> integerFunction = x -> Integer.parseInt(String.valueOf(x));
        Function<Object, Float> floatFunction = x -> Float.parseFloat(String.valueOf(x));
        Function<Object, Boolean> booleanFunction = x -> {
            String strFrom = String.valueOf(x);
            return TRUE.equals(strFrom.toLowerCase()) ? Boolean.TRUE : Boolean.FALSE;
        };

        converterMap = new HashMap<>(16);
        converterMap.put(Double.class, doubleFunction);
        converterMap.put(Integer.class, integerFunction);
        converterMap.put(Float.class, floatFunction);
        converterMap.put(Boolean.class, booleanFunction);

        converterMap.put(int.class, integerFunction);
        converterMap.put(double.class, doubleFunction);
        converterMap.put(float.class, floatFunction);
        converterMap.put(boolean.class, booleanFunction);
    }

    @SuppressWarnings("unchecked")
    public static <T> T convert(Class<T> clazz, Object val) {
        Function<Object, ?> converter = converterMap.get(clazz);
        if (converter == null) {
            return (T) val;
        }
        return (T) converter.apply(val);
    }
}
