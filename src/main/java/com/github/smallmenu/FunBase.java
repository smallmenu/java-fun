package com.github.smallmenu;

import com.github.smallmenu.util.RandomUtils;
import com.github.smallmenu.util.SizeUtils;
import com.github.smallmenu.util.StringUtils;

/**
 * Java With Fun(ctions)
 *
 * @author smallmenu
 */
public class FunBase {
    /**
     * 禁止实例化
     */
    protected FunBase() {
        throw new AssertionError();
    }

    /**
     * 返回系统空闲堆内存，单位Byte
     *
     * @return long
     */
    public static long freeMemoryByte() {
        return Runtime.getRuntime().freeMemory();
    }

    /**
     * 返回系统最大堆内存（-Xmx），单位Byte
     *
     * @return long
     */
    public static long maxMemoryByte() {
        return Runtime.getRuntime().maxMemory();
    }

    /**
     * 返回系统当前已使用堆内存，单位Byte
     *
     * @return long
     */
    public static long usedMemoryByte() {
        return Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
    }

    /**
     * 返回系统当前已申请堆内存，单位Byte
     *
     * @return long
     */
    public static long totalMemoryByte() {
        return Runtime.getRuntime().totalMemory();
    }

    /**
     * 格式化返回系统当前空闲堆内存
     *
     * @param format 格式化单位
     * @return long
     */
    public static long freeMemory(final String format) {
        long memory = freeMemoryByte();
        return format != null ? SizeUtils.format(memory, format) : memory;
    }

    /**
     * 格式化返回系统已使用空闲堆内存
     *
     * @param format 格式化单位
     * @return long
     */
    public static long usedMemory(final String format) {
        long memory = totalMemoryByte() - freeMemoryByte();
        return format != null ? SizeUtils.format(memory, format) : memory;
    }

    /**
     * 格式化返回系统当前最大堆内存
     *
     * @param format 格式化单位
     * @return long
     */
    public static long maxMemory(final String format) {
        long memory = maxMemoryByte();
        return format != null ? SizeUtils.format(memory, format) : memory;
    }

    /**
     * 格式化返回系统当前已申请堆内存
     *
     * @param format 格式化单位
     * @return long
     */
    public static long totalMemory(final String format) {
        long memory = totalMemoryByte();
        return format != null ? SizeUtils.format(memory, format) : memory;
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
     * 检测是否为空
     *
     * @param value
     * @return
     */
    public static boolean empty(boolean value) {
        return value == false;
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
     * 获得随机数[0, 2^32)
     *
     * @return int
     */
    public static int randomInt() {
        return RandomUtils.randomInt();
    }

    /**
     * 获得指定范围内的随机数
     *
     * @param min 最小数（包含）
     * @param max 最大数（不包含）
     * @return int
     */
    public static int randomInt(int min, int max) {
        return RandomUtils.randomInt(min, max);
    }

    /**
     * 获得随机数[0, 2^32)
     *
     * @return int
     */
    public static long randomLong() {
        return RandomUtils.randomLong();
    }

    /**
     * 获得指定范围内的随机数
     *
     * @param min 最小数（包含）
     * @param max 最大数（不包含）
     * @return int
     */
    public static long randomLong(long min, long max) {
        return RandomUtils.randomLong(min, max);
    }

    /**
     * 以 separator 为分隔符将数组转换为字符串
     *
     * @param array     数组
     * @param separator 分隔符
     * @return 连接后的字符串
     */
    public static String join(boolean[] array, CharSequence separator) {
        if (array == null) {
            return null;
        }

        if (separator == null) {
            separator = StringUtils.EMPTY;
        }

        final StringBuilder sb = new StringBuilder();
        boolean isFirst = true;
        for (boolean item : array) {
            if (isFirst) {
                isFirst = false;
            } else {
                sb.append(separator);
            }
            sb.append(item);
        }
        return sb.toString();
    }

    /**
     * 以 separator 为分隔符将数组转换为字符串
     *
     * @param array     数组
     * @param separator 分隔符
     * @return String
     */
    public static String join(byte[] array, CharSequence separator) {
        if (array == null) {
            return null;
        }

        if (separator == null) {
            separator = StringUtils.EMPTY;
        }

        final StringBuilder sb = new StringBuilder();
        boolean isFirst = true;
        for (byte item : array) {
            if (isFirst) {
                isFirst = false;
            } else {
                sb.append(separator);
            }
            sb.append(item);
        }
        return sb.toString();
    }

    /**
     * 以 separator 为分隔符将数组转换为字符串
     *
     * @param array     数组
     * @param separator 分隔符
     * @return String
     */
    public static String join(char[] array, CharSequence separator) {
        if (array == null) {
            return null;
        }

        if (separator == null) {
            separator = StringUtils.EMPTY;
        }

        final StringBuilder sb = new StringBuilder();
        boolean isFirst = true;
        for (char item : array) {
            if (isFirst) {
                isFirst = false;
            } else {
                sb.append(separator);
            }
            sb.append(item);
        }
        return sb.toString();
    }

    /**
     * 以 separator 为分隔符将数组转换为字符串
     *
     * @param array     数组
     * @param separator 分隔符
     * @return String
     */
    public static String join(double[] array, CharSequence separator) {
        if (array == null) {
            return null;
        }

        if (separator == null) {
            separator = StringUtils.EMPTY;
        }

        final StringBuilder sb = new StringBuilder();
        boolean isFirst = true;
        for (double item : array) {
            if (isFirst) {
                isFirst = false;
            } else {
                sb.append(separator);
            }
            sb.append(item);
        }
        return sb.toString();
    }

    /**
     * 以 separator 为分隔符将数组转换为字符串
     *
     * @param array     数组
     * @param separator 分隔符
     * @return String
     */
    public static String join(float[] array, CharSequence separator) {
        if (array == null) {
            return null;
        }

        if (separator == null) {
            separator = StringUtils.EMPTY;
        }

        final StringBuilder sb = new StringBuilder();
        boolean isFirst = true;
        for (float item : array) {
            if (isFirst) {
                isFirst = false;
            } else {
                sb.append(separator);
            }
            sb.append(item);
        }
        return sb.toString();
    }

    /**
     * 以 separator 为分隔符将数组转换为字符串
     *
     * @param array     数组
     * @param separator 分隔符
     * @return String
     */
    public static String join(int[] array, CharSequence separator) {
        if (array == null) {
            return null;
        }

        if (separator == null) {
            separator = StringUtils.EMPTY;
        }

        final StringBuilder sb = new StringBuilder();
        boolean isFirst = true;
        for (int item : array) {
            if (isFirst) {
                isFirst = false;
            } else {
                sb.append(separator);
            }
            sb.append(item);
        }
        return sb.toString();
    }

    /**
     * 以 separator 为分隔符将数组转换为字符串
     *
     * @param array     数组
     * @param separator 分隔符
     * @return String
     */
    public static String join(short[] array, CharSequence separator) {
        if (array == null) {
            return null;
        }

        if (separator == null) {
            separator = StringUtils.EMPTY;
        }

        final StringBuilder sb = new StringBuilder();
        boolean isFirst = true;
        for (short item : array) {
            if (isFirst) {
                isFirst = false;
            } else {
                sb.append(separator);
            }
            sb.append(item);
        }
        return sb.toString();
    }

    /**
     * 以 separator 为分隔符将数组转换为字符串
     *
     * @param array     数组
     * @param separator 分隔符
     * @return String
     */
    public static String join(long[] array, CharSequence separator) {
        if (array == null) {
            return null;
        }

        if (separator == null) {
            separator = StringUtils.EMPTY;
        }

        final StringBuilder sb = new StringBuilder();
        boolean isFirst = true;
        for (long item : array) {
            if (isFirst) {
                isFirst = false;
            } else {
                sb.append(separator);
            }
            sb.append(item);
        }
        return sb.toString();
    }
}
