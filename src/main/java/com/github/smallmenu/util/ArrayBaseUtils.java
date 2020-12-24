package com.github.smallmenu.util;

/**
 * ArrayBaseUtils
 *
 * @author smallmenu
 */
public class ArrayBaseUtils {
    //    public static final boolean[] EMPTY_BOOLEAN_ARRAY = new boolean[0];
//
//    public static final Boolean[] EMPTY_BOOLEAN_OBJECT_ARRAY = new Boolean[0];
//
//    public static final byte[] EMPTY_BYTE_ARRAY = new byte[0];
//
//    public static final Byte[] EMPTY_BYTE_OBJECT_ARRAY = new Byte[0];
//
//    public static final char[] EMPTY_CHAR_ARRAY = new char[0];
//
//    public static final Character[] EMPTY_CHARACTER_OBJECT_ARRAY = new Character[0];
//
//    public static final double[] EMPTY_DOUBLE_ARRAY = new double[0];
//
//    public static final Double[] EMPTY_DOUBLE_OBJECT_ARRAY = new Double[0];
//
    public static final String[] EMPTY_STRING = new String[0];
//
//    public static final float[] EMPTY_FLOAT_ARRAY = new float[0];
//
//    public static final Float[] EMPTY_FLOAT_OBJECT_ARRAY = new Float[0];
//
//    public static final int[] EMPTY_INT_ARRAY = new int[0];
//
//    public static final Integer[] EMPTY_INTEGER_OBJECT_ARRAY = new Integer[0];
//
//    public static final long[] EMPTY_LONG_ARRAY = new long[0];
//
//    public static final Long[] EMPTY_LONG_OBJECT_ARRAY = new Long[0];
//
//    public static final Class<?>[] EMPTY_CLASS_ARRAY = new Class[0];
//
//    public static final Object[] EMPTY_OBJECT_ARRAY = new Object[0];
//
//    public static final short[] EMPTY_SHORT_ARRAY = new short[0];
//
//    public static final Short[] EMPTY_SHORT_OBJECT_ARRAY = new Short[0];

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
    public static boolean contains(byte[] array, byte value) {
        if (array != null) {
            return indexOf(array, value) > INDEX_NOT_FOUND;
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
            return indexOf(array, value) > INDEX_NOT_FOUND;
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
            return indexOf(array, value) > INDEX_NOT_FOUND;
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
            return indexOf(array, value) > INDEX_NOT_FOUND;
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
            return indexOf(array, value) > INDEX_NOT_FOUND;
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
            return indexOf(array, value) > INDEX_NOT_FOUND;
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
            return indexOf(array, value) > INDEX_NOT_FOUND;
        }

        return false;
    }

    /**
     * 查找数组指定元素，返回索引
     *
     * @param array 数组
     * @param value 待查找元素
     * @return int
     */
    public static int indexOf(boolean[] array, boolean value) {
        if (null != array) {
            for (int i = 0; i < array.length; i++) {
                if (value == array[i]) {
                    return i;
                }
            }
        }
        return INDEX_NOT_FOUND;
    }

    /**
     * 查找数组指定元素，返回索引
     *
     * @param array 数组
     * @param value 待查找元素
     * @return int
     */
    public static int indexOf(byte[] array, byte value) {
        if (null != array) {
            for (int i = 0; i < array.length; i++) {
                if (value == array[i]) {
                    return i;
                }
            }
        }
        return INDEX_NOT_FOUND;
    }

    /**
     * 查找数组指定元素，返回索引
     *
     * @param array 数组
     * @param value 待查找元素
     * @return int
     */
    public static int indexOf(char[] array, char value) {
        if (null != array) {
            for (int i = 0; i < array.length; i++) {
                if (value == array[i]) {
                    return i;
                }
            }
        }
        return INDEX_NOT_FOUND;
    }

    /**
     * 查找数组指定元素，返回索引
     *
     * @param array 数组
     * @param value 待查找元素
     * @return int
     */
    public static int indexOf(double[] array, double value) {
        if (null != array) {
            for (int i = 0; i < array.length; i++) {
                if (Double.doubleToLongBits(value) == Double.doubleToRawLongBits(array[i])) {
                    return i;
                }
            }
        }
        return INDEX_NOT_FOUND;
    }

    /**
     * 查找数组指定元素，返回索引
     *
     * @param array 数组
     * @param value 待查找元素
     * @return int
     */
    public static int indexOf(float[] array, float value) {
        if (null != array) {
            for (int i = 0; i < array.length; i++) {
                if (Float.floatToIntBits(value) == Float.floatToIntBits(array[i])) {
                    return i;
                }
            }
        }
        return INDEX_NOT_FOUND;
    }

    /**
     * 查找数组指定元素，返回索引
     *
     * @param array 数组
     * @param value 待查找元素
     * @return int
     */
    public static int indexOf(int[] array, int value) {
        if (null != array) {
            for (int i = 0; i < array.length; i++) {
                if (value == array[i]) {
                    return i;
                }
            }
        }
        return INDEX_NOT_FOUND;
    }

    /**
     * 查找数组指定元素，返回索引
     *
     * @param array 数组
     * @param value 待查找元素
     * @return int
     */
    public static int indexOf(long[] array, long value) {
        if (null != array) {
            for (int i = 0; i < array.length; i++) {
                if (value == array[i]) {
                    return i;
                }
            }
        }
        return INDEX_NOT_FOUND;
    }

    /**
     * 查找数组指定元素，返回索引
     *
     * @param array 数组
     * @param value 待查找元素
     * @return int
     */
    public static int indexOf(short[] array, short value) {
        if (null != array) {
            for (int i = 0; i < array.length; i++) {
                if (value == array[i]) {
                    return i;
                }
            }
        }
        return INDEX_NOT_FOUND;
    }
}
