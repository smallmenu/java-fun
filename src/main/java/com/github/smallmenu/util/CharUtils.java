package com.github.smallmenu.util;

/**
 * CharUtils
 *
 * @author smallmenu
 */
public class CharUtils {
    public static final char SPACE = ' ';
    public static final char TAB = '	';
    public static final char DOT = '.';
    public static final char SLASH = '/';
    public static final char BACKSLASH = '\\';
    public static final char CR = '\r';
    public static final char LF = '\n';
    public static final char UNDERLINE = '_';
    public static final char DASHED = '-';
    public static final char COMMA = ',';
    public static final char AMP = '&';
    public static final char COLON = ':';

    /**
     * 禁止实例化
     */
    private CharUtils() {
        throw new AssertionError();
    }

    /**
     * 检测对象是否为字符类
     *
     * @param value 被检查的对象
     * @return boolean
     */
    public static boolean isChar(Object value) {
        return value instanceof Character || value.getClass() == char.class;
    }

    /**
     * 比较两个字符是否相等
     *
     * @param c1
     * @param c2
     * @param ignoreCase
     * @return
     */
    public static boolean equals(char c1, char c2, boolean ignoreCase) {
        if (ignoreCase) {
            return Character.toLowerCase(c1) == Character.toLowerCase(c2);
        }
        return c1 == c2;
    }

    /**
     * 是否空白字符
     * <p>
     * 空白字符包括空格、制表符、全角空格和不间断空格
     *
     * @param c 字符
     * @return 是否空白符
     * @see Character#isWhitespace(int)
     * @see Character#isSpaceChar(int)
     */
    public static boolean isBlankChar(char c) {
        return isBlankChar((int) c);
    }

    /**
     * 是否空白字符
     *
     * 空白字符包括空格、制表符、全角空格和不间断空格
     *
     * @param c 字符
     * @return 是否空白符
     * @see Character#isWhitespace(int)
     * @see Character#isSpaceChar(int)
     */
    public static boolean isBlankChar(int c) {
        return Character.isWhitespace(c)
                || Character.isSpaceChar(c)
                || c == '\ufeff'
                || c == '\u202a';
    }
}
