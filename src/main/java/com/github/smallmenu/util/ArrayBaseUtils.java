package com.github.smallmenu.util;

import java.util.Arrays;

/**
 * ArrayBaseUtils
 *
 * @author smallmenu
 */
public class ArrayBaseUtils {

    public static final int INDEX_NOT_FOUND = -1;

    protected ArrayBaseUtils() {
        throw new AssertionError();
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
