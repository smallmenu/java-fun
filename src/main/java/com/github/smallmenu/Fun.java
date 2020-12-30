package com.github.smallmenu;

import com.github.smallmenu.constant.DatePattern;
import com.github.smallmenu.date.Strtotime;
import com.github.smallmenu.util.*;

import java.lang.reflect.Array;
import java.nio.charset.Charset;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Java With Fun(ctions)
 *
 * @author smallmenu
 */
public class Fun extends FunBase {
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
     * 返回系统空闲堆内存，单位MB
     *
     * @return long
     */
    public static long freeMemory() {
        return freeMemory(SizeUtils.MB);
    }

    /**
     * 返回系统最大堆内存（-Xmx），单位MB
     *
     * @return long
     */
    public static long maxMemory() {
        return maxMemory(SizeUtils.MB);
    }

    /**
     * 返回系统当前已使用堆内存，单位MB
     *
     * @return long
     */
    public static long usedMemory() {
        return usedMemory(SizeUtils.MB);
    }

    /**
     * 返回系统当前已申请堆内存，单位MB
     *
     * @return long
     */
    public static long totalMemory() {
        return totalMemory(SizeUtils.MB);
    }

    /**
     * 字符串获取 Bytes
     *
     * @param str 字符串
     * @return byte[]
     */
    public static byte[] bytes(CharSequence str) {
        return bytes(str, Charset.defaultCharset());
    }

    /**
     * 字符串获取 Bytes
     *
     * @param str     字符串
     * @param charset 字符集
     * @return byte[]
     */
    public static byte[] bytes(CharSequence str, Charset charset) {
        if (str == null) {
            return null;
        }

        if (null == charset) {
            return str.toString().getBytes();
        }

        return str.toString().getBytes(charset);
    }

    /**
     * 字符串获取 Bytes
     *
     * @param str     字符串
     * @param charset 字符集
     * @return byte[]
     */
    public static byte[] bytes(CharSequence str, String charset) {
        return bytes(str, blank(charset) ? Charset.defaultCharset() : Charset.forName(charset));
    }

    /**
     * 检测是否为空，（可匹配单一对象）
     * <p>
     * 如果对象为 null，返回 true
     * 如果对象为数组，判断数组长度是否为 0
     * 如果对象为字符串，判断字符串长度是否为 0
     * 如果对象为数值类型，判断是否为 0
     * 如果对象为布尔类型，判断是否为 false
     * 其他情况，默认返回 false
     *
     * @param object 数组
     * @return boolean
     */
    public static boolean empty(Object object) {
        if (object != null) {
            if (ArrayUtils.isArray(object)) {
                return 0 == Array.getLength(object);
            }
            if (StringUtils.isString(object)) {
                return object.toString().length() == 0;
            }
            if (object instanceof Integer) {
                return 0 == (int) object;
            }
            if (object instanceof Long) {
                return 0 == (long) object;
            }
            if (object instanceof Short) {
                return 0 == (short) object;
            }
            if (object instanceof Boolean) {
                return !((boolean) object);
            }
            return false;
        }
        return true;
    }

    /**
     * 检测字符串是否为空
     * <p>
     * 空包含 null、空字符串 ""
     *
     * @param str 待检测字符串
     * @return boolean
     */
    public static boolean empty(final CharSequence str) {
        return str == null || str.length() == 0;
    }

    /**
     * 检测数组为空（范型）
     *
     * @param array 数组
     * @param <T>   T
     * @return boolean
     */
    public static <T> boolean empty(T[] array) {
        return array == null || array.length == 0;
    }

    /**
     * 检测 Map 是否为空
     *
     * @param map 列表
     * @return boolean
     */
    public static boolean empty(Map<?, ?> map) {
        return map == null || map.isEmpty();
    }

    /**
     * 检测 Collection 是否为空
     *
     * @param collection 集合
     * @return boolean
     */
    public static boolean empty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    /**
     * 检测字符串列表，是否包含有空值
     *
     * @param strs 字符串列表
     * @return boolean
     */
    public static boolean emptyAny(CharSequence... strs) {
        if (empty(strs)) {
            return true;
        }

        for (CharSequence str : strs) {
            if (empty(str)) {
                return true;
            }
        }

        return false;
    }

    /**
     * 检测字符串列表，是否全部为空
     *
     * @param strs 字符串列表
     * @return boolean
     */
    public static boolean emptyAll(final CharSequence... strs) {
        if (empty(strs)) {
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
     * 检测字符串是否为空白
     * <p>
     * 空白包含：null、""、" "、以及不可见字符如缩紧
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
     * 检测字符串列表，是否包含空白
     * <p>
     * 空白包含：null、""、" "、以及不可见字符如缩紧
     *
     * @param strs 字符串列表
     * @return boolean
     */
    public static boolean blankAny(CharSequence... strs) {
        if (empty(strs)) {
            return true;
        }

        for (CharSequence str : strs) {
            if (blank(str)) {
                return true;
            }
        }

        return false;
    }

    /**
     * 检测字符串列表，是否全部为空白
     *
     * @param strs 字符串列表
     * @return boolean
     */
    public static boolean blankAll(final CharSequence... strs) {
        if (empty(strs)) {
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
     * 除去字符串头尾部的空白，如果字符串是 null，依然返回 null
     * <p>
     * 和 String.trim 不同，此方法使用 CharUtils.isBlankChar()  来判定空白
     *
     * @param str 待处理字符串
     * @return String
     */
    public static String trim(final CharSequence str) {
        return (null == str) ? null : StringUtils.trim(str);
    }

    /**
     * 除去字符串左侧的空白
     *
     * @param str 待处理字符串
     * @return String
     */
    public static String ltrim(final CharSequence str) {
        return (null == str) ? null : StringUtils.trim(str, null, -1);
    }

    /**
     * 除去字符串右侧的空白
     *
     * @param str 待处理字符串
     * @return String
     */
    public static String rtrim(final CharSequence str) {
        return (null == str) ? null : StringUtils.trim(str, null, 1);
    }

    /**
     * 除去字符串两侧的指定字符串集合
     * <p>
     * 如果 trimStr 为空，则默认使用空白符号
     *
     * @param str     待处理字符串
     * @param trimStr 需要处理的字符串集合
     * @return String
     */
    public static String trim(final CharSequence str, final CharSequence trimStr) {
        return (null == str) ? null : StringUtils.trim(str, trimStr, 0);
    }


    /**
     * 除去字符串头尾部的空白，并处理 null 值
     * <p>
     * 如果是 null，返回 ""
     *
     * @param str 待处理字符串
     * @return String
     */
    public static String trimToEmpty(final CharSequence str) {
        return str == null ? StringUtils.EMPTY : trim(str);
    }

    /**
     * 除去字符串头尾部的空白，并处理 null 和空字符串 ""
     * <p>
     * 如果字符串是 null 或者 "" ，返回  null
     *
     * @param str 待处理字符串
     * @return String
     */
    public static String trimToNull(final CharSequence str) {
        final String trimStr = trim(str);
        return StringUtils.EMPTY.equals(trimStr) ? null : trimStr;
    }

    /**
     * 字符串转整型
     * <p>
     * 如果是 null 或者 "" 或者异常，返回 0
     * 如果是小数，返回 0
     *
     * @param str 字符串
     * @return int
     */
    public static int toInt(final String str) {
        return toInt(str(str), 0);
    }

    /**
     * 字符串转整型
     * <p>
     * 如果是 null 或者 "" 或者异常，返回默认值
     *
     * @param str          字符串
     * @param defalutValue 默认值
     * @return int
     */
    public static int toInt(final String str, final int defalutValue) {
        if (str == null) {
            return defalutValue;
        }
        try {
            return Integer.parseInt(str);
        } catch (final NumberFormatException nfe) {
            return defalutValue;
        }
    }

    /**
     * 字符串转浮点型
     * <p>
     * 如果是 null 或者 "" 或者异常，返回 0.0
     *
     * @param str 字符串
     * @return float
     */
    public static float toFloat(final String str) {
        return toFloat(str, 0.0F);
    }

    /**
     * 字符串转浮点型
     * <p>
     * 如果是 null 或者 "" 或者异常，返回默认值
     *
     * @param str 字符串
     * @return float
     */
    public static float toFloat(final String str, float defaultValue) {
        if (str == null) {
            return defaultValue;
        } else {
            try {
                return Float.parseFloat(str);
            } catch (NumberFormatException var3) {
                return defaultValue;
            }
        }
    }

    /**
     * 字符串转浮点型
     * <p>
     * 如果是 null 或者 "" 或者异常，返回 0.0
     *
     * @param str 字符串
     * @return double
     */
    public static double toDouble(String str) {
        return toDouble(str, 0.0D);
    }

    /**
     * 字符串转浮点型
     * <p>
     * 如果是 null 或者 "" 或者异常，返回默认值
     *
     * @param str 字符串
     * @return double
     */
    public static double toDouble(String str, double defaultValue) {
        if (str == null) {
            return defaultValue;
        } else {
            try {
                return Double.parseDouble(str);
            } catch (NumberFormatException var4) {
                return defaultValue;
            }
        }
    }

    /**
     * 字符串转整型
     * <p>
     * 如果是  null 或者 "" 或异常，返回 0
     *
     * @param str 字符串
     * @return int
     */
    public static long toLong(final String str) {
        return toLong(str, 0);
    }

    /**
     * 字符串转整型
     * <p>
     * 如果是  null 或者 "" 或者异常，返回默认值
     *
     * @param str          字符串
     * @param defalutValue 默认值
     * @return long
     */
    public static long toLong(final String str, final long defalutValue) {
        if (str == null) {
            return defalutValue;
        }
        try {
            return Long.parseLong(str);
        } catch (final NumberFormatException nfe) {
            return defalutValue;
        }
    }

    /**
     * 查找字符串是否包含指定字符串
     *
     * @param str       字符串
     * @param searchStr 被查找的字符串
     * @return boolean
     */
    public static boolean contains(final CharSequence str, final CharSequence searchStr) {
        if (null == str || null == searchStr) {
            return false;
        }
        return str.toString().contains(searchStr);
    }

    /**
     * 查找字符串是否包含指定字符串，忽略大小写
     *
     * @param str       字符串
     * @param searchStr 被查找的字符串
     * @return boolean
     */
    public static boolean containsIgnoreCase(final CharSequence str, final CharSequence searchStr) {
        if (null == str || null == searchStr) {
            return false;
        }
        return str.toString().toLowerCase().contains(searchStr.toString().toLowerCase());
    }

    /**
     * 查找指定字符串，是否包含指定字符列表中的任意一个字符串
     *
     * @param str        指定字符串
     * @param searchStrs 被查找的字符串数组
     * @return 是否包含任意一个字符
     */
    public static boolean containsAny(final CharSequence str, final CharSequence... searchStrs) {
        if (empty(str) || empty(searchStrs)) {
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
    public static boolean equals(final CharSequence str1, final CharSequence str2) {
        return StringUtils.equals(str1, str2, false);
    }

    /**
     * 比较两个字符串，忽略大小写
     *
     * @param str1 要比较的字符串1
     * @param str2 要比较的字符串2
     * @return boolean
     */
    public static boolean equalsIgnoreCase(final CharSequence str1, final CharSequence str2) {
        return StringUtils.equals(str1, str2, true);
    }

    /**
     * 检测字符串是否以指定字符串开头
     * <p>
     * 如果给定的字符串和开头字符串都为 null 则返回 true ，否则任意一个值为 null 返回 false
     * 如果给定的字符串不为 null，prefix 字符串为 "" 空字符串，返回true
     *
     * @param str    被监测字符串
     * @param prefix 开头字符串
     * @return boolean
     */
    public static boolean startsWith(final CharSequence str, final CharSequence prefix) {
        return StringUtils.startsWith(str, prefix, false);
    }

    /**
     * 是否以指定字符串开头，忽略大小写
     *
     * @param str    被监测字符串
     * @param prefix 开头字符串
     * @return boolean
     */
    public static boolean startsWithIgnoreCase(final CharSequence str, final CharSequence prefix) {
        return StringUtils.startsWith(str, prefix, true);
    }

    /**
     * 是否以指定字符串结尾
     * <p>
     * 如果给定的字符串和开头字符串都为 null 则返回 true ，否则任意一个值为 null 返回 false
     * 如果给定的字符串不为 null，suffix 字符串为 "" 空字符串，返回true
     *
     * @param str    被监测字符串
     * @param suffix 结尾字符串
     * @return boolean
     */
    public static boolean endsWith(final CharSequence str, final CharSequence suffix) {
        return StringUtils.endsWith(str, suffix, false);
    }

    /**
     * 是否以指定字符串结尾，忽略大小写
     *
     * @param str    被监测字符串
     * @param suffix 结尾字符串
     * @return boolean
     */
    public static boolean endsWithIgnoreCase(final CharSequence str, final CharSequence suffix) {
        return StringUtils.endsWith(str, suffix, true);
    }

    /**
     * 获取字符串的字符长度，如果为 null 返回 0
     * <p>
     * 使用 String.lenght 方法， 中英文数字均是 1
     *
     * @param str 字符串
     * @return int
     */
    public static int length(final CharSequence str) {
        return str == null ? 0 : str.length();
    }

    /**
     * 字符串转小写
     *
     * @param str 字符串
     * @return String
     */
    public static String toLowerCase(final CharSequence str) {
        return str == null ? null : str.toString().toLowerCase();
    }

    /**
     * 字符串转大写
     *
     * @param str 字符串
     * @return String
     */
    public static String toUpperCase(final CharSequence str) {
        return str == null ? null : str.toString().toUpperCase();
    }

    /**
     * 截取字符串左侧指定长度的子串
     *
     * @param str    字符串
     * @param length 长度
     * @return String
     */
    public static String left(final String str, final int length) {
        if (str == null) {
            return null;
        }
        if (length < 0) {
            return StringUtils.EMPTY;
        }
        if (str.length() <= length) {
            return str;
        }
        return str.substring(0, length);
    }

    /**
     * 截取字符串右侧指定长度的子串
     *
     * @param str    字符串
     * @param length 长度
     * @return String
     */
    public static String right(final String str, final int length) {
        if (str == null) {
            return null;
        }
        if (length < 0) {
            return StringUtils.EMPTY;
        }
        if (str.length() <= length) {
            return str;
        }
        return str.substring(str.length() - length);
    }

    /**
     * 查找字符串，返回索引位置
     *
     * @param str       字符串
     * @param searchStr 查找字符串
     * @return int
     */
    public static int indexOf(final CharSequence str, final CharSequence searchStr) {
        if (str == null || searchStr == null) {
            return StringUtils.INDEX_NOT_FOUND;
        }
        return StringUtils.indexOf(str, searchStr, 0, false);
    }

    /**
     * 查找字符串，返回索引位置
     *
     * @param str       字符串
     * @param searchStr 查找字符串
     * @param start     起始位置
     * @return int
     */
    public static int indexOf(final CharSequence str, final CharSequence searchStr, int start) {
        if (str == null || searchStr == null) {
            return StringUtils.INDEX_NOT_FOUND;
        }
        return StringUtils.indexOf(str, searchStr, start, false);
    }

    /**
     * 查找字符串，返回索引位置，忽略大小写
     *
     * @param str       字符串
     * @param searchStr 查找字符串
     * @return int
     */
    public static int indexOfIgnoreCase(final CharSequence str, final CharSequence searchStr) {
        if (str == null || searchStr == null) {
            return StringUtils.INDEX_NOT_FOUND;
        }
        return StringUtils.indexOf(str, searchStr, 0, true);
    }

    /**
     * 查找字符串，返回索引位置，忽略大小写
     *
     * @param str       字符串
     * @param searchStr 查找字符串
     * @param start     起始位置
     * @return int
     */
    public static int indexOfIgnoreCase(final CharSequence str, final CharSequence searchStr, int start) {
        if (str == null || searchStr == null) {
            return StringUtils.INDEX_NOT_FOUND;
        }
        return StringUtils.indexOf(str, searchStr, start, true);
    }

    /**
     * CharSequence 转 String
     *
     * @param cs 字符串
     * @return String
     */
    public static String str(final CharSequence cs) {
        return null == cs ? null : cs.toString();
    }

    /**
     * 反转字符串
     *
     * @param str 字符串
     * @return String
     */
    public static String reverse(final String str) {
        if (str == null) {
            return null;
        }

        return new StringBuilder(str).reverse().toString();
    }

    /**
     * 数组或对象转 String
     *
     * @param obj 数组或对象
     * @return String
     */
    public static String string(Object obj) {
        if (null == obj) {
            return null;
        }

        if (obj instanceof long[]) {
            return Arrays.toString((long[]) obj);
        } else if (obj instanceof int[]) {
            return Arrays.toString((int[]) obj);
        } else if (obj instanceof short[]) {
            return Arrays.toString((short[]) obj);
        } else if (obj instanceof char[]) {
            return Arrays.toString((char[]) obj);
        } else if (obj instanceof byte[]) {
            return Arrays.toString((byte[]) obj);
        } else if (obj instanceof boolean[]) {
            return Arrays.toString((boolean[]) obj);
        } else if (obj instanceof float[]) {
            return Arrays.toString((float[]) obj);
        } else if (obj instanceof double[]) {
            return Arrays.toString((double[]) obj);
        } else if (ArrayUtils.isArray(obj)) {
            try {
                return Arrays.deepToString((Object[]) obj);
            } catch (Exception ignore) {
            }
        }

        return obj.toString();
    }

    /**
     * 重复字符到指定次数
     *
     * @param ch     字符
     * @param repeat 次数
     * @return String
     */
    public static String repeat(final char ch, final int repeat) {
        if (repeat <= 0) {
            return StringUtils.EMPTY;
        }
        final char[] buf = new char[repeat];
        for (int i = repeat - 1; i >= 0; i--) {
            buf[i] = ch;
        }
        return new String(buf);
    }

    /**
     * 重复字符串到指定次数
     *
     * @param str    字符串
     * @param repeat 次数
     * @return String
     */
    public static String repeat(final CharSequence str, final int repeat) {
        if (str == null) {
            return null;
        }

        if (repeat <= 0) {
            return StringUtils.EMPTY;
        }

        final int inputLength = str.length();
        if (repeat == 1 || inputLength == 0) {
            return str(str);
        }

        if (inputLength == 1 && repeat <= StringUtils.PAD_LIMIT) {
            return repeat(str.charAt(0), repeat);
        }

        final int outputLength = inputLength * repeat;
        switch (inputLength) {
            case 1:
                return repeat(str.charAt(0), repeat);
            case 2:
                final char ch0 = str.charAt(0);
                final char ch1 = str.charAt(1);
                final char[] output2 = new char[outputLength];
                for (int i = repeat * 2 - 2; i >= 0; i--, i--) {
                    output2[i] = ch0;
                    output2[i + 1] = ch1;
                }
                return new String(output2);
            default:
                final StringBuilder buf = new StringBuilder(outputLength);
                for (int i = 0; i < repeat; i++) {
                    buf.append(str);
                }
                return buf.toString();
        }
    }

    /**
     * 右侧填充字符串到最小长度，使用单一字符填充
     *
     * @param str     待填充字符串
     * @param length  长度
     * @param padChar 填充字符
     * @return String
     */
    public static String padLeft(final CharSequence str, final int length, final char padChar) {
        if (str == null) {
            return null;
        }

        final int pads = length - str.length();
        if (pads <= 0) {
            return str.toString();
        }

        if (pads > StringUtils.PAD_LIMIT) {
            return padLeft(str, length, String.valueOf(padChar));
        }

        return repeat(padChar, pads).concat(str.toString());
    }


    /**
     * 左侧填充字符串到指定长度，使用默认空格填充
     *
     * @param str    待填充字符串
     * @param length 长度
     * @return String
     */
    public static String padLeft(final CharSequence str, final int length) {
        return padLeft(str, length, ' ');
    }

    /**
     * 左侧填充字符串到指定长度
     *
     * @param str    待填充字符串
     * @param length 长度
     * @param padStr 填充字符串
     * @return String
     */
    public static String padLeft(final CharSequence str, final int length, String padStr) {
        if (str == null) {
            return null;
        }

        if (empty(padStr)) {
            padStr = StringUtils.SPACE;
        }

        final int padLen = padStr.length();
        final int strLen = str.length();
        final int pads = length - strLen;
        if (pads <= 0) {
            return str(str);
        }

        if (padLen == 1 && pads <= StringUtils.PAD_LIMIT) {
            return padLeft(str, length, padStr.charAt(0));
        }

        if (pads == padLen) {
            return padStr.concat(str.toString());
        } else if (pads < padLen) {
            return padStr.substring(0, pads).concat(str.toString());
        } else {
            final char[] padding = new char[pads];
            final char[] padChars = padStr.toCharArray();
            for (int i = 0; i < pads; i++) {
                padding[i] = padChars[i % padLen];
            }
            return new String(padding).concat(str.toString());
        }
    }

    /**
     * 右侧填充字符串到指定长度。使用单一字符填充
     *
     * @param str    待填充字符
     * @param length 长度
     * @return String
     */
    public static String padRight(final CharSequence str, final int length, final char padChar) {
        if (str == null) {
            return null;
        }

        final int pads = length - str.length();
        if (pads <= 0) {
            return str(str);
        }

        if (pads > StringUtils.PAD_LIMIT) {
            return padRight(str, length, String.valueOf(padChar));
        }

        return str.toString().concat(repeat(padChar, pads));
    }

    /**
     * 右侧填充字符串到指定长度。使用默认空格填充
     *
     * @param str    待填充字符串
     * @param length 长度
     * @return String
     */
    public static String padRight(final CharSequence str, final int length) {
        return padRight(str, length, ' ');
    }

    /**
     * 右侧填充字符串到指定长度
     *
     * @param str    待填充字符串
     * @param length 长度
     * @param padStr 填充字符串
     * @return String
     */
    public static String padRight(final CharSequence str, final int length, String padStr) {
        if (str == null) {
            return null;
        }

        if (empty(padStr)) {
            padStr = StringUtils.SPACE;
        }

        final int padLen = padStr.length();
        final int strLen = str.length();
        final int pads = length - strLen;
        if (pads <= 0) {
            return str(str);
        }
        if (padLen == 1 && pads <= StringUtils.PAD_LIMIT) {
            return padRight(str, length, padStr.charAt(0));
        }

        if (pads == padLen) {
            return str.toString().concat(padStr);
        } else if (pads < padLen) {
            return str.toString().concat(padStr.substring(0, pads));
        } else {
            final char[] padding = new char[pads];
            final char[] padChars = padStr.toCharArray();
            for (int i = 0; i < pads; i++) {
                padding[i] = padChars[i % padLen];
            }
            return str.toString().concat(new String(padding));
        }
    }

    /**
     * 分割字符串为数组
     *
     * @param str 字符串
     * @return String[]
     */
    public static String[] split(final CharSequence str) {
        if (str == null) {
            return new String[]{};
        }

        return StringUtils.split(str.toString(), 0, false, false);
    }

    /**
     * 分割字符串为数组
     *
     * @param str       字符串
     * @param seperator 分隔符
     * @return String[]
     */
    public static String[] split(final CharSequence str, final char seperator) {
        if (str == null) {
            return new String[]{};
        }

        return StringUtils.split(str.toString(), seperator, 0, false, false, false);
    }

    /**
     * 分割字符串为数组
     *
     * @param str       字符串
     * @param seperator 分割字符串
     * @return String[]
     */
    public static String[] split(final CharSequence str, final CharSequence seperator) {
        if (str == null) {
            return new String[]{};
        }

        if (seperator == null) {
            return StringUtils.split(str.toString(), 0, false, false);
        } else {
            return StringUtils.split(str.toString(), seperator.toString(), 0, false, false, false);
        }
    }

    /**
     * 分割字符串为列表
     * <p>
     * 默认以空白字符分割。对分割后的值进行 trim ，并自动忽略空值
     *
     * @param str 字符串
     * @return String[]
     */
    public static List<String> splitToList(final CharSequence str) {
        if (str == null) {
            return Collections.emptyList();
        }

        return StringUtils.splitToList(str.toString(), 0, false, false);
    }

    /**
     * 分割字符串为列表
     * <p>
     * 默认以空白字符分割。对分割后的值进行 trim ，并自动忽略空值
     *
     * @param str       字符串
     * @param seperator 分隔符
     * @return String[]
     */
    public static List<String> splitToList(final CharSequence str, final char seperator) {
        if (str == null) {
            return Collections.emptyList();
        }

        return StringUtils.splitToList(str.toString(), seperator, 0, false, false, false);
    }

    /**
     * 分割字符串为列表
     *
     * @param str       字符串
     * @param seperator 分割字符串
     * @return String[]
     */
    public static List<String> splitToList(final CharSequence str, final CharSequence seperator) {
        if (str == null) {
            return Collections.emptyList();
        }

        if (seperator == null) {
            return StringUtils.splitToList(str.toString(), 0, false, false);
        } else {
            return StringUtils.splitToList(str.toString(), seperator.toString(), 0, false, false, false);
        }
    }

    /**
     * 分割字符串为数组
     * <p>
     * 默认以空白字符分割。对分割后的值进行 trim ，并自动忽略空值
     *
     * @param str 字符串
     * @return String[]
     */
    public static String[] splitTrim(final CharSequence str) {
        if (str == null) {
            return new String[]{};
        }

        return StringUtils.split(str.toString(), 0, true, true);
    }

    /**
     * 分割字符串为数组
     * <p>
     * 默认以空白字符分割。对分割后的值进行 trim ，并自动忽略空值
     *
     * @param str       字符串
     * @param seperator 分隔符
     * @return String[]
     */
    public static String[] splitTrim(final CharSequence str, final char seperator) {
        if (str == null) {
            return new String[]{};
        }

        return StringUtils.split(str.toString(), seperator, 0, true, true, false);
    }

    /**
     * 分割字符串为数组
     *
     * @param str       字符串
     * @param seperator 分割字符串
     * @return String[]
     */
    public static String[] splitTrim(final CharSequence str, final CharSequence seperator) {
        if (str == null) {
            return new String[]{};
        }

        if (seperator == null) {
            return StringUtils.split(str.toString(), 0, true, true);
        } else {
            return StringUtils.split(str.toString(), seperator.toString(), 0, true, true, false);
        }
    }

    /**
     * 分割字符串为列表
     * <p>
     * 默认以空白字符分割。对分割后的值进行 trim ，并自动忽略空值
     *
     * @param str 字符串
     * @return String[]
     */
    public static List<String> splitTrimToList(final CharSequence str) {
        if (str == null) {
            return Collections.emptyList();
        }

        return StringUtils.splitToList(str.toString(), 0, true, true);
    }

    /**
     * 分割字符串为列表
     * <p>
     * 默认以空白字符分割。对分割后的值进行 trim ，并自动忽略空值
     *
     * @param str       字符串
     * @param seperator 分隔符
     * @return String[]
     */
    public static List<String> splitTrimToList(final CharSequence str, final char seperator) {
        if (str == null) {
            return Collections.emptyList();
        }

        return StringUtils.splitToList(str.toString(), seperator, 0, true, true, false);
    }

    /**
     * 分割字符串为列表
     *
     * @param str       字符串
     * @param seperator 分割字符串
     * @return String[]
     */
    public static List<String> splitTrimToList(final CharSequence str, final CharSequence seperator) {
        if (str == null) {
            return Collections.emptyList();
        }

        if (seperator == null) {
            return StringUtils.splitToList(str.toString(), 0, true, true);
        } else {
            return StringUtils.splitToList(str.toString(), seperator.toString(), 0, true, true, false);
        }
    }

    /**
     * 字符串截取
     *
     * @param str   字符串
     * @param start 起始索引
     * @return String
     */
    public static String substring(final CharSequence str, int start) {
        if (str == null) {
            return null;
        }

        if (start < 0) {
            start = str.length() + start;
        }

        if (start < 0) {
            start = 0;
        }
        if (start > str.length()) {
            return StringUtils.EMPTY;
        }

        return str.toString().substring(start);
    }

    /**
     * 字符串截取
     *
     * @param str   字符串
     * @param start 起始索引
     * @param end   结束索引
     * @return String
     */
    public static String substring(final CharSequence str, int start, int end) {
        if (str == null) {
            return null;
        }

        if (end < 0) {
            end = str.length() + end;
        }
        if (start < 0) {
            start = str.length() + start;
        }

        if (end > str.length()) {
            end = str.length();
        }

        if (start > end) {
            return StringUtils.EMPTY;
        }

        if (start < 0) {
            start = 0;
        }
        if (end < 0) {
            end = 0;
        }

        return str.toString().substring(start, end);
    }

    /**
     * 字符串截取，等同于 substring
     *
     * @param str   字符串
     * @param start 起始索引
     * @return String
     */
    public static String substr(final CharSequence str, int start) {
        return substring(str, start);
    }

    /**
     * 字符串截取
     *
     * @param str    字符串
     * @param start  起始索引
     * @param length 截取长度
     * @return String
     */
    public static String substr(final CharSequence str, int start, int length) {
        if (str == null) {
            return null;
        }

        int len = str.length();
        int end;

        if (start < 0) {
            start = str.length() + start;
        }

        if (length < 0) {
            end = len + length;
        } else {
            if (start + length >= len) {
                end = len;
            } else {
                end = start + length;
            }
        }

        if (start > end) {
            return StringUtils.EMPTY;
        }

        if (start < 0) {
            start = 0;
        }
        if (end < 0) {
            end = 0;
        }

        return str.toString().substring(start, end);
    }

    /**
     * 以 separator 为分隔符将数组转换为字符串（范型）
     *
     * @param array     数组
     * @param separator 分隔符
     * @return String
     */
    public static <T> String join(T[] array, CharSequence separator) {
        if (array == null) {
            return null;
        }

        if (separator == null) {
            separator = StringUtils.EMPTY;
        }

        final StringBuilder sb = new StringBuilder();
        boolean isFirst = true;
        for (T item : array) {
            if (item != null) {
                if (isFirst) {
                    isFirst = false;
                } else {
                    sb.append(separator);
                }
                sb.append(item);
            }
        }
        return sb.toString();
    }

    /**
     * 以 separator 为分隔符转换为字符串，支持字符串类容器
     *
     * @param iterable  字符串容器
     * @param separator 分隔符
     * @return String
     */
    public static <T> String join(final Iterable<?> iterable, String separator) {
        if (iterable == null) {
            return null;
        }

        return join(iterable.iterator(), separator);
    }

    public static String regexMatch() {
        return null;
    }

    /**
     * 以 separator 为分隔符转换为字符串，支持字符串容器
     *
     * @param iterator  迭代器
     * @param separator 分隔符
     * @return String
     */
    public static String join(final Iterator<?> iterator, String separator) {
        if (iterator == null) {
            return null;
        }

        if (separator == null) {
            separator = StringUtils.EMPTY;
        }

        if (!iterator.hasNext()) {
            return StringUtils.EMPTY;
        }

        final Object first = iterator.next();
        if (!iterator.hasNext()) {
            return Objects.toString(first, "");
        }

        boolean isFirst = true;
        final StringBuilder sb = new StringBuilder();
        if (first != null) {
            sb.append(first);
            isFirst = false;
        }

        while (iterator.hasNext()) {
            final Object obj = iterator.next();
            if (obj != null) {
                if (isFirst) {
                    isFirst = false;
                } else {
                    sb.append(separator);
                }
                sb.append(obj);
            }
        }
        return sb.toString();
    }

    /**
     * 字符串替换
     *
     * @param str        待替换的字符串
     * @param searchStr  查找字符串
     * @param replaceStr 替换字符串
     * @return String
     */
    public static String replace(final CharSequence str, final CharSequence searchStr, final CharSequence replaceStr) {
        return StringUtils.replace(str, searchStr, replaceStr, -1, false);
    }

    /**
     * 字符串替换，忽略大小写
     *
     * @param str        待替换的字符串
     * @param searchStr  查找字符串
     * @param replaceStr 替换字符串
     * @return String
     */
    public static String replaceIgnoreCase(final CharSequence str, final CharSequence searchStr, final CharSequence replaceStr) {
        return StringUtils.replace(str, searchStr, replaceStr, -1, true);
    }

    /**
     * 移除字符串中所有给定字符串
     *
     * @param str    字符串
     * @param remove 被移除的字符串
     * @return String
     */
    public static String remove(final CharSequence str, final CharSequence remove) {
        if (empty(str) || empty(remove)) {
            return str(str);
        }
        return str.toString().replace(remove, StringUtils.EMPTY);
    }

    /**
     * 移除字符串左侧中所有给定字符串
     *
     * @param str    字符串
     * @param remove 被移除的字符串
     * @return String
     */
    public static String removePrefix(final CharSequence str, final CharSequence remove) {
        if (empty(str) || empty(remove)) {
            return str(str);
        }
        if (str.toString().startsWith(remove.toString())) {
            return str.toString().substring(remove.length());
        }
        return str.toString();
    }

    /**
     * 移除字符串右侧中所有给定字符串
     *
     * @param str    字符串
     * @param remove 被移除的字符串
     * @return String
     */
    public static String removeSuffix(final CharSequence str, final CharSequence remove) {
        if (empty(str) || empty(remove)) {
            return str(str);
        }
        if (str.toString().endsWith(remove.toString())) {
            return str.toString().substring(0, str.length() - remove.length());
        }
        return str.toString();
    }

    /**
     * 移除字符串中所有给定字符串列表
     *
     * @param str     字符串
     * @param removes 被移除的字符串，一个或多个
     * @return String
     */
    public static String removeAny(final CharSequence str, final CharSequence... removes) {
        String result = str(str);
        if (!empty(str)) {
            for (CharSequence remove : removes) {
                result = remove(result, remove);
            }
        }
        return result;
    }

    /**
     * 去除字符串中指定的多个字符集
     *
     * @param str   字符串
     * @param chars 字符列表
     * @return String
     */
    public static String removeAny(final CharSequence str, char... chars) {
        if (null == str || empty(chars)) {
            return str(str);
        }
        final int len = str.length();
        if (0 == len) {
            return str(str);
        }
        final StringBuilder builder = new StringBuilder(len);
        char c;
        for (int i = 0; i < len; i++) {
            c = str.charAt(i);
            if (!ArrayUtils.contains(chars, c)) {
                builder.append(c);
            }
        }
        return builder.toString();
    }

    /**
     * 去除字符串中换行符
     *
     * @param str 字符串
     * @return String
     */
    public static String removeLines(final CharSequence str) {
        return removeAny(str, CharUtils.CR, CharUtils.LF);
    }

    /**
     * 返回默认日期时间字符串
     * <p>
     * 默认格式：yyyy-MM-dd HH:mm:ss
     *
     * @return String
     */
    public static String date() {
        return date(DatePattern.DATETIME_PATTERN);
    }

    /**
     * 根据时间戳，返回默认日期时间字符串
     * <p>
     * 默认格式：yyyy-MM-dd HH:mm:ss
     *
     * @param timestamp 时间戳
     * @return String
     */
    public static String date(final long timestamp) {
        return date(DatePattern.DATETIME_PATTERN, timestamp);
    }

    /**
     * 根据格式化参数返回日期时间字符串
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
     * @param pattern   日期时间格式
     * @param timestamp 时间戳
     * @return String
     */
    public static String date(final String pattern, final long timestamp) {
        return LocalDateTime.ofInstant(Instant.ofEpochSecond(Math.abs(timestamp)), ZoneId.systemDefault())
                .format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 返回当前 Unix 时间戳
     *
     * @return long
     */
    public static long strtotime() {
        return Instant.now().getEpochSecond();
    }

    /**
     * 将通用格式的日期时间字符串，自动为转换时间戳
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
     * @param expString 时期时间字符串
     * @param timestamp 时间戳
     * @return long
     */
    public static long strtotime(final String expString, final Long timestamp) {
        return Strtotime.parse(expString, timestamp);
    }

    /**
     * 获得指定范围内的随机数
     * <p>
     * 默认 [0,Integer.MAX_VALUE)
     *
     * @return int
     */
    public static int random() {
        return RandomUtils.randomInt(Integer.MAX_VALUE);
    }

    /**
     * 获得指定范围内的随机数 [0, max)
     *
     * @param max 限制随机数的范围
     * @return int
     */
    public static int randomInt(int max) {
        return RandomUtils.randomInt(max);
    }

    /**
     * 获得指定范围内的随机数 [0, max)
     *
     * @param max 限制随机数的范围
     * @return 随机数
     */
    public static long randomLong(long max) {
        return RandomUtils.randomLong(max);
    }

    /**
     * 获得一个指定长度随机的字符串（只包含数字和字符）
     *
     * @param length 字符串的长度
     * @return String
     */
    public static String randomString(int length) {
        return RandomUtils.randomString(length);
    }

    /**
     * 获得一个指定长度随机的字符串，排除指定的字符串列表集
     *
     * @param length 字符串的长度
     * @return String
     */
    public static String randomStringExcepts(int length, String... excepts) {
        return RandomUtils.randomStringExcept(length, excepts);
    }

    /**
     * 获得一个指定长度只包含数字的随机字符串
     *
     * @param length 字符串的长度
     * @return String
     */
    public static String randomStringNumber(int length) {
        return RandomUtils.randomStringNumber(length);
    }

    /**
     * 获得一个指定长度只包含字符的随机字符串
     *
     * @param length 字符串的长度
     * @return String
     */
    public static String randomStringChar(int length) {
        return RandomUtils.randomStringChar(length);
    }

    /**
     * base64Encode
     *
     * @param data byte数组
     * @return String
     */
    public static String base64Encode(final byte[] data) {
        return Base64.getEncoder().encodeToString(data);
    }

    /**
     * base64Encode
     *
     * @param str 字符串
     * @return String
     */
    public static String base64Encode(final String str) {
        return base64Encode(bytes(str));
    }

    /**
     * base64Decode
     *
     * @param str Base64字符串
     * @return String
     */
    public static String base64Decode(final String str) {
        return new String(Base64.getDecoder().decode(str));
    }

    /**
     * base64URLEncode
     * <p>
     * 会将 +、/ 替换为 -、_
     *
     * @param data byte数组
     * @return String
     */
    public static String base64URLEncode(final byte[] data) {
        return Base64.getUrlEncoder().encodeToString(data);
    }

    /**
     * base64URLEncode
     * <p>
     * 会将 +、/ 替换为 -、_
     *
     * @param str 字符串
     * @return String
     */
    public static String base64URLEncode(final String str) {
        return base64URLEncode(bytes(str));
    }

    /**
     * base64URLDecode
     *
     * @param str Base64字符串
     * @return String
     */
    public static String base64URLDecode(final String str) {
        return new String(Base64.getUrlDecoder().decode(str));
    }

    /**
     * MD5
     *
     * @param data 字节数组
     * @return String
     */
    public static String md5(final byte[] data) {
        return DigestUtils.md5Hex(data);
    }

    /**
     * MD5
     *
     * @param str 字符串
     * @return String
     */
    public static String md5(final String str) {
        return DigestUtils.md5Hex(str);
    }

    /**
     * SHA1
     *
     * @param data 字节数组
     * @return String
     */
    public static String sha1(final byte[] data) {
        return DigestUtils.sha1Hex(data);
    }

    /**
     * SHA1
     *
     * @param str 字符串
     * @return String
     */
    public static String sha1(final String str) {
        return DigestUtils.sha1Hex(str);
    }

    /**
     * SHA256
     *
     * @param data 字节数组
     * @return String
     */
    public static String sha256(final byte[] data) {
        return DigestUtils.sha256Hex(data);
    }

    /**
     * SHA256
     *
     * @param str 字符串
     * @return String
     */
    public static String sha256(final String str) {
        return DigestUtils.sha256Hex(str);
    }

    /**
     * SHA384
     *
     * @param data 字节数组
     * @return String
     */
    public static String sha384(final byte[] data) {
        return DigestUtils.sha384Hex(data);
    }

    /**
     * SHA384
     *
     * @param str 字符串
     * @return String
     */
    public static String sha384(final String str) {
        return DigestUtils.sha384Hex(str);
    }

    /**
     * SHA512
     *
     * @param data 字节数组
     * @return String
     */
    public static String sha512(final byte[] data) {
        return DigestUtils.sha512Hex(data);
    }

    /**
     * SHA512
     *
     * @param str 字符串
     * @return String
     */
    public static String sha512(final String str) {
        return DigestUtils.sha512Hex(str);
    }
}
