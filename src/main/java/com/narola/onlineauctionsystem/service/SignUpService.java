package com.narola.onlineauctionsystem.service;

import com.narola.onlineauctionsystem.dao.UserDao;
import com.narola.onlineauctionsystem.dto.SignUpDTO;
import com.narola.onlineauctionsystem.exception.DaoException;
import com.narola.onlineauctionsystem.model.User;

public class SignUpService {
    private static UserDao userDao = new UserDao();
    public static User createUser(SignUpDTO signUpDTO) throws DaoException {
        return userDao.addUserDetails(signUpDTO);
    }
}