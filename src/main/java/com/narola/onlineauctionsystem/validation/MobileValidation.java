package com.narola.onlineauctionsystem.validation;

public class MobileValidation {
    public static boolean isValidMobileNumber(String number) {
        String pattern = "[1-9]\\d{9}$";
        return number.matches(pattern);
    }
}
