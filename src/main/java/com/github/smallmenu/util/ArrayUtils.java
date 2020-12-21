package com.github.smallmenu.util;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * ArrayUtils
 *
 * @author smallmenu
 */
public class ArrayUtils extends ArrayBaseUtils {

    private ArrayUtils() {
        throw new AssertionError();
    }

    /**
     * 对象是否为数组对象
     *
     * @param obj 对象
     * @return boolean
     */
    public static boolean isArray(Object obj) {
        if (null == obj) {
            return false;
        }
        return obj.getClass().isArray();
    }

    /**
     * 获取数组长度，null 检查
     *
     * @param array
     * @return int
     * @throws IllegalArgumentException 如果参数不为数组，抛出异常
     */
    public static int length(Object array) throws IllegalArgumentException {
        if (null == array) {
            return 0;
        }
        return Array.getLength(array);
    }

    /**
     * 数组中是否包含元素
     *
     * @param <T>   数组元素类型
     * @param array 数组
     * @param value 被检查的元素
     * @return 是否包含
     */
    public static <T> boolean contains(T[] array, T value) {
        if (array != null) {
            return Arrays.binarySearch(array, value) > INDEX_NOT_FOUND;
        }

        return false;
    }
}
