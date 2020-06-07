package com.nancy.shirothymeleafdemo.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Base64;

/**
 * @author chen
 * @date 2020/6/3 0:46
 */
public class Md5Util {
    private static final int NUM = 1024;

    public static String onEncrypt(String info) throws Exception {
        return encrypt(info, NUM);
    }

    private static String encrypt(String info, int i) throws Exception {
        MessageDigest digest = MessageDigest.getInstance("MD5");
        digest.update(info.getBytes(StandardCharsets.UTF_8));
        byte[] b = digest.digest();
        String result = Base64.getEncoder().encodeToString(b);
        i -= 1;
        if (i > 0) {
            result = encrypt(result, i);
        }
        return result;
    }
}
