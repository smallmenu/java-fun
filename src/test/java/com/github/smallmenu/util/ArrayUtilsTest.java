package com.github.smallmenu.util;

import org.junit.Assert;
import org.junit.Test;

public class ArrayUtilsTest {

    @Test
    public void empty() {
        String str = null;
        Assert.assertTrue(ArrayUtils.empty(""));
        Assert.assertTrue(ArrayUtils.empty(str));

        Assert.assertFalse(ArrayUtils.empty(" "));
        Assert.assertFalse(ArrayUtils.empty("   "));
    }

    @Test
    public void length() {
        String[] strs1 = {"a", "b"};
        String[] strs2 = null;
        Assert.assertEquals(2, ArrayUtils.length(strs1));
        Assert.assertEquals(0, ArrayUtils.length(strs2));
    }

    @Test
    public void contains() {
        String[] strs1 = {"a", "b"};
        String[] strs2 = null;
        int[] ints = {2, 3, 5};
        Assert.assertTrue(ArrayUtils.contains(strs1, "a"));
        Assert.assertTrue(ArrayUtils.contains(strs1, null));
        Assert.assertFalse(ArrayUtils.contains(strs2, "a"));
        Assert.assertTrue(ArrayUtils.contains(ints, 3));
    }
}
