package com.github.smallmenu.util;

import org.junit.Test;


public class RandomUtilsTest {

    @Test
    public void testRandomInt() {
        System.out.println(RandomUtils.randomInt());
        System.out.println(RandomUtils.randomInt(100));
        System.out.println(RandomUtils.randomInt(100, 200));
    }

    @Test
    public void testRandomLong() {
        System.out.println(RandomUtils.randomLong());
        System.out.println(RandomUtils.randomLong(100));
        System.out.println(RandomUtils.randomLong(100, 200));
    }

    @Test
    public void testRandomString() {
        System.out.println(RandomUtils.randomString(10));
        System.out.println(RandomUtils.randomStringChar(10));
        System.out.println(RandomUtils.randomStringNumber(10));
        System.out.println(RandomUtils.randomStringExcept(32, "o", "0"));
    }
}
