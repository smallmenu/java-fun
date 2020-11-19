package com.github.smallmenu.util;

/**
 * FunUtils
 *
 * @author smallmenu
 */
public class FunUtils {
    public static final String SIZE_KB = "K";
    public static final String SIZE_MB = "M";
    public static final String SIZE_GB = "G";
    public static final String SIZE_TB = "T";

    /**
     * 格式化转换大小
     *
     * @param bytes  字节大小
     * @param suffix 后缀标识
     * @return long
     */
    public static long sizeFormat(long bytes, final String suffix) {
        if (bytes > 0) {
            switch (suffix.toUpperCase()) {
                case "K":
                    return (bytes / 1024);
                case "M":
                    return (bytes / 1024 / 1024);
                case "G":
                    return (bytes / 1024 / 1024 / 1024);
                case "T":
                    return (bytes / 1024 / 1024 / 1024 / 1024);
                default:
                    return bytes;
            }
        }

        return bytes;
    }
}
