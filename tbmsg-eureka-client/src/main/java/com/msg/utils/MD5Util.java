package com.msg.utils;

import java.security.MessageDigest;
import java.util.Base64;

public class MD5Util {
    public static String encoderByMd5(String str) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            return Base64.getEncoder().encodeToString(md5.digest(str.getBytes("utf-8")));
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }
}
