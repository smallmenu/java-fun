package com.github.smallmenu.fun;

import com.github.smallmenu.Fun;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.github.smallmenu.Fun.*;

/**
 * StringUtils
 *
 * @author smallmenu
 */
public class StringFun {
    /**
     *
     */
    public static final int INDEX_NOT_FOUND = -1;

    /**
     *
     */
    public static final String EMPTY = "";

    /**
     *
     */
    public static final String EMPTY_JSON = "{}";

    /**
     *
     */
    public static final String SPACE = " ";

    /**
     *
     */
    public static final String TAB = "	";

    /**
     *
     */
    public static final String DOT = ".";

    /**
     *
     */
    public static final String DOUBLE_DOT = "..";

    /**
     *
     */
    public static final String SLASH = "/";

    /**
     *
     */
    public static final String BACKSLASH = "\\";

    /**
     *
     */
    public static final String UNDERLINE = "_";

    /**
     *
     */
    public static final String DASHED = "-";

    /**
     *
     */
    public static final String COMMA = ",";

    /**
     *
     */
    public static final String CR = "\r";

    /**
     *
     */
    public static final String LF = "\n";

    /**
     *
     */
    public static final String CRLF = "\r\n";

    /**
     *
     */
    public static final String HTML_NBSP = "&nbsp;";

    /**
     *
     */
    public static final String HTML_AMP = "&amp;";

    /**
     *
     */
    public static final String HTML_LT = "&lt;";

    /**
     *
     */
    public static final String HTML_GT = "&gt;";

    /**
     *
     */
    public static final int PAD_LIMIT = 8192;

    /**
     * 禁止实例化
     */
    private StringFun() {
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
     * 除去两侧字符串空白，如果字符串是 null，依然返回 null
     *
     * @param str 待处理字符串
     * @return String
     */
    public static String trim(final CharSequence str) {
        return trim(str, null, 0);
    }

    /**
     * 除去字符串空白，如果字符串是null，依然返回null
     *
     * @param str  待处理字符串
     * @param mode 模式（左侧-1、全部0、右侧1）
     * @return String
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
            if (trimStr == null) {
                while ((start < end) && (CharFun.isBlankChar(str.charAt(start)))) {
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
            if (trimStr == null) {
                while ((start < end) && (CharFun.isBlankChar(str.charAt(end - 1)))) {
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
            return str2 == null;
        }
        if (null == str2) {
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
            return toStr(str);
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

    /**
     * 分割字符串为列表
     *
     * @param str    字符串
     * @param limit   次数
     * @param isTrim   是否trim
     * @param ignoreEmpty  是否忽略空值
     * @return List
     */
    public static List<String> splitToList(String str, int limit, boolean isTrim, boolean ignoreEmpty) {
        if (empty(str)) {
            return Collections.emptyList();
        }
        if (limit == 1) {
            return splitAddList(new ArrayList<>(1), str, isTrim, ignoreEmpty);
        }

        final ArrayList<String> list = new ArrayList<>();
        int len = str.length();
        int start = 0;
        for (int i = 0; i < len; i++) {
            if (CharFun.isBlankChar(str.charAt(i))) {
                splitAddList(list, str.substring(start, i), isTrim, ignoreEmpty);
                start = i + 1;

                if (limit > 0 && list.size() > limit - 2) {
                    break;
                }
            }
        }
        return splitAddList(list, str.substring(start, len), true, true);
    }

    /**
     * 分割字符串为列表
     *
     * @param str   字符串
     * @param separator    分隔符
     * @param limit     次数
     * @param isTrim    是否trim
     * @param ignoreEmpty  是否忽略空值
     * @param ignoreCase   是否忽略大小写
     * @return List
     */
    public static List<String> splitToList(String str, char separator, int limit, boolean isTrim, boolean ignoreEmpty, boolean ignoreCase) {
        if (empty(str)) {
            return Collections.emptyList();
        }
        if (limit == 1) {
            return splitAddList(new ArrayList<>(1), str, isTrim, ignoreEmpty);
        }

        final ArrayList<String> list = new ArrayList<>(limit > 0 ? limit : 16);
        int len = str.length();
        int start = 0;
        for (int i = 0; i < len; i++) {
            if (CharFun.equals(separator, str.charAt(i), ignoreCase)) {
                splitAddList(list, str.substring(start, i), isTrim, ignoreEmpty);
                start = i + 1;

                if (limit > 0 && list.size() > limit - 2) {
                    break;
                }
            }
        }
        return splitAddList(list, str.substring(start, len), isTrim, ignoreEmpty);
    }

    /**
     * 分割字符串为列表
     *
     * @param str   字符串
     * @param separator    分隔字符串
     * @param limit     次数
     * @param isTrim    是否trim
     * @param ignoreEmpty  是否忽略空值
     * @param ignoreCase   是否忽略大小写
     * @return List
     */
    public static List<String> splitToList(String str, String separator, int limit, boolean isTrim, boolean ignoreEmpty, boolean ignoreCase) {
        if (empty(str)) {
            return Collections.emptyList();
        }

        if (limit == 1) {
            return splitAddList(new ArrayList<>(1), str, isTrim, ignoreEmpty);
        }

        if (empty(separator)) {
            return splitToList(str, limit, isTrim, ignoreEmpty);
        } else if (separator.length() == 1) {
            return splitToList(str, separator.charAt(0), limit, isTrim, ignoreEmpty, ignoreCase);
        }

        final List<String> list = new ArrayList<>();
        int len = str.length();
        int separatorLen = separator.length();
        int start = 0;
        int i = 0;
        while (i < len) {
            i = indexOf(str, separator, start, ignoreCase);
            if (i > -1) {
                splitAddList(list, str.substring(start, i), isTrim, ignoreEmpty);
                start = i + separatorLen;

                if (limit > 0 && list.size() > limit - 2) {
                    break;
                }
            } else {
                break;
            }
        }
        return splitAddList(list, str.substring(start, len), isTrim, ignoreEmpty);
    }

    /**
     * 分割字符串为数组
     *
     * @param str   字符串
     * @param limit   次数
     * @param isTrim    是否trim
     * @param ignoreEmpty  是否忽略空值
     * @return String[]
     */
    public static String[] split(String str, int limit, boolean isTrim, boolean ignoreEmpty) {
        List<String> list = splitToList(str, limit, isTrim, ignoreEmpty);
        return list.toArray(ArrayFun.EMPTY_STRING);
    }

    /**
     * 分割字符串为数组
     *
     * @param str   字符串
     * @param separator    分隔字符串
     * @param limit     次数
     * @param isTrim    是否trim
     * @param ignoreEmpty  是否忽略空值
     * @param ignoreCase   是否忽略大小写
     * @return String[]
     */
    public static String[] split(String str, String separator, int limit, boolean isTrim, boolean ignoreEmpty, boolean ignoreCase) {
        List<String> list = splitToList(str, separator, limit, isTrim, ignoreEmpty, ignoreCase);
        return list.toArray(ArrayFun.EMPTY_STRING);
    }

    /**
     * 分割字符串为数组
     *
     * @param str   字符串
     * @param separator    分隔字符
     * @param limit     次数
     * @param isTrim    是否trim
     * @param ignoreEmpty  是否忽略空值
     * @param ignoreCase   是否忽略大小写
     * @return
     */
    public static String[] split(String str, char separator, int limit, boolean isTrim, boolean ignoreEmpty, boolean ignoreCase) {
        List<String> list = splitToList(str, separator, limit, isTrim, ignoreEmpty, ignoreCase);
        return list.toArray(ArrayFun.EMPTY_STRING);
    }

    /**
     * 将 split 后字符串加入 List
     *
     * @param list        列表
     * @param str         字符串
     * @param trim        是否 trim
     * @param ignoreEmpty 是否去除空
     * @return List
     */
    private static List<String> splitAddList(List<String> list, String str, boolean trim, boolean ignoreEmpty) {
        if (trim) {
            str = Fun.trim(str);
        }

        if (!ignoreEmpty || !empty(str)) {
            list.add(str);
        }

        return list;
    }
}
