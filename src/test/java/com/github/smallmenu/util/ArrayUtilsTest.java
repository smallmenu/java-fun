package com.github.smallmenu.util;

import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Array;

public class ArrayUtilsTest {
    @Test
    public void testLength() {
        String[] strs1 = {"a", "b"};
        String[] strs2 = null;
        Assert.assertEquals(2, ArrayUtils.length(strs1));
        Assert.assertEquals(0, ArrayUtils.length(strs2));
    }

    @Test
    public void testIsArray() {
        int[] ints = {};
        Integer[] integers = new Integer[0];
        String[] strs1 = {""};
        String[] strs2 = null;

        Assert.assertTrue(ArrayUtils.isArray(ints));
        Assert.assertTrue(ArrayUtils.isArray(integers));
        Assert.assertTrue(ArrayUtils.isArray(strs1));
        Assert.assertFalse(ArrayUtils.isArray(strs2));
    }

    @Test
    public void testContains() {
        String[] strs1 = {"c", "a", "b"};
        String[] strs2 = null;
        int[] ints = {2, 3, 5};
        Assert.assertTrue(ArrayUtils.contains(strs1, "a"));
        Assert.assertFalse(ArrayUtils.contains(strs1, null));
        Assert.assertFalse(ArrayUtils.contains(strs2, "a"));
        Assert.assertTrue(ArrayUtils.contains(ints, 3));
    }

    @Test
    public void testIndexOf() {
        String[] str1 = null;
        String[] str2 = {"bb", "aa", "cc"};
        int[] ints = {5, 3, 7, 9};

        Assert.assertEquals(-1, ArrayUtils.indexOf(str1, "aa"));
        Assert.assertEquals(1, ArrayUtils.indexOf(str2, "aa"));
        Assert.assertEquals(2, ArrayUtils.indexOf(ints, 7));
    }
}
