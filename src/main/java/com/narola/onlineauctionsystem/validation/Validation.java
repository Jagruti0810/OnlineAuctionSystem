package com.narola.onlineauctionsystem.validation;

public class Validation {
    private static EmailValidation emailValidation = new EmailValidation();
    private static MobileValidation mobileValidation = new MobileValidation();
    private static PasswordValidation passwordValidation = new PasswordValidation();

    public static boolean isEmpty(String value) {
        if (value == null || value.trim().isEmpty()) {
            return true;
        }
        return false;
    }
    public static boolean isEmpty(Double value) {
        if (value == null) {
            return true;
        }
        return false;
    }

    public static boolean isEmailValid(String emailId) {
        return emailValidation.validate(emailId);
    }

    public static boolean isMobileValid(String mobileNo) {
        return mobileValidation.isValidMobileNumber(mobileNo);
    }

    public static boolean isPasswordValid(String password) {
        return passwordValidation.validatePassword(password);
    }

}

