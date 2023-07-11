package com.narola.onlineauctionsystem.servlet.user;

import com.narola.onlineauctionsystem.dao.UserDao;
import com.narola.onlineauctionsystem.exception.DaoException;
import com.narola.onlineauctionsystem.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class VerificationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("checkVerification.jsp");
        requestDispatcher.forward(req, resp);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserDao userDao = new UserDao();
        String userId = request.getParameter("userId");
        String verificationCode = request.getParameter("code");
        User user = null;// Call to dao get User by ID
        if (user == null) {
            try {
                user = userDao.getUserById(userId);
                if (verificationCode.equals(user.getVerificationCode())) {
                    userDao.checkUserVerified(true, user.getEmail());
                    response.sendRedirect("login.jsp");
                }
            } catch (DaoException e) {
                e.printStackTrace();
            }
        }
//        try {
//            userDao.checkUserVerified(true, userId);
//        } catch (DaoException e) {
//            e.printStackTrace();
//        }

//        user = (User) session.getAttribute("user");
        if (user != null) {
            if (verificationCode.equals(user.getVerificationCode())) {
                try {
                    userDao.checkUserVerified(true, user.getEmail());
                } catch (DaoException e) {
                    e.printStackTrace();
                }
            }
        }
//        HttpSession session = request.getSession(false);

//        if (session.getAttribute("prevURL").toString().equals("/verifyCode")) {
//            response.sendRedirect("/login");
//        } else if (session.getAttribute("prevURL").toString().equals("/login")) {
//            response.sendRedirect("/home-dashboard");
//        }
//        if (session != null) {
//            String prevURL = (String) session.getAttribute("prevURL");
//            if (prevURL != null) {
//                if (prevURL.equals("/registration")) {
//                    response.sendRedirect("/login");
//                } else if (prevURL.equals("/login")) {
//                    response.sendRedirect("/seller.jsp");
//                }
//            }
//        }
    }
}
//public class VerificationServlet extends HttpServlet {
//
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        RequestDispatcher requestDispatcher = req.getRequestDispatcher("checkVerification.jsp");
//        requestDispatcher.forward(req, resp);
//    }
//
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        UserDao userDao = new UserDao();
//        String userId = request.getParameter("userId");
//        String verificationCode = request.getParameter("code");
//        User user = null;
//        if (user != null) {
//            if (verificationCode.equals(user.getVerificationCode())) {
//                try {
//                    userDao.checkUserVerified(true, user.getEmail());
//                } catch (DaoException e) {
//                    e.printStackTrace();
//                }
//            }
//        } else {
//            try {
//                User user1 = userDao.getUserById(userId);
//                if (verificationCode.equals(user1.getVerificationCode())) {
//                    userDao.checkUserVerified(true, user1.getEmail());
//                }
//            } catch (DaoException e) {
//                e.printStackTrace();
//            }
//        }
//
//        HttpSession session = request.getSession(false);
//        if (session != null) {
//            String prevURL = (String) session.getAttribute("prevURL");
//            if (prevURL != null) {
//                if (prevURL.equals("/registration")) {
//                    response.sendRedirect("/login");
//                } else if (prevURL.equals("/login")) {
//                    response.sendRedirect("/home-dashboard");
//                }
//            }
//        }
//    }
//}
//
