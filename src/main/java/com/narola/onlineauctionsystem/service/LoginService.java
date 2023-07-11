package com.narola.onlineauctionsystem.service;

import com.narola.onlineauctionsystem.dao.UserDao;
import com.narola.onlineauctionsystem.dto.CredentialDTO;
import com.narola.onlineauctionsystem.model.User;

public class LoginService {
    private static UserDao userDao = new UserDao();

    public static User getUserByCredentials(CredentialDTO credentialDTO) {
        return userDao.getUserCredentials(credentialDTO);
    }
}

