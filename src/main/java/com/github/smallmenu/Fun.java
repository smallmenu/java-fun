package com.github.smallmenu;

import com.github.smallmenu.constant.DatePattern;
import com.github.smallmenu.date.Strtotime;
import com.github.smallmenu.util.*;

import java.lang.reflect.Array;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Base64;

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
     * 检测是否为空，（可匹配单一对象）
     *
     * 如果对象为 null，返回 true
     * 如果对象为数组，判断数组长度
     * 如果对象为非数组，判断是否为0
     * 其他，返回 false
     *
     * @param object 数组
     * @return boolean
     */
    public static boolean empty(Object object) {
        if (object != null) {
            if (ArrayUtils.isArray(object)) {
                return 0 == Array.getLength(object);
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
            return false;
        }
        return true;
    }

    /**
     * 检测是否为空。（null、""）
     *
     * @param str 待检测字符串
     * @return boolean
     */
    public static boolean empty(final CharSequence str) {
        return str == null || str.length() == 0;
    }

    /**
     * 检测是否为空。数组范型
     *
     * @param array 数组
     * @param <T>   T
     * @return boolean
     */
    public static <T> boolean empty(T[] array) {
        return array == null || array.length == 0;
    }

    /**
     * 检测字符串是否全部为空
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
     * 检测字符串是否为空白。（null、""、" "、不可见字符如空格）
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
     * 检测字符串是否全部为空白
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
     * 和 String.trim 不同，此方法使用 CharUtil.isBlankChar  来判定空白
     * 因而可以除去英文字符集之外的其它空白，如中文空格。
     *
     * @param str 待处理字符串
     * @return String
     */
    public static String trim(final String str) {
        return (null == str) ? null : StringUtils.trim(str, 0);
    }

    /**
     * 除去字符串左侧的空白
     *
     * @param str 待处理字符串
     * @return String
     */
    public static String ltrim(final String str) {
        return (null == str) ? null : StringUtils.trim(str, -1);
    }

    /**
     * 除去字符串右侧的空白
     *
     * @param str 待处理字符串
     * @return String
     */
    public static String rtrim(final String str) {
        return (null == str) ? null : StringUtils.trim(str, 1);
    }

    /**
     * 除去字符串头尾部的空白
     * <p>
     * 如果是 null，返回 ""
     *
     * @param str 待处理字符串
     * @return String
     */
    public static String trimToEmpty(final String str) {
        return str == null ? StringUtils.EMPTY : trim(str);
    }

    /**
     * 除去字符串头尾部的空白
     * <p>
     * 如果字符串是 null 或者 "" ，返回  null。
     *
     * @param str 待处理字符串
     * @return String
     */
    public static String trimToNull(final String str) {
        final String trimStr = trim(str);
        return StringUtils.EMPTY.equals(trimStr) ? null : trimStr;
    }

    /**
     * 字符串转整型
     *
     * 如果是 null 或者 ""，返回 0
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
     *
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
     * 如果是 null 或者 ""，返回 0
     * 如果是小数，返回 0
     *
     * @param str 字符串
     * @return int
     */
    public static int intVal(final String str) {
        return toInt(str);
    }

    /**
     * 字符串转整型
     * <p>
     * 如果是  null 或者 ""，返回 0
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
     * 如果是  null 或者 "" 或者失败，返回默认值
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
     * 查找字符串是否包含
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
     * 查找字符串是否包含
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
     * 查找指定字符串是否包含指定字符列表中的任意一个字符
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
     * 比较两个字符串（大小写不敏感）
     *
     * @param str1 要比较的字符串1
     * @param str2 要比较的字符串2
     * @return boolean
     */
    public static boolean equalsIgnoreCase(final CharSequence str1, final CharSequence str2) {
        return StringUtils.equals(str1, str2, true);
    }

    /**
     * 是否以指定字符串开头
     * <p>
     * 如果给定的字符串和开头字符串都为 null 则返回 true ，否则任意一个值为 null 返回 false
     * 如果给定的字符串不为 null，开头字符串为 "" 空字符串，返回true
     *
     * @param str    被监测字符串
     * @param prefix 开头字符串
     * @return boolean
     */
    public static boolean startWith(final CharSequence str, final CharSequence prefix) {
        return StringUtils.startWith(str, prefix, false);
    }

    /**
     * 是否以指定字符串开头，忽略大小写
     *
     * @param str    被监测字符串
     * @param prefix 开头字符串
     * @return boolean
     */
    public static boolean startWithIgnoreCase(final CharSequence str, final CharSequence prefix) {
        return StringUtils.startWith(str, prefix, true);
    }

    /**
     * 是否以指定字符串结尾
     *
     * @param str    被监测字符串
     * @param suffix 结尾字符串
     * @return boolean
     */
    public static boolean endWith(final CharSequence str, final CharSequence suffix) {
        return StringUtils.endWith(str, suffix, false);
    }

    /**
     * 是否以指定字符串结尾，忽略大小写
     *
     * @param str    被监测字符串
     * @param suffix 结尾字符串
     * @return boolean
     */
    public static boolean endWithIgnoreCase(final CharSequence str, final CharSequence suffix) {
        return StringUtils.endWith(str, suffix, true);
    }

    /**
     * 获取字符串的长度，如果为 null 返回 0
     *
     * @param str 字符串
     * @return int
     */
    public static int strlen(final CharSequence str) {
        return str == null ? 0 : str.length();
    }

    /**
     * CharSequence toString()
     *
     * @param cs
     * @return
     */
    public static String str(final CharSequence cs) {
        return null == cs ? null : cs.toString();
    }

    /**
     * 移除字符串中所有给定字符串
     *
     * @param str         字符串
     * @param remove 被移除的字符串
     * @return String
     */
    public static String remove(final CharSequence str, final CharSequence remove) {
        if (empty(str)) {
            return str(str);
        }
        return str.toString().replace(remove, StringUtils.EMPTY);
    }

    public static String removeStart() {
        return null;
    }

    public static String removeEnd() {
        return null;
    }

    /**
     * 移除字符串中所有给定字符串
     *
     * @param str     字符串
     * @param removes 被移除的字符串，一个或多个
     * @return String
     */
    public static String removeAny(final CharSequence str, final CharSequence... removes) {
        String result = str(str);
        if (empty(str)) {
            for (CharSequence remove : removes) {
                result = remove(result, remove);
            }
        }
        return result;
    }

    /**
     * 去除字符串中指定的多个字符，如有多个则全部去除
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

    public static String removeLines(final CharSequence str) {
        return removeAny(str, CharUtils.CR, CharUtils.LF);
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
     * @param pattern   日期时间格式
     * @param timestamp 时间戳
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

    /**
     * 获得指定范围内的随机数 [0,limit)
     *
     * @param max 限制随机数的范围，不包括这个数
     * @return int
     */
    public static int randomInt(int max) {
        return RandomUtils.randomInt(max);
    }

    /**
     * 获得指定范围内的随机数 [0,limit)
     *
     * @param max 限制随机数的范围，不包括这个数
     * @return 随机数
     */
    public static long randomLong(long max) {
        return RandomUtils.randomLong(max);
    }

    /**
     * 获得一个随机的字符串（只包含数字和字符）
     *
     * @param length 字符串的长度
     * @return String
     */
    public static String randomString(int length) {
        return RandomUtils.randomString(length);
    }

    /**
     * 获得一个随机的字符串，排除指定的字符串集
     *
     * @param length 字符串的长度
     * @return String
     */
    public static String randomStringExcepts(int length, String... excepts) {
        return RandomUtils.randomStringExcept(length, excepts);
    }

    /**
     * 获得一个只包含数字的字符串
     *
     * @param length 字符串的长度
     * @return String
     */
    public static String randomStringNumber(int length) {
        return RandomUtils.randomStringNumber(length);
    }

    /**
     * 获得一个只包含字符的字符串
     *
     * @param length 字符串的长度
     * @return String
     */
    public static String randomStringChar(int length) {
        return RandomUtils.randomStringChar(length);
    }

    public static String base64Encode(final byte[] data) {
        return Base64.getEncoder().encodeToString(data);
    }

    public static String base64Encode(final String str) {
        return base64Encode(StringUtils.getBytes(str));
    }

    public static String base64Encode(final CharSequence str) {
        return base64Encode(str(str).getBytes());
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
     * MD5
     *
     * @param str 字符串
     * @return String
     */
    public static String md5(final CharSequence str) {
        return DigestUtils.md5Hex(str.toString());
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
     * SHA1
     *
     * @param str 字符串
     * @return String
     */
    public static String sha1(final CharSequence str) {
        return DigestUtils.sha1Hex(str.toString());
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
     * SHA256
     *
     * @param str 字符串
     * @return String
     */
    public static String sha256(final CharSequence str) {
        return DigestUtils.sha256Hex(str.toString());
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
     * SHA384
     *
     * @param str 字符串
     * @return String
     */
    public static String sha384(final CharSequence str) {
        return DigestUtils.sha384Hex(str.toString());
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

    /**
     * SHA512
     *
     * @param str 字符串
     * @return String
     */
    public static String sha512(final CharSequence str) {
        return DigestUtils.sha512Hex(str.toString());
    }
}
