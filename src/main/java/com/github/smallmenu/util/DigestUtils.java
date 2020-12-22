package com.github.smallmenu.util;

import com.github.smallmenu.digest.DigestAlgorithm;
import com.github.smallmenu.digest.Hex;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * DigestUtils
 *
 * @author smallmenu
 */
public class DigestUtils {

    /**
     * 禁止实例化
     */
    private DigestUtils() {
        throw new AssertionError();
    }

    /**
     * @param algorithm
     * @return
     */
    public static MessageDigest getDigest(final String algorithm) {
        try {
            return MessageDigest.getInstance(algorithm);
        } catch (final NoSuchAlgorithmException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static MessageDigest getMd5Digest() {
        return getDigest(DigestAlgorithm.MD5.value());
    }

    public static MessageDigest getSha1Digest() {
        return getDigest(DigestAlgorithm.SHA1.value());
    }

    public static MessageDigest getSha256Digest() {
        return getDigest(DigestAlgorithm.SHA256.value());
    }

    public static MessageDigest getSha384Digest() {
        return getDigest(DigestAlgorithm.SHA384.value());
    }

    public static MessageDigest getSha512Digest() {
        return getDigest(DigestAlgorithm.SHA512.value());
    }

    public static byte[] md5(final byte[] data) {
        return getMd5Digest().digest(data);
    }

    public static byte[] md5(final String str) {
        return md5(StringUtils.getBytesUtf8(str));
    }

    public static String md5Hex(final byte[] data) {
        return Hex.encodeHexString(md5(data));
    }

    public static String md5Hex(final String str) {
        return Hex.encodeHexString(md5(str));
    }

    public static byte[] sha1(final byte[] data) {
        return getSha1Digest().digest(data);
    }

    public static byte[] sha1(final String str) {
        return sha1(StringUtils.getBytesUtf8(str));
    }

    public static String sha1Hex(final byte[] data) {
        return Hex.encodeHexString(sha1(data));
    }

    public static String sha1Hex(final String str) {
        return Hex.encodeHexString(sha1(str));
    }

    public static byte[] sha256(final byte[] data) {
        return getSha256Digest().digest(data);
    }

    public static byte[] sha256(final String str) {
        return sha256(StringUtils.getBytesUtf8(str));
    }

    public static String sha256Hex(final byte[] data) {
        return Hex.encodeHexString(sha256(data));
    }

    public static String sha256Hex(final String str) {
        return Hex.encodeHexString(sha256(str));
    }

    public static byte[] sha384(final byte[] data) {
        return getSha384Digest().digest(data);
    }

    public static byte[] sha384(final String str) {
        return sha384(StringUtils.getBytesUtf8(str));
    }

    public static String sha384Hex(final byte[] data) {
        return Hex.encodeHexString(sha384(data));
    }

    public static String sha384Hex(final String str) {
        return Hex.encodeHexString(sha384(str));
    }

    public static byte[] sha512(final byte[] data) {
        return getSha512Digest().digest(data);
    }

    public static byte[] sha512(final String str) {
        return sha512(StringUtils.getBytesUtf8(str));
    }

    public static String sha512Hex(final byte[] data) {
        return Hex.encodeHexString(sha512(data));
    }

    public static String sha512Hex(final String str) {
        return Hex.encodeHexString(sha512(str));
    }
}
