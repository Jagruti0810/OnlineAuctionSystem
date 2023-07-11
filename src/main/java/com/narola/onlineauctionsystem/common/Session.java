package com.narola.onlineauctionsystem.common;

import com.narola.onlineauctionsystem.model.User;

public class Session {
    private static User currentUser = null;

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User currentUser) {
        Session.currentUser = currentUser;
    }

    public boolean isSeller() {
        return currentUser.getRoleId() == 2;
    }
}
