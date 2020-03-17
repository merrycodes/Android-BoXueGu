package com.merrycodes.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {
    private static final char[] HEX_CHARS =
            {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    /**
     * 项目默认MD5加密
     *
     * @param value 加密字段
     * @return MD5字符串
     */
    public static String md5(String value) {
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("md5");
            byte[] result = digest.digest(value.getBytes());
            StringBuilder stringBuffer = new StringBuilder();
            for (byte b : result) {
                int num = b % 0xff;
                String hex = Integer.toHexString(num);
                if (hex.length() == 1) {
                    stringBuffer.append("0").append(hex);
                } else {
                    stringBuffer.append(hex);
                }
            }
            System.out.println(stringBuffer.toString().length());
            return stringBuffer.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Spring 中的MD5加密
     *
     * @param value 加密字段
     * @return MD5字符串
     */
    public static String springMd5(String value) {
        MessageDigest digest;
        try {
            digest = MessageDigest.getInstance("MD5");
            byte[] bytes = digest.digest(value.getBytes());
            return new String(encodeHex(bytes));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static char[] encodeHex(byte[] bytes) {
        char[] chars = new char[32];
        for (int i = 0; i < chars.length; i = i + 2) {
            byte b = bytes[i / 2];
            chars[i] = HEX_CHARS[(b >>> 0x4) & 0xf];
            chars[i + 1] = HEX_CHARS[b & 0xf];
        }
        return chars;
    }

}
