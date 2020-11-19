package com.github.smallmenu;

import com.github.smallmenu.date.DatePattern;
import com.github.smallmenu.date.Strtotime;
import com.github.smallmenu.util.CharUtils;
import com.github.smallmenu.util.FunUtils;
import com.github.smallmenu.util.StrUtils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * Java With Fun
 *
 * @author smallmenu
 */
final public class Fun {

    /**
     * 禁止实例化
     */
    private Fun() {
        throw new AssertionError();
    }

    /**
     * 返回当前 Unix 时间戳（秒）
     *
     * @return long
     */
    public static long time() {
        return System.currentTimeMillis() / 1000;
    }

    /**
     * 返回当前 Unix 时间戳（毫秒）
     *
     * @return long
     */
    public static long microtime() {
        return System.currentTimeMillis();
    }

    /**
     * 返回系统空闲内存
     *
     * @return long
     */
    public static long freeMemory() {
        return Runtime.getRuntime().freeMemory();
    }

    /**
     * 返回系统最大内存
     *
     * @return long
     */
    public static long maxMemory() {
        return Runtime.getRuntime().maxMemory();
    }

    /**
     * 返回系统当前使用内存
     *
     * @return long
     */
    public static long usedMemory() {
        return Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
    }

    /**
     * 返回系统当前占用内存
     *
     * @return long
     */
    public static long totalMemory() {
        return Runtime.getRuntime().totalMemory();
    }

    /**
     * 格式化返回系统当前空闲内存
     *
     * @param sizeFormat 格式化单位
     * @return long
     */
    public static long freeMemory(final String sizeFormat) {
        long memory = Runtime.getRuntime().freeMemory();
        return sizeFormat != null ? FunUtils.sizeFormat(memory, sizeFormat) : memory;

    }

    /**
     * 格式化返回系统使用空闲内存
     *
     * @param sizeFormat 格式化单位
     * @return long
     */
    public static long usedMemory(final String sizeFormat) {
        long memory = totalMemory() - freeMemory();
        return sizeFormat != null ? FunUtils.sizeFormat(memory, sizeFormat) : memory;
    }

    /**
     * 格式化返回系统当前最大内存
     *
     * @param sizeFormat 格式化单位
     * @return long
     */
    public static long maxMemory(final String sizeFormat) {
        long memory = Runtime.getRuntime().maxMemory();
        return sizeFormat != null ? FunUtils.sizeFormat(memory, sizeFormat) : memory;
    }

    /**
     * 格式化返回系统当前占用内存
     *
     * @param sizeFormat 格式化单位
     * @return long
     */
    public static long totalMemory(final String sizeFormat) {
        long memory = Runtime.getRuntime().totalMemory();
        return sizeFormat != null ? FunUtils.sizeFormat(memory, sizeFormat) : memory;
    }

    /**
     * 检测字符串是否为空白。（null、""、不可见字符如空格）
     *
     * @param str 待检测字符串
     * @return boolean
     */
    public static boolean blank(final CharSequence str) {
        int length;

        if ((str == null) || ((length = str.length()) == 0)) {
            return true;
        }

        for (int i = 0; i < length; i++) {
            if (!CharUtils.isBlankChar(str.charAt(i))) {
                return false;
            }
        }

        return true;
    }

    /**
     * 检测字符串是否全部为空
     *
     * @param strs 字符串列表
     * @return boolean
     */
    public static boolean blankAll(final CharSequence... strs) {
        if (emptyArray(strs)) {
            return true;
        }

        for (CharSequence str : strs) {
            if (!blank(str)) {
                return false;
            }
        }

        return true;
    }

    /**
     * 检测字符串是否为空。（null、""）
     *
     * @param str 待检测字符串
     * @return boolean
     */
    public static boolean empty(final CharSequence str) {
        return str == null || str.length() == 0;
    }

    /**
     * 检测字符串是否全部为空
     *
     * @param strs 字符串列表
     * @return boolean
     */
    public static boolean emptyAll(final CharSequence... strs) {
        if (emptyArray(strs)) {
            return true;
        }

        for (CharSequence str : strs) {
            if (!empty(str)) {
                return false;
            }
        }

        return true;
    }

    /**
     * 检测数组是否为空
     *
     * @param array  数组
     * @param <T> T
     * @return boolean
     */
    public static <T> boolean emptyArray(T[] array) {
        return array == null || array.length == 0;
    }

    /**
     * 除去字符串头尾部的空白，如果字符串是<code>null</code>，依然返回<code>null</code>。
     * <p>
     * 和<code>String.trim</code>不同，此方法使用<code>CharUtil.isBlankChar</code> 来判定空白， 因而可以除去英文字符集之外的其它空白，如中文空格。
     *
     * @param str 待处理字符串
     * @return String
     */
    public static String trim(final CharSequence str) {
        return (null == str) ? null : StrUtils.trim(str, 0);
    }

    /**
     * 除去字符串左侧的空白
     *
     * @param str 待处理字符串
     * @return String
     */
    public static String ltrim(final CharSequence str) {
        return (null == str) ? null : StrUtils.trim(str, -1);
    }

    /**
     * 除去字符串右侧的空白
     *
     * @param str 待处理字符串
     * @return String
     */
    public static String rtrim(final CharSequence str) {
        return (null == str) ? null : StrUtils.trim(str, 1);
    }


    /**
     * 除去字符串头尾部的空白
     *
     * 如果是 {@code null}，返回 <code>""</code>
     *
     * @param str 待处理字符串
     * @return String
     */
    public static String trimToEmpty(final CharSequence str) {
        return str == null ? StrUtils.EMPTY : trim(str);
    }

    /**
     * 除去字符串头尾部的空白
     *
     * 如果字符串是 {@code null} 或者 "" ，返回 {@code null}。
     *
     * @param str 待处理字符串
     * @return String
     */
    public static String trimToNull(final CharSequence str) {
        final String trimStr = trim(str);
        return StrUtils.EMPTY.equals(trimStr) ? null : trimStr;
    }

    /**
     * 字符串转整型
     *
     * 如果是 {@code null} 或者 ""，返回 0
     *
     * @param str 字符串
     * @return int
     */
    public static int toInt(final String str) {
        return toInt(str, 0);
    }

    /**
     * 字符串转整型
     *
     * 如果是 {@code null} 或者 "" 或者失败，返回默认值
     *
     * @param str 字符串
     * @param defalutValue 默认值
     * @return int
     */
    public static int toInt(final String str, final int defalutValue) {
        if(str == null) {
            return defalutValue;
        }
        try {
            return Integer.parseInt(str);
        } catch (final NumberFormatException nfe) {
            return defalutValue;
        }
    }

    /**
     * 字符串转整型
     *
     * 如果是 {@code null} 或者 ""，返回 0
     *
     * @param str 字符串
     * @return int
     */
    public static int intval(final String str) {
        return toInt(str);
    }

    /**
     * 字符串转整型
     *
     * 如果是 {@code null} 或者 ""，返回 0
     *
     * @param str 字符串
     * @return long
     */
    public static long longval(final String str) {
        return toLong(str);
    }

    /**
     * 字符串转整型
     *
     * 如果是 {@code null} 或者 ""，返回 0
     *
     * @param str 字符串
     * @return int
     */
    public static long toLong(final String str) {
        return toLong(str, 0);
    }

    /**
     * 查找字符串
     *
     * @param str       字符串
     * @param searchStr 被查找的字符串
     * @return boolean
     */
    public static boolean contains(CharSequence str, CharSequence searchStr) {
        if (null == str || null == searchStr) {
            return false;
        }
        return str.toString().contains(searchStr);
    }

    /**
     * 查找指定字符串是否包含指定字符列表中的任意一个字符
     *
     * @param str       指定字符串
     * @param searchStrs 被查找的字符串数组
     * @return 是否包含任意一个字符
     */
    public static boolean containsAny(CharSequence str, CharSequence... searchStrs) {
        if (empty(str) || emptyArray(searchStrs)) {
            return false;
        }
        for (CharSequence checkStr : searchStrs) {
            if (str.toString().contains(checkStr)) {
                return true;
            }
        }

        return false;
    }

    /**
     * 比较两个字符串
     *
     * @param str1 要比较的字符串1
     * @param str2 要比较的字符串2
     * @return boolean
     */
    public static boolean equals(CharSequence str1, CharSequence str2) {
        return StrUtils.equals(str1, str2, false);
    }

    /**
     * 比较两个字符串（大小写不敏感）
     *
     * @param str1 要比较的字符串1
     * @param str2 要比较的字符串2
     * @return boolean
     */
    public static boolean equalsIgnoreCase(CharSequence str1, CharSequence str2) {
        return StrUtils.equals(str1, str2, true);
    }

    /**
     * 是否以指定字符串开头
     *
     * @param str    被监测字符串
     * @param prefix 开头字符串
     * @return boolean
     */
    public static boolean startWith(CharSequence str, CharSequence prefix) {
        return StrUtils.startWith(str, prefix, false);
    }

    /**
     * 是否以指定字符串开头，忽略大小写
     *
     * @param str    被监测字符串
     * @param prefix 开头字符串
     * @return boolean
     */
    public static boolean startWithIgnoreCase(CharSequence str, CharSequence prefix) {
        return StrUtils.startWith(str, prefix, true);
    }

    /**
     * 是否以指定字符串结尾
     *
     * @param str    被监测字符串
     * @param suffix 结尾字符串
     * @return boolean
     */
    public static boolean endWith(CharSequence str, CharSequence suffix) {
        return StrUtils.endWith(str, suffix, false);
    }

    /**
     * 是否以指定字符串结尾，忽略大小写
     *
     * @param str    被监测字符串
     * @param suffix 结尾字符串
     * @return boolean
     */
    public static boolean endWithIgnoreCase(CharSequence str, CharSequence suffix) {
        return StrUtils.endWith(str, suffix, true);
    }

    /**
     * 获取字符串的长度，如果为 null 返回 0
     *
     * @param str 字符串
     * @return int
     */
    public static int strlen(CharSequence str) {
        return str == null ? 0 : str.length();
    }

    /**
     * 字符串转整型
     *
     * 如果是 {@code null} 或者 "" 或者失败，返回默认值
     *
     * @param str 字符串
     * @param defalutValue 默认值
     * @return long
     */
    public static long toLong(final String str, final long defalutValue) {
        if(str == null) {
            return defalutValue;
        }
        try {
            return Long.parseLong(str);
        } catch (final NumberFormatException nfe) {
            return defalutValue;
        }
    }


    /**
     * 根据格式化参数返回时间字符串
     *
     * @return String
     */
    public static String date() {
        return date(DatePattern.DATETIME_PATTERN);
    }

    /**
     * 根据格式化参数返回时间字符串
     *
     * @param timestamp 时间戳
     * @return String
     */
    public static String date(final long timestamp) {
        return date(DatePattern.DATETIME_PATTERN, timestamp);
    }

    /**
     * 根据格式化参数返回时间字符串
     *
     * @param pattern 日期时间格式
     * @return String
     */
    public static String date(final String pattern) {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 根据格式化参数，时间戳参数，返回时间字符串
     *
     * @param pattern 日期时间格式
     * @param timestamp  时间戳
     * @return String
     */
    public static String date(final String pattern, final long timestamp) {
        return LocalDateTime.ofInstant(Instant.ofEpochSecond(Math.abs(timestamp)), ZoneId.systemDefault())
                .format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 返回当前默认时间戳
     *
     * @return long
     */
    public static long strtotime() {
        return Instant.now().getEpochSecond();
    }

    /**
     * 通用格式日期时间字符串自动转换时间戳
     *
     * @param dateString 日期时间字符串
     * @return long
     */
    public static long strtotime(final String dateString) {
        return Strtotime.parse(dateString);
    }

    /**
     * 以提供的时间戳为基准，返回简单表达式计算后的时间戳
     *
     * @param dateString 时期时间字符串
     * @param timestamp  时间戳
     * @return long
     */
    public static long strtotime(final String dateString, final Long timestamp) {
        return Strtotime.parse(dateString, timestamp);
    }
}
