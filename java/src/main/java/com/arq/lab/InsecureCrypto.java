package com.arq.lab;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.MessageDigest;
import java.util.Base64;

public class InsecureCrypto {

    // Weak: hardcoded key + AES/ECB + MD5
    private static final String HARDCODED_KEY = "0123456789ABCDEF";

    public static String md5Hex(String input) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5"); // weak
        byte[] digest = md.digest(input.getBytes("UTF-8"));
        StringBuilder sb = new StringBuilder();
        for (byte b : digest) sb.append(String.format("%02x", b));
        return sb.toString();
    }

    public static String aesEcbEncrypt(String plaintext) throws Exception {
        SecretKeySpec key = new SecretKeySpec(HARDCODED_KEY.getBytes("UTF-8"), "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding"); // insecure mode
        cipher.init(Cipher.ENCRYPT_MODE, key);
        return Base64.getEncoder().encodeToString(cipher.doFinal(plaintext.getBytes("UTF-8")));
    }
}
