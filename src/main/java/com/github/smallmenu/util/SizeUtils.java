package com.github.smallmenu.util;

/**
 * SizeUtils
 *
 * @author smallmenu
 */
final public class SizeUtils {
    public static final String KB = "KB";
    public static final String MB = "MB";
    public static final String GB = "GB";
    public static final String TB = "TB";
    public static final String PB = "PB";

    /**
     * 禁止实例化
     */
    private SizeUtils() {
        throw new AssertionError();
    }

    /**
     * 格式化转换大小
     *
     * @param bytes  字节大小
     * @param suffix 后缀标识
     * @return long
     */
    public static long format(long bytes, final String suffix) {
        if (bytes > 0) {
            switch (suffix.toUpperCase()) {
                case KB:
                    return (bytes / 1024);
                case MB:
                    return (bytes / 1024 / 1024);
                case GB:
                    return (bytes / 1024 / 1024 / 1024);
                case TB:
                    return (bytes / 1024 / 1024 / 1024 / 1024);
                case PB:
                    return (bytes / 1024 / 1024 / 1024 / 1024 / 1024);
                default:
                    return bytes;
            }
        }

        return bytes;
    }
}
