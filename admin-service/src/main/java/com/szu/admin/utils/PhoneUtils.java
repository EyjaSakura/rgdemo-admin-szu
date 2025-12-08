package com.szu.admin.utils;

public class PhoneUtils {
    public static boolean isValidPhone(String phone) {
        return phone != null && phone.matches("^1[3-9]\\d{9}$");
    }
}