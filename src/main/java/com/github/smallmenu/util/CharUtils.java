package com.github.smallmenu.util;

/**
 * CharUtils
 *
 * @author smallmenu
 */
public class CharUtils {
    public static boolean isBlankChar(int c) {
        return Character.isWhitespace(c)
                || Character.isSpaceChar(c)
                || c == '\ufeff'
                || c == '\u202a';
    }
}
