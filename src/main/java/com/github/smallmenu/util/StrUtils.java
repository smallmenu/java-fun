package com.github.smallmenu.util;

/**
 * StrUtil
 *
 * @author smallmenu
 */
public class StrUtils {
    public static final String EMPTY = "";
    public static final String EMPTY_JSON = "{}";

    public static final String SPACE = " ";
    public static final String TAB = "	";

    public static final String DOT = ".";
    public static final String DOUBLE_DOT = "..";
    public static final String SLASH = "/";

    public static final String CR = "\r";
    public static final String LF = "\n";
    public static final String CRLF = "\r\n";

    /**
     * 除去字符串空白，如果字符串是<code>null</code>，依然返回<code>null</code>。
     *
     * @param str  待处理字符串
     * @param mode 模式（左侧、全部、右侧）
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
     * 如果两个字符串相同，或者都是<code>null</code>，则返回<code>true</code>
     *
     * @param str1       要比较的字符串1
     * @param str2       要比较的字符串2
     * @param ignoreCase 是否忽略大小写
     * @return boolean
     */
    public static boolean equals(CharSequence str1, CharSequence str2, boolean ignoreCase) {
        if (null == str1) {
            // 只有两个都为null才判断相等
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
}
