package com.github.smallmenu.fun;

import java.util.concurrent.ThreadLocalRandom;

import static com.github.smallmenu.Fun.empty;
import static com.github.smallmenu.Fun.removeAny;

/**
 * RandomFun
 *
 * @author smallmenu
 */
public class RandomFun {

    /**
     * 随机字符池
     */
    public static final String RANDOM_CHAR_POOL = "abcdefghijklmnopqrstuvwxyz";

    /**
     * 随机数字池
     */
    public static final String RANDOM_NUM_POOL = "0123456789";

    /**
     * 随机字符和数字池
     */
    public static final String RANDOM_CHAR_NUM_POOL = RANDOM_CHAR_POOL + RANDOM_NUM_POOL;

    /**
     * 禁止实例化
     */
    private RandomFun() {
        throw new AssertionError();
    }

    /**
     * ThreadLocalRandom 对象
     *
     * @return ThreadLocalRandom
     */
    public static ThreadLocalRandom getRandom() {
        return ThreadLocalRandom.current();
    }

    /**
     * 获得随机数
     *
     * @return int
     */
    public static int randomInt() {
        return getRandom().nextInt();
    }

    /**
     * 获得指定范围内的随机数
     *
     * @param min 最小数（包含）
     * @param max 最大数（不包含）
     * @return int
     */
    public static int randomInt(int min, int max) {
        return getRandom().nextInt(min, max);
    }

    /**
     * 获得指定范围内的随机数 [0,max)
     *
     * @param max 限制随机数的范围，不包含这个数
     * @return int
     */
    public static int randomInt(int max) {
        return getRandom().nextInt(max);
    }

    /**
     * 获得指定范围内的随机数[min, max)
     *
     * @param min 最小数（包含）
     * @param max 最大数（不包含）
     * @return long
     */
    public static long randomLong(long min, long max) {
        return getRandom().nextLong(min, max);
    }

    /**
     * 获得随机数
     *
     * @return long
     */
    public static long randomLong() {
        return getRandom().nextLong();
    }

    /**
     * 获得指定范围内的随机数 [0, max)
     *
     * @param max 限制随机数的范围，不包括这个数
     * @return long
     */
    public static long randomLong(long max) {
        return getRandom().nextLong(max);
    }

    /**
     * 获得一个随机的字符串（只包含数字和字符）
     *
     * @param length 字符串的长度
     * @return String
     */
    public static String randomString(int length) {
        return randomString(RANDOM_CHAR_NUM_POOL, length);
    }

    /**
     * 获得一个随机的字符串，排除指定的字符串集
     *
     * @param length  字符串的长度
     * @param excepts 排除的字符串列表
     * @return String
     */
    public static String randomStringExcept(int length, String... excepts) {
        String pool = RANDOM_CHAR_NUM_POOL;
        pool = removeAny(pool, excepts);

        return randomString(pool, length);
    }

    /**
     * 获得一个只包含数字的字符串
     *
     * @param length 字符串的长度
     * @return String
     */
    public static String randomStringNumber(int length) {
        return randomString(RANDOM_NUM_POOL, length);
    }

    /**
     * 获得一个只包含字符的字符串
     *
     * @param length 字符串的长度
     * @return String
     */
    public static String randomStringChar(int length) {
        return randomString(RANDOM_CHAR_POOL, length);
    }

    /**
     * 获得一个随机的字符串
     *
     * @param baseString 随机字符选取的样本集
     * @param length     字符串的长度
     * @return String
     */
    public static String randomString(String baseString, int length) {
        if (empty(baseString)) {
            return StringFun.EMPTY;
        }

        final StringBuilder sb = new StringBuilder(length);

        if (length < 1) {
            length = 1;
        }
        int baseLength = baseString.length();
        for (int i = 0; i < length; i++) {
            int number = randomInt(baseLength);
            sb.append(baseString.charAt(number));
        }

        return sb.toString();
    }
}
