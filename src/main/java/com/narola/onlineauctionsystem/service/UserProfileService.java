package com.narola.onlineauctionsystem.service;

import com.narola.onlineauctionsystem.dao.UserDao;
import com.narola.onlineauctionsystem.dto.SignUpDTO;
import com.narola.onlineauctionsystem.exception.DaoException;
import com.narola.onlineauctionsystem.model.User;

public class UserProfileService {
    private static UserDao userDao = new UserDao();

    public static void editUserProfile(SignUpDTO signUpDTO) throws DaoException {
        userDao.editUserProfile(signUpDTO);
    }

    public User getUserById(String userId) {
        return userDao.getUserById(userId);
    }
}
