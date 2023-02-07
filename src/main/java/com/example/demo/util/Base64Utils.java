package com.example.demo.util;

public class Base64Utils {
    private Base64Utils() {
        throw new IllegalStateException("Utility class");
    }
    public static String encodeByBase64(String content) {
        return org.springframework.util.Base64Utils.encodeToString(content.getBytes());
    }
}
