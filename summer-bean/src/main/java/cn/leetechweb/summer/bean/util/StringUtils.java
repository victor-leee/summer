package cn.leetechweb.summer.bean.util;

import java.net.URLEncoder;
import java.util.Arrays;

/**
 * 字符串工具类
 * Project Name: summer
 * Create Time: 2020/11/3 18:27
 *
 * @author junyu lee
 **/
public abstract class StringUtils {

    private static final char RIGHT_BIG_BRACKET = '}';

    private static final char LEFT_BIG_BRACKET = '{';

    private static final String UTF_8 = "utf-8";

    public static boolean equalsAllowNull(String first, String second) {
        if (first == null && second == null) {
            return true;
        }
        if (first == null || second == null) {
            return false;
        }
        return first.equals(second);
    }

    public static boolean equals(String first, String second) {
        if (first == null || second == null) {
            return false;
        }
        return first.equals(second);
    }

    /**
     * 格式化字符串，将args填入str的{}里面
     * @param str 带有{}格式的字符串，例如id={}&name={}
     * @param encodeArgs 是否是对args进行转义
     * @param args 占位的参数
     * @return 格式化后的字符串
     */
    public static String format(String str, boolean encodeArgs, Object... args) {
        int argCount = 0;
        StringBuilder res = new StringBuilder();
        int lastRightIndex = -1;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == RIGHT_BIG_BRACKET) {
                if (i > 0 && str.charAt(i - 1) == LEFT_BIG_BRACKET) {
                    res.append(str, lastRightIndex + 1, i - 1);
                    String arg = String.valueOf(args[argCount++]);
                    try {
                        res.append(encodeArgs ?
                                URLEncoder.encode(arg, UTF_8)
                                : arg);
                    }catch (Exception e) {
                        System.err.println(format("format发生错误, str:{}, args:{}", false,
                                str, Arrays.toString(args)));
                    }
                    lastRightIndex = i;
                }else {
                    throw new IllegalArgumentException("括号不匹配");
                }
            }
        }
        if (lastRightIndex < str.length() - 1) {
            res.append(str, lastRightIndex + 1, str.length());
        }
        return res.toString();
    }
}
