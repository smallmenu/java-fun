package com.github.smallmenu;

import com.github.smallmenu.constant.DatePattern;
import com.github.smallmenu.util.SizeUtils;
import com.github.smallmenu.util.StringUtils;
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
        System.out.println(maxMemoryByte());
        System.out.println(totalMemoryByte());
        System.out.println(freeMemoryByte());
        System.out.println(usedMemoryByte());
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
        String[] strs1 = {};
        long[] long1 = {};
        Integer[] ints1 = {};
        TestClass testObj1 = null;
        int inta = 0;
        Integer integera = 0;
        Integer integerb = 1;

        Assert.assertTrue(empty(obj));
        Assert.assertTrue(empty(str1));
        Assert.assertTrue(empty(str2));
        Assert.assertTrue(empty(strs1));
        Assert.assertTrue(empty(long1));
        Assert.assertTrue(empty(ints1));
        Assert.assertTrue(empty(testObj1));
        Assert.assertTrue(empty(inta));
        Assert.assertTrue(empty(integera));
        Assert.assertTrue(empty(integerb));
        Assert.assertTrue(empty(""));

        String str3 = "a";
        String[] strs2 = {"a", "b"};
        TestClass testObj2 = new TestClass();
        long[] long2 = {1, 2};
        int intb = 1;
        Integer[] ints2 = {1, 2};
        Assert.assertFalse(empty(str3));
        Assert.assertFalse(empty(strs2));
        Assert.assertFalse(empty(long2));
        Assert.assertFalse(empty(ints2));
        Assert.assertFalse(empty(testObj2));
        Assert.assertFalse(empty(intb));
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
        Assert.assertTrue(containsIgnoreCase(s, "h"));
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

    @Test
    public void testRandom() {
        System.out.println(randomInt());
        System.out.println(randomInt(100));
        System.out.println(randomInt(100, 10000));
        System.out.println(randomLong());
        System.out.println(randomLong(100));
        System.out.println(randomLong(100, 10000));
        System.out.println(randomString(16));
        System.out.println(randomStringChar(16));
        System.out.println(randomStringNumber(16));
        System.out.println(randomStringExcepts(16, "0"));
    }

    @Test
    public void testMd5() {
        Assert.assertEquals("d41d8cd98f00b204e9800998ecf8427e", md5(StringUtils.EMPTY));

        StringBuffer sb = new StringBuffer("123456abc");
        Assert.assertEquals("df10ef8509dc176d733d59549e7dbfaf", md5(sb));
        Assert.assertEquals("df10ef8509dc176d733d59549e7dbfaf", md5("123456abc"));
        Assert.assertEquals("c3fcd3d76192e4007dfb496cca67e13b", md5("abcdefghijklmnopqrstuvwxyz"));
    }

    @Test
    public void testSha() {
        StringBuffer sb = new StringBuffer("123456abc");
        Assert.assertEquals("a172ffc990129fe6f68b50f6037c54a1894ee3fd", sha1(sb));
        Assert.assertEquals("a172ffc990129fe6f68b50f6037c54a1894ee3fd", sha1("123456abc"));
        Assert.assertEquals("32d10c7b8cf96570ca04ce37f2a19d84240d3a89", sha1("abcdefghijklmnopqrstuvwxyz"));
        Assert.assertEquals("931145d4ddd1811be545e4ac88a81f1fdbfaf0779c437efba16b884595274d11", sha256(sb));
        Assert.assertEquals("931145d4ddd1811be545e4ac88a81f1fdbfaf0779c437efba16b884595274d11", sha256("123456abc"));
        Assert.assertEquals("71c480df93d6ae2f1efad1447c66c9525e316218cf51fc8d9ed832f2daf18b73", sha256("abcdefghijklmnopqrstuvwxyz"));
        Assert.assertEquals("2545507ada3a26999b11ec0324ae76e0cdef629c4a28b24be3965d24e1c406180a8ef79626c77fb3f556bfd59ab54920", sha384(sb));
        Assert.assertEquals("2545507ada3a26999b11ec0324ae76e0cdef629c4a28b24be3965d24e1c406180a8ef79626c77fb3f556bfd59ab54920", sha384("123456abc"));
        Assert.assertEquals("feb67349df3db6f5924815d6c3dc133f091809213731fe5c7b5f4999e463479ff2877f5f2936fa63bb43784b12f3ebb4", sha384("abcdefghijklmnopqrstuvwxyz"));
        Assert.assertEquals("8756869d440a13e93979197b5d7839c823de87c2b115bce0dee62030af3b5b63114a517f1ab02509bfefa88527369ae0ad7946990f27dcb37711a7d6fb9bc893", sha512(sb));
        Assert.assertEquals("8756869d440a13e93979197b5d7839c823de87c2b115bce0dee62030af3b5b63114a517f1ab02509bfefa88527369ae0ad7946990f27dcb37711a7d6fb9bc893", sha512("123456abc"));
        Assert.assertEquals("4dbff86cc2ca1bae1e16468a05cb9881c97f1753bce3619034898faa1aabe429955a1bf8ec483d7421fe3c1646613a59ed5441fb0f321389f77f48a879c7b1f1", sha512("abcdefghijklmnopqrstuvwxyz"));
    }

    @Test
    public void testBase64() {
        StringBuffer sb = new StringBuffer("123456abc");
        Assert.assertEquals("MTIzNDU2YWJj", base64Encode(sb));
        Assert.assertEquals("MTIzNDU2YWJj", base64Encode("123456abc"));
        Assert.assertEquals("YWJjZGVmZ2hpamtsbW5vcHFyc3R1dnd4eXo=", base64Encode("abcdefghijklmnopqrstuvwxyz"));
    }
}
