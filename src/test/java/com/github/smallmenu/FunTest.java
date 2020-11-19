package com.github.smallmenu;

import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

import static com.github.smallmenu.Fun.*;


public class FunTest {
    @Test
    public void testBlank() {
        Assert.assertTrue(blank(""));
        Assert.assertTrue(blank(null));
        Assert.assertTrue(blank(" "));
        Assert.assertTrue(blank("   "));

        Assert.assertTrue(blankAll("", " ", "   ", null));
    }

    @Test
    public void testEmpty() {
        Assert.assertTrue(empty(""));
        Assert.assertTrue(empty(null));

        Assert.assertFalse(empty(" "));
        Assert.assertFalse(empty("   "));

        Assert.assertTrue(emptyAll("", null));

        String[] strs = {};
        Assert.assertTrue(emptyArray(strs));
    }

    @Test
    public void testTime() {
        Assert.assertEquals(time(), strtotime());
    }

    @Test
    public void testContains() {
        String s = "Hello";
        Assert.assertTrue(contains(s, "o"));
        Assert.assertFalse(contains(null, "o"));
        Assert.assertFalse(contains(null, ""));
        Assert.assertFalse(contains(null, null));
        Assert.assertTrue(containsAny(s, "", "o"));
    }

    @Test
    public void testIntval() {
        Assert.assertEquals(0, toInt(""));
        Assert.assertEquals(0, intval(""));
        Assert.assertEquals(0, intval(" "));
        Assert.assertEquals(0, intval(null));
        Assert.assertEquals(1, intval("1"));
        Assert.assertEquals(1, intval(trim(" 1 ")));

        Assert.assertEquals(0, intval("1.1"));
    }

    @Test
    public void testLongval() {
        Assert.assertEquals(0, toLong(""));
        Assert.assertEquals(0, longval(""));
        Assert.assertEquals(0, longval(" "));
        Assert.assertEquals(0, longval(null));
        Assert.assertEquals(1, longval("1"));
        Assert.assertEquals(1, longval(trim(" 1 ")));

        Assert.assertEquals(0, longval("1.1"));
    }

    @Test
    public void testStrtotime1() {

        String dtString = "2015-04-06T16:03:03+02:00";

        LocalDateTime ldt = LocalDateTime.parse(dtString, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssz"));
        long timeStamp = ldt.toEpochSecond(ZoneOffset.ofHours(8));

        Assert.assertEquals(strtotime("2015-04-06T16:03:03+08:00"), timeStamp);
        Assert.assertEquals(strtotime("2015-04-06T16:03:03Z"), timeStamp);
        Assert.assertEquals(strtotime("2015-04-06T16:03:03.123"), timeStamp);
        Assert.assertEquals(strtotime("2015-04-06T16:03:03"), timeStamp);
        Assert.assertEquals(strtotime("2015-04-06 16:03:03"), timeStamp);
        Assert.assertEquals(strtotime("2015-04-06 16:03"), timeStamp - 3);
        Assert.assertEquals(strtotime("2015-4-6 16:3:3"), timeStamp);
        Assert.assertEquals(strtotime("2015-4-06 16:03:3"), timeStamp);
        Assert.assertEquals(strtotime("2015-4-6 16:03:03"), timeStamp);
        Assert.assertEquals(strtotime("2015/04/06 16:03:03"), timeStamp);
        Assert.assertEquals(strtotime("2015年04月06日 16时03分03秒"), timeStamp);
        Assert.assertEquals(strtotime("2015年4月6日 16时03分3秒"), timeStamp);
        Assert.assertEquals(strtotime("2015年04月6日 16时03分3秒"), timeStamp);
        Assert.assertEquals(strtotime("2015年4月6日 16时03分"), timeStamp - 3);
    }

    @Test
    public void testStrtotime2() {
        String dString = "2019-07-31";
    }

    @Test
    public void testStrlen() {
        Assert.assertEquals(strlen("Hello, 中国！"), 10);
    }

    @Test
    public void testStartEndWith() {
        Assert.assertFalse(startWith("Hello", null));
        Assert.assertFalse(startWith(null, ""));
        Assert.assertTrue(startWith(null, null));
        Assert.assertTrue(startWith("Hello", ""));
        Assert.assertTrue(startWith("Hello", "H"));

        Assert.assertFalse(endWith("Hello", null));
        Assert.assertFalse(endWith(null, ""));
        Assert.assertTrue(endWith(null, null));
        Assert.assertTrue(endWith("Hello", ""));
        Assert.assertTrue(endWith("Hello", "o"));
    }
}
