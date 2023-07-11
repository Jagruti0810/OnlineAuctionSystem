package com.narola.onlineauctionsystem.common;

public class Utility {
    private Utility()
    {

    }
    public static String generateVerificationCode() {
        int code = (int) (Math.random() * 900000 + 100000);
        return String.valueOf(code);
    }
}
