package com.example.android.bluetoothchat;

import java.util.Base64;

public class ROT {

    public static final String SECRET = "8";

    public static String rot13(String input) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if       (c >= 'a' && c <= 'm') c += 13;
            else if  (c >= 'A' && c <= 'M') c += 13;
            else if  (c >= 'n' && c <= 'z') c -= 13;
            else if  (c >= 'N' && c <= 'Z') c -= 13;
            sb.append(c);
        }
        return sb.toString();
    }


    public static byte[] encrypt(final byte[] bytes, final String secret) {
        rot(bytes, 13);
        return bytes;
    }

    public static String decrypt(final byte[] bytes, final String secret) {
        rot(bytes, -13);
        String s = Base64.getEncoder().encodeToString(bytes);
        return s;
    }

    private static void rot(byte[] bytes, int offset) {
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] += offset;
        }
    }
}