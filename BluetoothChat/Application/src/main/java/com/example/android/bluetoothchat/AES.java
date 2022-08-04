package com.example.android.bluetoothchat;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class AES {

    public static final String SECRET_KEY = "OffTrack-SpecialSecret";
    private static SecretKeySpec secretKey;
    private static byte[] key;

    public static void setKey(final String myKey) {
        MessageDigest sha = null;
        try {
            key = myKey.getBytes("UTF-8");
            sha = MessageDigest.getInstance("SHA-1");
            key = sha.digest(key);
            key = Arrays.copyOf(key, 16);
            secretKey = new SecretKeySpec(key, "AES");
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public static byte[] encrypt(final byte[] bytes, final String secret) {
        try {
            setKey(secret);
            Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return cipher.doFinal(bytes);
        } catch (Exception e) {
            System.out.println("Error while encrypting: " + e.toString());
        }
        return null;
    }

    public static String encrypt(final String strToEncrypt, final String secret) {
        try {
            byte[] encrypted = encrypt(strToEncrypt.getBytes("UTF-8"), secret);
            return Base64.getEncoder().encodeToString(encrypted);
        } catch (UnsupportedEncodingException e) {
            System.out.println("Error while encoding to string: " + e.toString());
        }
        return "";
    }

    public static String decrypt(final byte[] bytes, final String secret) {
        try {
            setKey(secret);
            Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);

            return new String(cipher.doFinal(bytes));
        } catch (Exception e) {
            System.out.println("Error while decrypting: " + e.toString());
        }
        return "";
    }

    public static String decrypt(final String strToDecrypt, final String secret) {
        byte[] bytes = Base64.getDecoder().decode(strToDecrypt);
        return decrypt(bytes, secret);
    }

    public static void main(String[] args) {
        final String secretKey = "ssshhhhhhhhhhh!!!!";

        String originalString = "howtodoinjava.com";
        String encryptedString = AES.encrypt(originalString, secretKey) ;
        String decryptedString = AES.decrypt(encryptedString, secretKey) ;

        System.out.println(originalString);
        System.out.println(encryptedString);
        System.out.println(decryptedString);
    }
}