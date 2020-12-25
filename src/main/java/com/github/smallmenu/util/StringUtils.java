package com.github.smallmenu.util;

import static com.github.smallmenu.Fun.*;

/**
 * StringUtils
 *
 * @author smallmenu
 */
public class StringUtils {
    public static final int INDEX_NOT_FOUND = -1;

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
     * 指定范围内查找字符串
     *
     * @param str        字符串
     * @param searchStr  需要查找位置的字符串
     * @param start      起始位置
     * @param ignoreCase 是否忽略大小写
     * @return 位置
     * @since 3.2.1
     */
    public static int indexOf(final CharSequence str, final CharSequence searchStr, int start, boolean ignoreCase) {
        if (str == null || searchStr == null) {
            return INDEX_NOT_FOUND;
        }
        if (start < 0) {
            start = 0;
        }

        final int endLimit = str.length() - searchStr.length() + 1;
        if (start > endLimit) {
            return INDEX_NOT_FOUND;
        }
        if (searchStr.length() == 0) {
            return start;
        }

        if (!ignoreCase) {
            return str.toString().indexOf(searchStr.toString(), start);
        }

        for (int i = start; i < endLimit; i++) {
            if (isSubEquals(str, i, searchStr, 0, searchStr.length(), true)) {
                return i;
            }
        }
        return INDEX_NOT_FOUND;
    }

    /**
     * 截取两个字符串的不同部分（长度一致），判断截取的子串是否相同
     * <p>
     * 任意一个字符串为 null 返回false
     *
     * @param str1       第一个字符串
     * @param start1     第一个字符串开始的位置
     * @param str2       第二个字符串
     * @param start2     第二个字符串开始的位置
     * @param length     截取长度
     * @param ignoreCase 是否忽略大小写
     * @return boolean
     */
    public static boolean isSubEquals(final CharSequence str1, int start1, CharSequence str2, int start2, int length, boolean ignoreCase) {
        if (null == str1 || null == str2) {
            return false;
        }

        return str1.toString().regionMatches(ignoreCase, start1, str2.toString(), start2, length);
    }

    /**
     * 除去字符串空白，如果字符串是null，依然返回null。
     *
     * @param str  待处理字符串
     * @param mode 模式（左侧-1、全部0、右侧1）
     * @return
     */
    public static String trim(final CharSequence str, final CharSequence trimStr, int mode) {
        if (str == null) {
            return null;
        }

        int length = str.length();
        int start = 0;
        int end = length;

        // 扫描字符串头部
        if (mode <= 0) {
            if (empty(trimStr)) {
                while ((start < end) && (CharUtils.isBlankChar(str.charAt(start)))) {
                    start++;
                }
            } else {
                while ((start < end) && (trimStr.toString().indexOf(str.charAt(start)) != INDEX_NOT_FOUND)) {
                    start++;
                }
            }
        }

        // 扫描字符串尾部
        if (mode >= 0) {
            if (empty((trimStr))) {
                while ((start < end) && (CharUtils.isBlankChar(str.charAt(end - 1)))) {
                    end--;
                }
            } else {
                while ((start < end) && (trimStr.toString().indexOf(str.charAt(end - 1)) != INDEX_NOT_FOUND)) {
                    end--;
                }
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
    public static boolean startsWith(final CharSequence str, final CharSequence prefix, boolean isIgnoreCase) {
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
    public static boolean endsWith(final CharSequence str, final CharSequence suffix, boolean isIgnoreCase) {
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
    public static boolean equals(final CharSequence str1, final CharSequence str2, boolean ignoreCase) {
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
     * 字符串替换
     *
     * @param str        待替换的字符串
     * @param searchStr  查找字符串
     * @param replaceStr 替换字符串
     * @param max        替换次数
     * @param ignoreCase 是否忽略大小写
     * @return String
     */
    public static String replace(final CharSequence str, CharSequence searchStr, final CharSequence replaceStr, int max, final boolean ignoreCase) {
        if (empty(str) || empty(searchStr) || replaceStr == null || max == 0) {
            return str(str);
        }

        if (ignoreCase) {
            searchStr = searchStr.toString().toLowerCase();
        }
        int start = 0;
        int end = ignoreCase ? indexOfIgnoreCase(str, searchStr, start) : indexOf(str, searchStr, start, false);
        if (end == INDEX_NOT_FOUND) {
            return str.toString();
        }
        final int replLength = searchStr.length();
        int increase = Math.max(searchStr.length() - replLength, 0);
        increase *= max < 0 ? 16 : Math.min(max, 64);
        final StringBuilder buf = new StringBuilder(str.length() + increase);
        while (end != INDEX_NOT_FOUND) {
            buf.append(str, start, end).append(replaceStr);
            start = end + replLength;
            if (--max == 0) {
                break;
            }
            end = ignoreCase ? indexOfIgnoreCase(str, searchStr, start) : indexOf(str, searchStr, start, false);
        }
        buf.append(str, start, str.length());
        return buf.toString();
    }
}
