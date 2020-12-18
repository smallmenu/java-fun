package com.github.smallmenu.util;

import org.junit.Assert;
import org.junit.Test;

public class StringUtilsTest {

    @Test
    public void isString() {
        String a = "";
        String b = null;
        Integer c = 1;
        int d = 3;
        Assert.assertTrue(StringUtils.isString(a));
        Assert.assertFalse(StringUtils.isString(b));
        Assert.assertFalse(StringUtils.isString(c));
        Assert.assertFalse(StringUtils.isString(d));
    }
}
