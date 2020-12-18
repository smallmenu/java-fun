package com.github.smallmenu;

public class FunBase {
    /**
     * 禁止实例化
     */
    protected FunBase() {
        throw new AssertionError();
    }

    /**
     * 检测是否为空
     *
     * @param value
     * @return boolean
     */
    public static boolean empty(int value) {
        return value == 0;
    }

    /**
     * 检测是否为空
     *
     * @param value
     * @return boolean
     */
    public static boolean empty(long value) {
        return value == 0;
    }

    /**
     * 检测是否为空
     *
     * @param value
     * @return boolean
     */
    public static boolean empty(short value) {
        return value == 0;
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
}
