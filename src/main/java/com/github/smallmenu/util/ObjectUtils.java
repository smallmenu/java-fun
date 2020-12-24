package com.github.smallmenu.util;

import java.util.Objects;

/**
 * ObjectUtils
 *
 * @author smallmenu
 */
public class ObjectUtils {
    private ObjectUtils() {
        throw new AssertionError();
    }

    public static boolean equal(Object obj1, Object obj2) {
        return Objects.equals(obj1, obj2);
    }
}
