package com.narola.onlineauctionsystem.validation;

import com.narola.onlineauctionsystem.dto.CredentialDTO;
import com.narola.onlineauctionsystem.dto.SignUpDTO;
import com.narola.onlineauctionsystem.exception.Error;

import java.util.ArrayList;
import java.util.List;

public class UserValidation {
    public static List<Error> validate(CredentialDTO credentialDTO) {
        List<Error> errorList = new ArrayList<>();
        if (Validation.isEmpty(credentialDTO.getEmail())) {
            errorList.add(new Error("Please enter email"));
        }
        if (Validation.isEmpty(credentialDTO.getPassword())) {
            errorList.add(new Error("Please enter password"));
        }
        return errorList;
    }

    public static List<Error> validateUserCredentials(SignUpDTO signUpDTO) {
        List<Error> errorList = new ArrayList<>();
        if (Validation.isEmpty(signUpDTO.getRoleId())) {
            errorList.add(new Error("Please select your role"));
        }
        if (Validation.isEmpty(signUpDTO.getUsername())) {
            errorList.add(new Error("Please enter user name"));
        }
        if (Validation.isEmpty(signUpDTO.getMobileNo())) {
            errorList.add(new Error("Please enter mobile no"));
        }
        if (Validation.isEmpty(signUpDTO.getEmail())) {
            errorList.add(new Error("Please enter email"));
        }
        if (Validation.isEmpty(signUpDTO.getAddress())) {
            errorList.add(new Error("Please enter address"));
        }
        if (Validation.isEmpty(signUpDTO.getPassword())) {
            errorList.add(new Error("Please enter password"));
        }
        return errorList;
    }

    public static List<Error> validateUserProfileCredentials(SignUpDTO signUpDTO) {
        List<Error> errorList = new ArrayList<>();
        if (Validation.isEmpty(signUpDTO.getUsername())) {
            errorList.add(new Error("Please enter user name"));
        }
        if (Validation.isEmpty(signUpDTO.getMobileNo())) {
            errorList.add(new Error("Please enter mobile no"));
        }
        if (Validation.isEmpty(signUpDTO.getEmail())) {
            errorList.add(new Error("Please enter email"));
        }
        if (Validation.isEmpty(signUpDTO.getAddress())) {
            errorList.add(new Error("Please enter address"));
        }
        return errorList;
    }
}
