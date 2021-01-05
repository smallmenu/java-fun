package com.github.smallmenu.fun;

import java.util.Objects;

/**
 * ObjectUtils
 *
 * @author smallmenu
 */
public class ObjectFun {
    private ObjectFun() {
        throw new AssertionError();
    }

    public static boolean equal(Object obj1, Object obj2) {
        return Objects.equals(obj1, obj2);
    }
}
