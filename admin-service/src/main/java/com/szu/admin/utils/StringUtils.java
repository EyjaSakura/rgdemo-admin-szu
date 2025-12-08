package com.szu.admin.utils;

public class StringUtils {
    public static String blankToNull(String s) {
        return (s == null || s.isBlank()) ? null : s;
    }
}