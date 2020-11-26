package cn.leetechweb.summer.bean.util;

import java.lang.reflect.Array;
import java.util.*;
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
        if (Collection.class.isAssignableFrom(clazz)) {
            Collection<T> converted = createCollection(clazz);
            Object[] array = (Object[]) val;
            for (Object data : array) {
                converted.add((T) data);
            }
            return (T) converted;
        }
        if (val.getClass().isArray()) {
            if (!clazz.isArray()) {
                int arrayLength = Array.getLength(val);
                if (arrayLength == 1) {
                    val = Array.get(val, 0);
                } else {
                    throw new IllegalArgumentException("数组元素匹配到了非数组元素");
                }
            }else {
                Object[] ret = new Object[Array.getLength(val)];
                for (int i = 0; i < ret.length; i++) {
                    ret[i] = convert(Array.get(val, i).getClass(), Array.get(val, i));
                }
                return (T) ret;
            }
        }
        Function<Object, ?> converter = converterMap.get(clazz);
        if (converter == null) {
            return (T) val;
        }
        return (T) converter.apply(val);
    }

    public static <T> Collection<T> createCollection(Class<T> type) {
        if (type.isAssignableFrom(ArrayList.class)) {
            return new ArrayList<>();
        }else if (type.isAssignableFrom(HashSet.class)) {
            return new HashSet<>();
        }else if (type.isAssignableFrom(LinkedHashSet.class)) {
            return new LinkedHashSet<>();
        }else if (type.isAssignableFrom(TreeSet.class)) {
            return new TreeSet<>();
        }
        return null;
    }
}
