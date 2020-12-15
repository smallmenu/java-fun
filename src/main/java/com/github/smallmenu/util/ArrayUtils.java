package com.github.smallmenu.util;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * ArrayUtils
 *
 * @author smallmenu
 */
public class ArrayUtils {

    public static final int INDEX_NOT_FOUND = -1;

    private ArrayUtils() {
        throw new AssertionError();
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
     * 检测数组是否为空，数组是范型的
     *
     * @param array 数组
     * @param <T>   T
     * @return boolean
     */
    public static <T> boolean empty(T[] array) {
        return array == null || array.length == 0;
    }

    /**
     * 检测数组是否为空
     *
     * @param array 数组
     * @return boolean
     */
    public static boolean empty(long[] array) {
        return array == null || array.length == 0;
    }

    /**
     * 检测数组是否为空
     *
     * @param array 数组
     * @return boolean
     */
    public static boolean empty(int[] array) {
        return array == null || array.length == 0;
    }

    /**
     * 检测数组是否为空
     *
     * @param array 数组
     * @return boolean
     */
    public static boolean empty(short[] array) {
        return array == null || array.length == 0;
    }

    /**
     * 检测数组是否为空
     *
     * @param array 数组
     * @return boolean
     */
    public static boolean empty(char[] array) {
        return array == null || array.length == 0;
    }

    /**
     * 检测数组是否为空
     *
     * @param array 数组
     * @return boolean
     */
    public static boolean empty(byte[] array) {
        return array == null || array.length == 0;
    }

    /**
     * 检测数组是否为空
     *
     * @param array 数组
     * @return boolean
     */
    public static boolean empty(double[] array) {
        return array == null || array.length == 0;
    }

    /**
     * 检测数组是否为空
     *
     * @param array 数组
     * @return boolean
     */
    public static boolean empty(float[] array) {
        return array == null || array.length == 0;
    }

    /**
     * 检测数组是否为空
     *
     * @param array 数组
     * @return boolean
     */
    public static boolean empty(boolean[] array) {
        return array == null || array.length == 0;
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

    /**
     * 检测数组中是否包含元素
     *
     * @param array 数组
     * @param value 被检查的元素
     * @return boolean
     */
    public static boolean contains(char[] array, char value) {
        if (array != null) {
            return Arrays.binarySearch(array, value) > INDEX_NOT_FOUND;
        }

        return false;
    }

    /**
     * 检测数组中是否包含元素
     *
     * @param array 数组
     * @param value 被检查的元素
     * @return boolean
     */
    public static boolean contains(int[] array, int value) {
        if (array != null) {
            return Arrays.binarySearch(array, value) > INDEX_NOT_FOUND;
        }

        return false;
    }

    /**
     * 检测数组中是否包含元素
     *
     * @param array 数组
     * @param value 被检查的元素
     * @return boolean
     */
    public static boolean contains(long[] array, long value) {
        if (array != null) {
            return Arrays.binarySearch(array, value) > INDEX_NOT_FOUND;
        }

        return false;
    }

    /**
     * 检测数组中是否包含元素
     *
     * @param array 数组
     * @param value 被检查的元素
     * @return boolean
     */
    public static boolean contains(float[] array, float value) {
        if (array != null) {
            return Arrays.binarySearch(array, value) > INDEX_NOT_FOUND;
        }

        return false;
    }

    /**
     * 检测数组中是否包含元素
     *
     * @param array 数组
     * @param value 被检查的元素
     * @return boolean
     */
    public static boolean contains(double[] array, double value) {
        if (array != null) {
            return Arrays.binarySearch(array, value) > INDEX_NOT_FOUND;
        }

        return false;
    }

    /**
     * 检测数组中是否包含元素
     *
     * @param array 数组
     * @param value 被检查的元素
     * @return boolean
     */
    public static boolean contains(short[] array, short value) {
        if (array != null) {
            return Arrays.binarySearch(array, value) > INDEX_NOT_FOUND;
        }

        return false;
    }
}
