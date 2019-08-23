package com.smile.shirosmiledemo.utils;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author ：Smile(wangyajun)
 * @date ：Created in 2019/8/23 9:44
 * @description：
 */
public class Md5Utils {

    private static Charset charset_utf8 = Charset.forName("UTF-8");

    private static Charset charset_gbk = Charset.forName("GBK");

    public static String md5Encrypt(String str) {
        String md5 = "";
        try {
            MessageDigest digest = MessageDigest.getInstance("MD5");
            byte[] bytes = str.getBytes(charset_utf8);
            bytes = digest.digest(bytes);
            md5 = toHexStr(bytes);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return md5;
    }


    private static String toHexStr(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        int var;
        for (byte b : bytes) {
            var = ((int) b) & 0xFF;
            if (var < 16) {
                sb.append("0");
            }
            sb.append(Integer.toHexString(var));
        }
        return sb.toString().toUpperCase();
    }



}
