package com.github.smallmenu.digest;

import com.github.smallmenu.constant.CharEncoding;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * Hex
 *
 * @author smallmenu
 */
public class Hex {

    public static final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;


    public static final String DEFAULT_CHARSET_NAME = CharEncoding.UTF_8;


    private static final char[] DIGITS_LOWER = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};


    private static final char[] DIGITS_UPPER = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    public static byte[] decodeHex(final char[] data) throws Exception {
        final byte[] out = new byte[data.length >> 1];
        decodeHex(data, out, 0);
        return out;
    }

    public static int decodeHex(final char[] data, final byte[] out, final int outOffset) throws Exception {
        final int len = data.length;

        if ((len & 0x01) != 0) {
            throw new Exception("Odd number of characters.");
        }

        final int outLen = len >> 1;
        if (out.length - outOffset < outLen) {
            throw new Exception("Output array is not large enough to accommodate decoded data.");
        }

        // two characters form the hex value.
        for (int i = outOffset, j = 0; j < len; i++) {
            int f = toDigit(data[j], j) << 4;
            j++;
            f = f | toDigit(data[j], j);
            j++;
            out[i] = (byte) (f & 0xFF);
        }

        return outLen;
    }

    protected static int toDigit(final char ch, final int index) throws Exception {
        final int digit = Character.digit(ch, 16);
        if (digit == -1) {
            throw new Exception("Illegal hexadecimal character " + ch + " at index " + index);
        }
        return digit;
    }

    public static String encodeHexString(final byte[] data) {
        return new String(encodeHex(data));
    }

    public static char[] encodeHex(final byte[] data) {
        return encodeHex(data, true);
    }

    public static char[] encodeHex(final byte[] data, final boolean toLowerCase) {
        return encodeHex(data, toLowerCase ? DIGITS_LOWER : DIGITS_UPPER);
    }

    protected static char[] encodeHex(final byte[] data, final char[] toDigits) {
        final int l = data.length;
        final char[] out = new char[l << 1];
        encodeHex(data, 0, data.length, toDigits, out, 0);
        return out;
    }

    private static void encodeHex(final byte[] data, final int dataOffset, final int dataLen, final char[] toDigits, final char[] out, final int outOffset) {
        // two characters form the hex value.
        for (int i = dataOffset, j = outOffset; i < dataOffset + dataLen; i++) {
            out[j++] = toDigits[(0xF0 & data[i]) >>> 4];
            out[j++] = toDigits[0x0F & data[i]];
        }
    }
}
