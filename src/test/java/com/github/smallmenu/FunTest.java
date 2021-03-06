package com.github.smallmenu;

import com.github.smallmenu.constant.DatePattern;
import com.github.smallmenu.fun.DataSizeFun;
import com.github.smallmenu.fun.StringFun;
import org.junit.Assert;
import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Pattern;

import static com.github.smallmenu.Fun.*;


public class FunTest {
    public static class ObjectClass {
        private Integer id;
        private String name;

        public ObjectClass() {
        }

        public ObjectClass(Integer id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public String toString() {
            return "ObjectClass{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }
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
        System.out.println(maxMemoryMb());
        System.out.println(totalMemoryMb());
        System.out.println(freeMemoryMb());
        System.out.println(usedMemoryMb());
        System.out.println(maxMemory(DataSizeFun.MB) + "MB");
        System.out.println(totalMemory(DataSizeFun.MB) + "MB");
        System.out.println(freeMemory(DataSizeFun.MB) + "MB");
        System.out.println(usedMemory(DataSizeFun.MB) + "MB");
    }

    @Test
    public void testBlank() {
        Assert.assertTrue(blank(""));
        Assert.assertTrue(blank(null));
        Assert.assertTrue(blank(" "));
        Assert.assertTrue(blank("   "));

        Assert.assertTrue(blankAny("a", "b", " "));
        Assert.assertTrue(blankAny("a", "b", ""));
        Assert.assertTrue(blankAny("a", "b", null));
        Assert.assertFalse(blankAny("a", "b", "c"));

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
        ObjectClass testObj1 = null;
        int inta = 0;
        boolean boola = false;
        Integer integera = 0;
        Boolean booleanA = false;
        List<String> strList1 = new ArrayList<>();
        Set<String> strSet1 = new HashSet<>();
        HashMap<String, String> strMap1 = new HashMap<>();

        Assert.assertTrue(empty(obj));
        Assert.assertTrue(empty(str1));
        Assert.assertTrue(empty(str2));
        Assert.assertTrue(empty(strs1));
        Assert.assertTrue(empty(long1));
        Assert.assertTrue(empty(ints1));
        Assert.assertTrue(empty(testObj1));
        Assert.assertTrue(empty(inta));
        Assert.assertTrue(empty(integera));
        Assert.assertTrue(empty(boola));
        Assert.assertTrue(empty(booleanA));
        Assert.assertTrue(empty(""));
        Assert.assertTrue(empty(strList1));
        Assert.assertTrue(empty(strSet1));
        Assert.assertTrue(empty(strMap1));

        String str3 = "a";
        String[] strs2 = {"a", "b"};
        ObjectClass testObj2 = new ObjectClass();
        long[] long2 = {1, 2};
        int intb = 1;
        boolean boolb = true;
        Integer integerb = 1;
        Integer[] ints2 = {1, 2};
        List<String> strList2 = new ArrayList<>();
        Set<String> strSet2 = new HashSet<>();
        HashMap<String, String> strMap2 = new HashMap<>();
        strList2.add("a");
        strSet2.add("a");
        strMap2.put("a", "b");

        Assert.assertFalse(empty(str3));
        Assert.assertFalse(empty(strs2));
        Assert.assertFalse(empty(long2));
        Assert.assertFalse(empty(ints2));
        Assert.assertFalse(empty(testObj2));
        Assert.assertFalse(empty(intb));
        Assert.assertFalse(empty(boolb));
        Assert.assertFalse(empty(integerb));
        Assert.assertFalse(empty(strList2));
        Assert.assertFalse(empty(strSet2));
        Assert.assertFalse(empty(strMap2));
        Assert.assertFalse(empty(" "));
        Assert.assertFalse(empty("   "));

        Assert.assertTrue(emptyAny("a", "b", ""));
        Assert.assertFalse(emptyAny("a", "b", "c"));

        Assert.assertFalse(emptyAll("a", ""));
        Assert.assertTrue(emptyAll("", "", ""));
    }

    @Test
    public void testTrim() {
        String str1 = "  HelloWorld  ";
        String str2 = "HelloWorld";
        String str3 = "\t\tHelloWorld\t\t";
        Assert.assertEquals("HelloWorld", trim(str1));
        Assert.assertEquals("HelloWorld  ", ltrim(str1));
        Assert.assertEquals("  HelloWorld", rtrim(str1));
        Assert.assertEquals("HelloWorld", trim(str3));

        Assert.assertEquals("  HelloWorld  ", trim(str1, ""));
        Assert.assertEquals("HelloWorld", trim(str1, " "));
        Assert.assertEquals("HelloWorld", trim(str1, null));
        Assert.assertEquals("elloWorl", trim(str1, " Hd"));
        Assert.assertEquals("oWor", trim(str2, "Hdle"));
    }

    @Test
    public void testTrimToEmpty() {
        String str1 = " HelloWorld ";
        String str2 = null;
        Assert.assertEquals("HelloWorld", trimToEmpty(str1));
        Assert.assertEquals("", trimToEmpty(str2));

        Assert.assertEquals("abc", trimToDefault("\n", "abc"));
        Assert.assertEquals("abc", trimToDefault("      ", "abc"));
        Assert.assertEquals("abc", trimToDefault(null, "abc"));
        Assert.assertEquals("abc", trimToDefault(str2, "abc"));
        Assert.assertEquals("HelloWorld", trimToDefault(str1, "abc"));
    }

    @Test
    public void testTrimToDefault() {
        String str1 = " HelloWorld ";
        String str2 = null;

        Assert.assertEquals("abc", trimToDefault("\n", "abc"));
        Assert.assertEquals("abc", trimToDefault("      ", "abc"));
        Assert.assertEquals("abc", trimToDefault(null, "abc"));
        Assert.assertEquals("abc", trimToDefault(str2, "abc"));
        Assert.assertEquals("HelloWorld", trimToDefault(str1, "abc"));
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
        Assert.assertEquals(0, toInt(""));
        Assert.assertEquals(0, toInt(" "));
        Assert.assertEquals(0, toInt(null));
        Assert.assertEquals(1, toInt("1"));
        Assert.assertEquals(1, toInt(trim(" 1 ")));

        Assert.assertEquals(0, toInt("1.1"));
        Assert.assertEquals(42, toInt("42"));
        Assert.assertEquals(0, toInt("4.2"));
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
    public void testToFloatDouble() {
        Assert.assertEquals(3.1415926F, toFloat("3.14159265"), 0.0001);
        Assert.assertEquals(3.14159265D, toDouble("3.14159265"), 0);
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
        Assert.assertFalse(startsWith("Hello", null));
        Assert.assertFalse(startsWith(null, ""));
        Assert.assertTrue(startsWith(null, null));
        Assert.assertTrue(startsWith("Hello", ""));
        Assert.assertTrue(startsWith("Hello", "H"));

        Assert.assertTrue(startsWithIgnoreCase("Hello", "h"));

        Assert.assertFalse(endsWith("Hello", null));
        Assert.assertFalse(endsWith(null, ""));
        Assert.assertTrue(endsWith(null, null));
        Assert.assertTrue(endsWith("Hello", ""));
        Assert.assertTrue(endsWith("Hello", "o"));

        Assert.assertTrue(endsWithIgnoreCase("Hello", "O"));


        Assert.assertFalse(startsWithAny(null, null));
        Assert.assertTrue(startsWithAny("a.zip", "b.", "a.", "c."));

        Assert.assertFalse(endWithAny(null, null));
        Assert.assertTrue(endWithAny("a.zip", ".doc", ".zip", ".xls"));
        Assert.assertFalse(endWithAny("a.png", ".doc", ".zip", ".xls"));
        Assert.assertTrue(endWithAny("a.png", new String[]{""}));
    }

    @Test
    public void testDate() {
        System.out.println(date());
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
        Assert.assertEquals(10, length("Hello, 中国！"));
    }

    @Test
    public void testStrLowerAndUpper() {
        String str = null;
        Assert.assertEquals("helloworld", toLowerCase("HelloWorld"));
        Assert.assertEquals("HELLOWORLD", toUpperCase("helloworld"));
    }

    @Test
    public void testRemove() {

        Assert.assertNull(remove(null, "o"));

        Assert.assertEquals("HelloWorld", remove("HelloWorld", null));
        Assert.assertEquals("HelloWorld", remove("HelloWorld", ""));
        Assert.assertEquals("HellWrld0", remove("HelloWorld0", "o"));
        Assert.assertEquals("HellWrld", removeAny("HelloWorld0", "o", "0"));

        Assert.assertEquals("com.abc.com", removePrefix("com.abc.com", ""));
        Assert.assertEquals(".abc.com", removePrefix("com.abc.com", "com"));
        Assert.assertEquals("com.abc.", removeSuffix("com.abc.com", "com"));

        Assert.assertEquals("//abc", removePrefix("////abc", "//"));
        Assert.assertEquals("abc", removePrefixComplete("////abc", "//"));

        Assert.assertEquals("abc##", removeSuffix("abc####", "##"));
        Assert.assertEquals("abc", removeSuffixComplete("abc####", "##"));
    }

    @Test
    public void testRemoveLines() {
        String str = "<html>\n" +
                "<head>\n" +
                "<meta charset=\"utf-8\">\n";

        Assert.assertEquals("<html><head><meta charset=\"utf-8\">", removeLines(str));
    }

    @Test
    public void testRepeat() {
        Assert.assertEquals("", repeat('a', 0));
        Assert.assertEquals("", repeat('a', -2));
        Assert.assertEquals("aaa", repeat('a', 3));

        Assert.assertEquals("", repeat("ab", 0));
        Assert.assertEquals("ababab", repeat("ab", 3));
    }

    @Test
    public void testPad() {
        Assert.assertEquals("abc", padLeft("abc", 1));
        Assert.assertEquals("abc", padLeft("abc", -1));
        Assert.assertEquals("       abc", padLeft("abc", 10));
        Assert.assertEquals("*******abc", padLeft("abc", 10, '*'));
        Assert.assertEquals("0000000abc", padLeft("abc", 10, "0"));
        Assert.assertEquals("0101010abc", padLeft("abc", 10, "01"));
        Assert.assertEquals("0120120abc", padLeft("abc", 10, "012"));

        Assert.assertEquals("abc", padRight("abc", 1));
        Assert.assertEquals("abc", padRight("abc", -1));
        Assert.assertEquals("abc       ", padRight("abc", 10));
        Assert.assertEquals("abc*******", padRight("abc", 10, '*'));
        Assert.assertEquals("abc0000000", padRight("abc", 10, "0"));
        Assert.assertEquals("abc0101010", padRight("abc", 10, "01"));
        Assert.assertEquals("abc0120120", padRight("abc", 10, "012"));
    }

    @Test
    public void testSubstring() {
        Assert.assertEquals("f", substring("abcdef", -1));
        Assert.assertEquals("ef", substring("abcdef", -2));
        Assert.assertEquals("f", substring("abcdef", 5));
        Assert.assertEquals("", substring("abcdef", 6));

        Assert.assertEquals("bc", substring("abcdef", 1, 3));
        Assert.assertEquals("bcdef", substring("abcdef", 1, 6));
        Assert.assertEquals("bcde", substring("abcdef", 1, -1));
        Assert.assertEquals("", substring("abcdef", 4, -4));
    }

    @Test
    public void testSubstr() {
        Assert.assertEquals("f", substr("abcdef", -1));
        Assert.assertEquals("ef", substr("abcdef", -2));
        Assert.assertEquals("f", substr("abcdef", 5));
        Assert.assertEquals("", substr("abcdef", 6));

        Assert.assertEquals("abc", substr("abcdef", 0, 3));
        Assert.assertEquals("abcdef", substr("abcdef", 0, 6));
        Assert.assertEquals("abcdef", substr("abcdef", 0, 7));
        Assert.assertEquals("bcde", substr("abcdef", 1, -1));
        Assert.assertEquals("cde", substr("abcdef", 2, -1));
        Assert.assertEquals("", substr("abcdef", 4, -4));
    }

    @Test
    public void testSplit() {
        Assert.assertArrayEquals(new String[]{"a", "b", "c"}, "a b c".split(" "));
        Assert.assertArrayEquals(new String[]{"a", "", "b", "", "c"}, "a  b  c".split(" "));
        Assert.assertArrayEquals(new String[]{"a", "b", "c"}, "a#b#c".split("#"));
        Assert.assertArrayEquals(new String[]{"a", "b", "c"}, "a#b#c#".split("#"));
        Assert.assertArrayEquals(new String[]{"", "a", "b", "c"}, "#a#b#c#".split("#"));
        Assert.assertArrayEquals(new String[]{"", "a", "", "b", "", "c"}, "#a##b##c#".split("#"));
        Assert.assertArrayEquals(new String[]{"", "a", "", "b", "", "c"}, "#a##b##c#".split("#"));

        Assert.assertArrayEquals(new String[]{}, split(null));
        Assert.assertArrayEquals(new String[]{"a", "b", "c"}, split("a b c"));
        Assert.assertArrayEquals(new String[]{"a", "", "b", "", "c"}, split("a  b  c"));
        Assert.assertArrayEquals(new String[]{"a#b#c"}, split("a#b#c", null));
        Assert.assertArrayEquals(new String[]{"a", "b", "c"}, split("a#b#c", '#'));
        Assert.assertArrayEquals(new String[]{"a", "b", "c"}, split("a#b#c", "#"));
        Assert.assertArrayEquals(new String[]{"", "a", "b", "c", ""}, split("#a#b#c#", "#"));
        Assert.assertArrayEquals(new String[]{"", "a", "", "b", "", "c", ""}, split("#a##b##c#", "#"));

        Assert.assertArrayEquals(new String[]{}, splitTrim(null));
        Assert.assertArrayEquals(new String[]{"a", "b", "c"}, splitTrim("a b c"));
        Assert.assertArrayEquals(new String[]{"a", "b", "c"}, splitTrim("a  b  c"));
        Assert.assertArrayEquals(new String[]{"a", "b", "c"}, splitTrim("a  b  c"));
        Assert.assertArrayEquals(new String[]{"a#b#c"}, splitTrim("a#b#c", null));
        Assert.assertArrayEquals(new String[]{"a", "b", "c"}, splitTrim("a#b#c", '#'));
        Assert.assertArrayEquals(new String[]{"a", "b", "c"}, splitTrim("a#b#c", "#"));
        Assert.assertArrayEquals(new String[]{"a", "b", "c"}, splitTrim("#a#b#c#", "#"));
        Assert.assertArrayEquals(new String[]{"a", "b", "c"}, splitTrim("#a##b##c#", "#"));
        Assert.assertArrayEquals(new String[]{"#a", "b", "c#"}, splitTrim("#a##b##c#", "##"));

        ArrayList<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");

        Assert.assertEquals(list, splitToList("a b c"));
        Assert.assertEquals(list, splitToList("a#b#c", '#'));
        Assert.assertEquals(list, splitToList("a#b#c", "#"));

        Assert.assertEquals(list, splitTrimToList("a b c"));
        Assert.assertEquals(list, splitTrimToList("a#b#c", '#'));
        Assert.assertEquals(list, splitTrimToList("a#b#c", "#"));
        Assert.assertEquals(list, splitTrimToList("#a#b#c#", "#"));
        Assert.assertEquals(list, splitTrimToList("#a##b##c#", "#"));

        Assert.assertArrayEquals(new int[]{3, 2, 0, 5}, splitTrimToInt("3 2 0 5"));
        Assert.assertArrayEquals(new long[]{3, 2, 0, 5}, splitTrimToLong("3 2 0 5"));
        Assert.assertArrayEquals(new int[]{3, 2, 0, 5}, splitTrimToInt("#3##2#0##5#", "#"));
        Assert.assertArrayEquals(new int[]{3, 1, 0, 5}, splitTrimToInt("#3##1#0#a#5#", "#"));
        Assert.assertArrayEquals(new long[]{3, 1, 0, 5}, splitTrimToLong("#3##1#0#a#5#", "#"));
    }

    @Test
    public void testJoin() {
        String a = null;
        short[] shorts = {};
        int[] ints = {2, 3, 4};
        long[] longs = {2, 3, 4};
        char[] chars = {'A', 'B', 'C'};
        float[] floats = {1.3F, 2.4F, 3.5F};
        double[] doubles = {1.3D, 2.4D, 3.5D};
        boolean[] booleans = {true, false};
        String[] strs = null;
        String[] strs1 = {"aa", "bb", "cc"};
        String[] strs2 = {"aa", null, "cc"};
        String[] strs3 = {null, "bb", "cc"};
        String[] strs4 = {};
        String[] strs5 = {null};
        String[] strs6 = {null, null, "cc", "dd"};
        String[] strs7 = {null, null, "", "dd"};

        List<Integer> strList1 = new ArrayList<>();
        strList1.add(2);
        strList1.add(3);
        strList1.add(4);

        List<String> strList2 = new ArrayList<>();

        List<String> strList3 = new ArrayList<>();
        strList3.add("aa");
        strList3.add("bb");
        strList3.add("cc");

        List<String> strList4 = new LinkedList<>();
        strList4.add("aa");
        strList4.add(null);
        strList4.add("cc");

        Set<String> strSets1 = new LinkedHashSet<>();
        strSets1.add(null);
        strSets1.add("bb");
        strSets1.add("cc");

        Set<String> strSets2 = new LinkedHashSet<>();
        strSets2.add(null);
        strSets2.add(null);
        strSets2.add("bb");
        strSets2.add("cc");

        Set<String> strSets3 = new LinkedHashSet<>();
        strSets3.add(null);
        strSets3.add(null);
        strSets3.add("bb");
        strSets3.add("cc");
        strSets3.add(null);
        strSets3.add("dd");

        Set<String> strSets4 = new LinkedHashSet<>();
        strSets4.add(null);
        strSets4.add(null);
        strSets4.add("bb");
        strSets4.add("");
        strSets4.add(null);
        strSets4.add("dd");

        Set<ObjectClass> objSets = new LinkedHashSet<>();
        objSets.add(new ObjectClass(1, "aa"));
        objSets.add(new ObjectClass(2, "bb"));

        Assert.assertNull(join(strs, ","));
        Assert.assertEquals("234", join(ints, a));
        Assert.assertEquals("234", join(ints, null));
        Assert.assertEquals("", join(shorts, ","));
        Assert.assertEquals("2,3,4", join(ints, ","));
        Assert.assertEquals("2,3,4", join(longs, ","));
        Assert.assertEquals("A,B,C", join(chars, ","));
        Assert.assertEquals("1.3,2.4,3.5", join(floats, ","));
        Assert.assertEquals("1.3,2.4,3.5", join(doubles, ","));
        Assert.assertEquals("true,false", join(booleans, ","));
        Assert.assertEquals("aa,bb,cc", join(strs1, ","));
        Assert.assertEquals("aa,cc", join(strs2, ","));
        Assert.assertEquals("bb,cc", join(strs3, ","));
        Assert.assertEquals("", join(strs4, ","));
        Assert.assertEquals("", join(strs5, ","));
        Assert.assertEquals("cc,dd", join(strs6, ","));
        Assert.assertEquals(",dd", join(strs7, ","));

        Assert.assertEquals("2,3,4", join(strList1, ","));
        Assert.assertEquals("", join(strList2, ","));
        Assert.assertEquals("aa,bb,cc", join(strList3, ","));
        Assert.assertEquals("aa,cc", join(strList4, ","));
        Assert.assertEquals("bb,cc", join(strSets1, ","));
        Assert.assertEquals("bb,cc", join(strSets2, ","));
        Assert.assertEquals("bb,cc,dd", join(strSets3, ","));
        Assert.assertEquals("bb,,dd", join(strSets4, ","));

        Assert.assertEquals("ObjectClass{id=1, name='aa'};ObjectClass{id=2, name='bb'}", join(objSets, ";"));
    }

    @Test
    public void testLeftRight() {
        String str1 = null;
        String str2 = "HelloWorld";
        Assert.assertNull(left(str1, 3));
        Assert.assertEquals("Hel", left(str2, 3));
        Assert.assertEquals("HelloWorld", left(str2, 15));
        Assert.assertEquals("rld", right(str2, 3));
        Assert.assertEquals("HelloWorld", right(str2, 15));
    }

    @Test
    public void testReverse() {
        Assert.assertEquals("dlroWolleH", reverse("HelloWorld"));
    }

    @Test
    public void testMatches() {
        Pattern pattern = Pattern.compile("(foo)(bar).*");
        Assert.assertTrue(matches("(foo)(bar)(baz)", "foobarbaz"));
        Assert.assertFalse(matches("(foo)(bar)(baa)", "foobarbaz"));

        Assert.assertTrue(matches(pattern, "foobarbaz"));
    }

    @Test
    public void testRegexMatch() {
        Pattern pattern = Pattern.compile("(foo)(bar)(baz)");
        List<String> results = new ArrayList<>();
        results.add("foobarbaz");
        results.add("foo");
        results.add("bar");
        results.add("baz");

        Assert.assertEquals("foobarbaz", regexMatch("(foo)(bar)(baz)", "foobarbaz", 0));
        Assert.assertEquals("bar", regexMatch("(foo)(bar)(baz)", "foobarbaz", 2));
        Assert.assertEquals("bar", regexMatch(pattern, "foobarbaz", 2));

        Assert.assertEquals(results, regexMatch("(foo)(bar)(baz)", "foobarbaz"));
        Assert.assertEquals(results, regexMatch(pattern, "foobarbaz"));
    }

    @Test
    public void testIndexOf() {
        String str1 = null;
        String str2 = "HelloWorld";
        String search = null;

        Assert.assertEquals(-1, indexOf(str1, "e"));
        Assert.assertEquals(-1, indexOf(str1, null));
        Assert.assertEquals(1, indexOf(str2, "e"));
        Assert.assertEquals(6, indexOf(str2, "o", 5));
        Assert.assertEquals(5, indexOfIgnoreCase(str2, "w"));
        Assert.assertEquals(6, indexOfIgnoreCase(str2, "O", 5));
    }

    @Test
    public void testReplace() {
        Assert.assertNull(replace(null, null, null));
        Assert.assertEquals("", replace("", null, null));
        Assert.assertEquals("HelloWorld", replace("HelloWorld", null, null));
        Assert.assertEquals("HellWrld", replace("HelloWorld", "o", ""));
        Assert.assertEquals("HellKWKrld", replace("HelloWorld", "o", "K"));
        Assert.assertEquals("Hello-orld", replaceIgnoreCase("HelloWorld", "w", "-"));
    }

    @Test
    public void testRandom() {
        System.out.println(random());
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
        Assert.assertEquals("d41d8cd98f00b204e9800998ecf8427e", md5(StringFun.EMPTY));
        Assert.assertEquals("df10ef8509dc176d733d59549e7dbfaf", md5("123456abc"));
        Assert.assertEquals("c3fcd3d76192e4007dfb496cca67e13b", md5("abcdefghijklmnopqrstuvwxyz"));
    }

    @Test
    public void testSha() {
        Assert.assertEquals("da39a3ee5e6b4b0d3255bfef95601890afd80709", sha1(StringFun.EMPTY));
        Assert.assertEquals("a172ffc990129fe6f68b50f6037c54a1894ee3fd", sha1("123456abc"));
        Assert.assertEquals("32d10c7b8cf96570ca04ce37f2a19d84240d3a89", sha1("abcdefghijklmnopqrstuvwxyz"));
        Assert.assertEquals("931145d4ddd1811be545e4ac88a81f1fdbfaf0779c437efba16b884595274d11", sha256("123456abc"));
        Assert.assertEquals("71c480df93d6ae2f1efad1447c66c9525e316218cf51fc8d9ed832f2daf18b73", sha256("abcdefghijklmnopqrstuvwxyz"));
        Assert.assertEquals("2545507ada3a26999b11ec0324ae76e0cdef629c4a28b24be3965d24e1c406180a8ef79626c77fb3f556bfd59ab54920", sha384("123456abc"));
        Assert.assertEquals("feb67349df3db6f5924815d6c3dc133f091809213731fe5c7b5f4999e463479ff2877f5f2936fa63bb43784b12f3ebb4", sha384("abcdefghijklmnopqrstuvwxyz"));
        Assert.assertEquals("8756869d440a13e93979197b5d7839c823de87c2b115bce0dee62030af3b5b63114a517f1ab02509bfefa88527369ae0ad7946990f27dcb37711a7d6fb9bc893", sha512("123456abc"));
        Assert.assertEquals("4dbff86cc2ca1bae1e16468a05cb9881c97f1753bce3619034898faa1aabe429955a1bf8ec483d7421fe3c1646613a59ed5441fb0f321389f77f48a879c7b1f1", sha512("abcdefghijklmnopqrstuvwxyz"));
    }

    @Test
    public void testBase64() {
        Assert.assertEquals("", base64Encode(StringFun.EMPTY));
        Assert.assertEquals("MTIzNDU2YWJj", base64Encode(bytes("123456abc")));
        Assert.assertEquals("MTIzNDU2YWJj", base64Encode("123456abc"));
        Assert.assertEquals("YWJjZGVmZ2hpamtsbW5vcHFyc3R1dnd4eXo=", base64Encode("abcdefghijklmnopqrstuvwxyz"));
        Assert.assertEquals("aHR0cHM6Ly93d3cuYmFpZHUuY29tL3M/aWU9dXRmLTgmZj04JnJzdl9icD0xJnRuPWJhaWR1", base64Encode("https://www.baidu.com/s?ie=utf-8&f=8&rsv_bp=1&tn=baidu"));
        Assert.assertEquals("aHR0cHM6Ly93d3cuYmFpZHUuY29tL3M_aWU9dXRmLTgmZj04JnJzdl9icD0xJnRuPWJhaWR1", base64UrlSafeEncode("https://www.baidu.com/s?ie=utf-8&f=8&rsv_bp=1&tn=baidu"));

        Assert.assertEquals("123456abc", base64Decode("MTIzNDU2YWJj"));
        Assert.assertEquals("abcdefghijklmnopqrstuvwxyz", base64Decode("YWJjZGVmZ2hpamtsbW5vcHFyc3R1dnd4eXo="));
        Assert.assertEquals("https://www.baidu.com/s?ie=utf-8&f=8&rsv_bp=1&tn=baidu", base64Decode("aHR0cHM6Ly93d3cuYmFpZHUuY29tL3M/aWU9dXRmLTgmZj04JnJzdl9icD0xJnRuPWJhaWR1"));
        Assert.assertEquals("https://www.baidu.com/s?ie=utf-8&f=8&rsv_bp=1&tn=baidu", base64UrlSafeDecode("aHR0cHM6Ly93d3cuYmFpZHUuY29tL3M_aWU9dXRmLTgmZj04JnJzdl9icD0xJnRuPWJhaWR1"));
    }

    @Test
    public void testIdEncodeDecode() {
        Assert.assertEquals("j0gW", idEncode(12345L));
        Assert.assertEquals("NkK9", idEncode(12345L, "this is my salt"));
        Assert.assertEquals("lej0gWbj", idEncode(12345L, 8));
        Assert.assertEquals("EDngB0NkK9A5ev1W", idEncode(12345L, "this is my salt", 16));

        Assert.assertEquals(12345L, idDecode("j0gW"));
        Assert.assertEquals(12345L, idDecode("NkK9", "this is my salt"));
        Assert.assertEquals(12345L, idDecode("lej0gWbj", 8));
        Assert.assertEquals(12345L, idDecode("EDngB0NkK9A5ev1W", "this is my salt", 16));
    }

    @Test
    public void testUrlVerify() {
        Assert.assertFalse(urlVerify("http://www"));
        Assert.assertTrue(urlVerify("http://www.ss"));

        Assert.assertTrue(urlVerify("https://www.baidu.com/s?wd=%E9%A9%AC%E4%BA%91"));
        Assert.assertTrue(urlVerify("//www.zhihu.com/collection/150270125?page=3"));
        Assert.assertTrue(urlVerify("//static.ws.126.net/www/logo/logo-ipad-icon.png"));
        Assert.assertTrue(urlVerify("http://www.siyst.org.cn/portal/article/index/id/12/cid/1.html"));
        Assert.assertTrue(urlVerify("http://www.jptour.cn/../../../../s_毛里求斯.html"));
        Assert.assertTrue(urlVerify("https://www.cnblogs.com/hubingxu/archive/2012/02/17/2355516.html"));
    }

    @Test
    public void testUrlParse() {
        Assert.assertNull(urlParse(""));
        Assert.assertNull(urlParse(" "));
        Assert.assertNull(urlParse("http://www"));

        URL url1 = urlParse("https://www.baidu.com/s?wd=%E9%A9%AC%E4%BA%91");
        Assert.assertEquals("https", url1.getProtocol());
        Assert.assertEquals("www.baidu.com", url1.getHost());
        Assert.assertEquals("/s", url1.getPath());
        Assert.assertEquals("/s?wd=%E9%A9%AC%E4%BA%91", url1.getFile());
        Assert.assertEquals("wd=%E9%A9%AC%E4%BA%91", url1.getQuery());

        URL url2 = urlParse("http://username:password@www.abc.com:8080/path?arg=value&parameters=passed#with-anchor");
        Assert.assertEquals(8080, url2.getPort());
        Assert.assertEquals("http", url2.getProtocol());
        Assert.assertEquals("username:password", url2.getUserInfo());
        Assert.assertEquals("arg=value&parameters=passed", url2.getQuery());
        Assert.assertEquals("with-anchor", url2.getRef());

        URL url3 = urlParse("//www.example.com/path?googleguy=googley");
        Assert.assertEquals("http", url3.getProtocol());
        Assert.assertEquals("www.example.com", url3.getHost());
    }
}
