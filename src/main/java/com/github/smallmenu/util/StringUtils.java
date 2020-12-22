package com.github.smallmenu.util;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * StrUtils
 *
 * @author smallmenu
 */
public class StringUtils {
    public static final String EMPTY = "";
    public static final String EMPTY_JSON = "{}";

    public static final String SPACE = " ";
    public static final String TAB = "	";

    public static final String DOT = ".";
    public static final String DOUBLE_DOT = "..";
    public static final String SLASH = "/";
    public static final String BACKSLASH = "\\";
    public static final String UNDERLINE = "_";
    public static final String DASHED = "-";
    public static final String COMMA = ",";

    public static final String CR = "\r";
    public static final String LF = "\n";
    public static final String CRLF = "\r\n";

    public static final String HTML_NBSP = "&nbsp;";
    public static final String HTML_AMP = "&amp;";
    public static final String HTML_LT = "&lt;";
    public static final String HTML_GT = "&gt;";

    /**
     * 禁止实例化
     */
    private StringUtils() {
        throw new AssertionError();
    }

    /**
     * 检测对象是否为字符串类
     *
     * @param value 被检查的对象
     * @return boolean
     */
    public static boolean isString(Object value) {
        return value instanceof CharSequence;
    }

    /**
     * 除去字符串空白，如果字符串是null，依然返回null。
     *
     * @param str  待处理字符串
     * @param mode 模式（左侧-1、全部0、右侧1）
     * @return
     */
    public static String trim(CharSequence str, int mode) {
        if (str == null) {
            return null;
        }

        int length = str.length();
        int start = 0;
        int end = length;

        // 扫描字符串头部
        if (mode <= 0) {
            while ((start < end) && (CharUtils.isBlankChar(str.charAt(start)))) {
                start++;
            }
        }

        // 扫描字符串尾部
        if (mode >= 0) {
            while ((start < end) && (CharUtils.isBlankChar(str.charAt(end - 1)))) {
                end--;
            }
        }

        if ((start > 0) || (end < length)) {
            return str.toString().substring(start, end);
        }

        return str.toString();
    }

    /**
     * 是否以指定字符串开头
     * <p>
     * 如果给定的字符串和开头字符串都为 null 则返回 true ，否则任意一个值为 null 返回 false
     * 如果给定的字符串不为 null，开头字符串为 "" 空字符串，返回true
     *
     * @param str          被监测字符串
     * @param prefix       开头字符串
     * @param isIgnoreCase 是否忽略大小写
     * @return boolean
     */
    public static boolean startWith(CharSequence str, CharSequence prefix, boolean isIgnoreCase) {
        if (null == str || null == prefix) {
            return null == str && null == prefix;
        }

        if (isIgnoreCase) {
            return str.toString().toLowerCase().startsWith(prefix.toString().toLowerCase());
        } else {
            return str.toString().startsWith(prefix.toString());
        }
    }

    /**
     * 是否以指定字符串结尾
     * <p>
     * 如果给定的字符串和开头字符串都为 null 则返回true，否则任意一个值为 null 返回 false
     * 如果给定的字符串不为 null，开头字符串为 "" 空字符串，返回true
     *
     * @param str          被监测字符串
     * @param suffix       结尾字符串
     * @param isIgnoreCase 是否忽略大小写
     * @return boolean
     */
    public static boolean endWith(CharSequence str, CharSequence suffix, boolean isIgnoreCase) {
        if (null == str || null == suffix) {
            return null == str && null == suffix;
        }

        if (isIgnoreCase) {
            return str.toString().toLowerCase().endsWith(suffix.toString().toLowerCase());
        } else {
            return str.toString().endsWith(suffix.toString());
        }
    }

    /**
     * 比较两个字符串是否相等
     * <p>
     * 如果两个字符串相同，或者都是 null，则返回 true
     *
     * @param str1       要比较的字符串1
     * @param str2       要比较的字符串2
     * @param ignoreCase 是否忽略大小写
     * @return boolean
     */
    public static boolean equals(CharSequence str1, CharSequence str2, boolean ignoreCase) {
        if (null == str1) {
            // 只有两个都为 null 才判断相等
            return str2 == null;
        }
        if (null == str2) {
            // 字符串2空，字符串1非空，直接false
            return false;
        }

        if (ignoreCase) {
            return str1.toString().equalsIgnoreCase(str2.toString());
        } else {
            return str1.toString().contentEquals(str2);

        }
    }

    /**
     * 字符串获取 Bytes，UTF8
     * 如果字符串为 null，返回 null
     *
     * @param str
     * @return byte[]
     */
    public static byte[] getBytesUtf8(final CharSequence str) {
        return getBytes(str, StandardCharsets.UTF_8);
    }

    /**
     * 字符串获取 Bytes
     * 如果字符串为 null，返回 null
     *
     * @param str
     * @param charset
     * @return byte[]
     */
    public static byte[] getBytes(final CharSequence str, final Charset charset) {
        if (str == null) {
            return null;
        }

        return str.toString().getBytes(charset);
    }
}
