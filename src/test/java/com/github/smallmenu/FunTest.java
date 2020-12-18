package com.github.smallmenu;

import com.github.smallmenu.date.DatePattern;
import com.github.smallmenu.util.SizeUtils;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

import static com.github.smallmenu.Fun.*;


public class FunTest {
    public static class TestClass {
    }

    @Test
    public void testTime() {
        Assert.assertEquals(time(), strtotime());
        Assert.assertEquals(microtime() / 1000, strtotime());
    }

    @Test
    public void testMemory() {
        System.out.println(maxMemory());
        System.out.println(totalMemory());
        System.out.println(freeMemory());
        System.out.println(usedMemory());
        System.out.println(maxMemory(SizeUtils.MB) + "MB");
        System.out.println(totalMemory(SizeUtils.MB) + "MB");
        System.out.println(freeMemory(SizeUtils.MB) + "MB");
        System.out.println(usedMemory(SizeUtils.MB) + "MB");
    }

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
        Object obj = null;
        String str1 = null;
        String str2 = "";
        String str3 = "a";
        String[] strs1 = {};
        String[] strs2 = {"a", "b"};
        long[] long1 = {};
        long[] long2 = {1, 2};
        Integer[] ints1 = {};
        Integer[] ints2 = {1, 2};
        TestClass testObj1 = null;
        TestClass testObj2 = new TestClass();
        int inta = 0;
        int intb = 1;
        Integer integera = 0;
        Integer integerb = 1;

        Assert.assertTrue(empty(obj));
        Assert.assertTrue(empty(str1));
        Assert.assertTrue(empty(str2));
        Assert.assertFalse(empty(str3));
        Assert.assertTrue(empty(strs1));
        Assert.assertFalse(empty(strs2));
        Assert.assertTrue(empty(long1));
        Assert.assertFalse(empty(long2));
        Assert.assertTrue(empty(ints1));
        Assert.assertFalse(empty(ints2));
        Assert.assertTrue(empty(testObj1));
        Assert.assertFalse(empty(testObj2));
        Assert.assertTrue(empty(inta));
        Assert.assertFalse(empty(intb));
        Assert.assertTrue(empty(integera));
        Assert.assertTrue(empty(integerb));

        Assert.assertTrue(empty(""));
        Assert.assertFalse(empty(" "));
        Assert.assertFalse(empty("   "));
    }

    @Test
    public void testTrim() {
        String str1 = "  HelloWorld  ";
        Assert.assertEquals("HelloWorld", trim(str1));
        Assert.assertEquals("HelloWorld  ", ltrim(str1));
        Assert.assertEquals("  HelloWorld", rtrim(str1));
    }

    @Test
    public void testTrimToEmpty() {
        String str1 = " HelloWorld ";
        String str2 = null;
        Assert.assertEquals("HelloWorld", trimToEmpty(str1));
        Assert.assertEquals("", trimToEmpty(str2));
    }

    @Test
    public void testTrimToNull() {
        String str1 = " HelloWorld ";
        String str2 = "";
        String str3 = null;
        Assert.assertEquals("HelloWorld", trimToNull(str1));
        Assert.assertNull(trimToNull(str2));
        Assert.assertNull(trimToNull(str3));
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
    public void testToInt() {
        Assert.assertEquals(0, toInt(""));
        Assert.assertEquals(0, intVal(""));
        Assert.assertEquals(0, intVal(" "));
        Assert.assertEquals(0, intVal(null));
        Assert.assertEquals(1, intVal("1"));
        Assert.assertEquals(1, intVal(trim(" 1 ")));

        Assert.assertEquals(0, intVal("1.1"));
        Assert.assertEquals(42, intVal("42"));
        Assert.assertEquals(4, intVal("4.2"));
    }

    @Test
    public void testToLong() {
        Assert.assertEquals(0, toLong(""));
        Assert.assertEquals(0, toLong(""));
        Assert.assertEquals(0, toLong(" "));
        Assert.assertEquals(0, toLong(null));
        Assert.assertEquals(1, toLong("1"));
        Assert.assertEquals(1, toLong(trim(" 1 ")));

        Assert.assertEquals(0, toLong("1.1"));
    }

    @Test
    public void testEquals() {
        String str1 = "HelloWorld";
        String str2 = null;
        Assert.assertTrue(Fun.equals(str1, "HelloWorld"));
        Assert.assertTrue(Fun.equalsIgnoreCase(str1, "helloworld"));
        Assert.assertTrue(Fun.equals(str2, null));
    }

    @Test
    public void testStartEndWith() {
        Assert.assertFalse(startWith("Hello", null));
        Assert.assertFalse(startWith(null, ""));
        Assert.assertTrue(startWith(null, null));
        Assert.assertTrue(startWith("Hello", ""));
        Assert.assertTrue(startWith("Hello", "H"));

        Assert.assertTrue(startWithIgnoreCase("Hello", "h"));

        Assert.assertFalse(endWith("Hello", null));
        Assert.assertFalse(endWith(null, ""));
        Assert.assertTrue(endWith(null, null));
        Assert.assertTrue(endWith("Hello", ""));
        Assert.assertTrue(endWith("Hello", "o"));

        Assert.assertTrue(endWithIgnoreCase("Hello", "O"));
    }

    @Test
    public void testDate() {
        System.out.println(date(DatePattern.SLASH_DATETIME_PATTERN));
        System.out.println(date(DatePattern.CN_DATETIME_PATTERN));
        System.out.println(date(DatePattern.UTC_SIMPLE_DATETIME_PATTERN));

        Assert.assertEquals("2015-04-06 16:03:03", date(1428307383));
        Assert.assertEquals("2015/04/06 16:03:03", date(DatePattern.SLASH_DATETIME_PATTERN, 1428307383));
    }

    @Test
    public void testStrtotime() {
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

        Assert.assertEquals("2014-04-06 16:03:03", date(strtotime("-1 year", timeStamp)));
        Assert.assertEquals("2015-04-04 16:03:03", date(strtotime("-2 days", timeStamp)));
        Assert.assertEquals("2015-04-06 18:03:03", date(strtotime("+2 hours", timeStamp)));
    }

    @Test
    public void testStrlen() {
        Assert.assertEquals(strlen("Hello, 中国！"), 10);
    }

    @Test
    public void testRemove() {
        Assert.assertEquals("HellWrld0", remove("HelloWorld0", "o"));
        Assert.assertEquals("HellWrld", removeAny("HelloWorld0", "o", "0"));
    }
}
