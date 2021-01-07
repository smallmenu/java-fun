package com.github.smallmenu.fun;

import com.github.smallmenu.digest.DigestAlgorithm;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static com.github.smallmenu.Fun.bytes;

/**
 * DigestUtils
 *
 * @author smallmenu
 */
public class DigestFun {

    /**
     * 禁止实例化
     */
    private DigestFun() {
        throw new AssertionError();
    }

    /**
     * 摘要算法工厂
     *
     * @param algorithm 摘要算法字符串表示
     * @return MessageDigest
     */
    public static MessageDigest getDigest(final String algorithm) {
        try {
            return MessageDigest.getInstance(algorithm);
        } catch (final NoSuchAlgorithmException e) {
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * MD5 MessageDigest
     *
     * @return MessageDigest
     */
    public static MessageDigest getMd5Digest() {
        return getDigest(DigestAlgorithm.MD5.value());
    }

    /**
     * SHA1 MessageDigest
     *
     * @return MessageDigest
     */
    public static MessageDigest getSha1Digest() {
        return getDigest(DigestAlgorithm.SHA1.value());
    }

    /**
     * SHA256 MessageDigest
     *
     * @return MessageDigest
     */
    public static MessageDigest getSha256Digest() {
        return getDigest(DigestAlgorithm.SHA256.value());
    }

    /**
     * SHA384 MessageDigest
     *
     * @return MessageDigest
     */
    public static MessageDigest getSha384Digest() {
        return getDigest(DigestAlgorithm.SHA384.value());
    }

    /**
     * SHA512 MessageDigest
     *
     * @return MessageDigest
     */
    public static MessageDigest getSha512Digest() {
        return getDigest(DigestAlgorithm.SHA512.value());
    }

    /**
     * md5
     *
     * @return byte[]
     */
    public static byte[] md5(final byte[] data) {
        return getMd5Digest().digest(data);
    }

    /**
     * MD5
     *
     * @return byte[]
     */
    public static byte[] md5(final String str) {
        return md5(bytes(str));
    }

    /**
     * MD5
     *
     * @return String
     */
    public static String md5Hex(final byte[] data) {
        return HexFun.encodeHexString(md5(data));
    }

    /**
     * MD5
     *
     * @return String
     */
    public static String md5Hex(final String str) {
        return HexFun.encodeHexString(md5(str));
    }

    /**
     * SHA1
     *
     * @return byte[]
     */
    public static byte[] sha1(final byte[] data) {
        return getSha1Digest().digest(data);
    }

    /**
     * SHA1
     *
     * @return byte[]
     */
    public static byte[] sha1(final String str) {
        return sha1(bytes(str));
    }

    /**
     * SHA1
     *
     * @return String
     */
    public static String sha1Hex(final byte[] data) {
        return HexFun.encodeHexString(sha1(data));
    }

    /**
     * SHA1
     *
     * @return String
     */
    public static String sha1Hex(final String str) {
        return HexFun.encodeHexString(sha1(str));
    }

    /**
     * SHA256
     *
     * @return byte[]
     */
    public static byte[] sha256(final byte[] data) {
        return getSha256Digest().digest(data);
    }

    /**
     * SHA256
     *
     * @return byte[]
     */
    public static byte[] sha256(final String str) {
        return sha256(bytes(str));
    }

    /**
     * SHA256
     *
     * @return String
     */
    public static String sha256Hex(final byte[] data) {
        return HexFun.encodeHexString(sha256(data));
    }

    /**
     * SHA256
     *
     * @return String
     */
    public static String sha256Hex(final String str) {
        return HexFun.encodeHexString(sha256(str));
    }

    /**
     * SHA384
     *
     * @return byte[]
     */
    public static byte[] sha384(final byte[] data) {
        return getSha384Digest().digest(data);
    }

    /**
     * SHA384
     *
     * @return byte[]
     */
    public static byte[] sha384(final String str) {
        return sha384(bytes(str));
    }

    /**
     * SHA384
     *
     * @return String
     */
    public static String sha384Hex(final byte[] data) {
        return HexFun.encodeHexString(sha384(data));
    }

    /**
     * SHA384
     *
     * @return String
     */
    public static String sha384Hex(final String str) {
        return HexFun.encodeHexString(sha384(str));
    }

    /**
     * SHA512
     *
     * @return byte[]
     */
    public static byte[] sha512(final byte[] data) {
        return getSha512Digest().digest(data);
    }

    /**
     * SHA512
     *
     * @return byte[]
     */
    public static byte[] sha512(final String str) {
        return sha512(bytes(str));
    }

    /**
     * SHA512
     *
     * @return String
     */
    public static String sha512Hex(final byte[] data) {
        return HexFun.encodeHexString(sha512(data));
    }

    /**
     * SHA512
     *
     * @return String
     */
    public static String sha512Hex(final String str) {
        return HexFun.encodeHexString(sha512(str));
    }
}
