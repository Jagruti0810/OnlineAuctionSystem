package com.narola.onlineauctionsystem.servlet.user;

import com.narola.onlineauctionsystem.common.SendEmailToUser;
import com.narola.onlineauctionsystem.common.Utility;
import com.narola.onlineauctionsystem.dao.UserDao;
import com.narola.onlineauctionsystem.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

//public class ResendEmailServlet extends HttpServlet {
//    private UserDao userDao = new UserDao();
//    private SignUpDTO signUpDTO = new SignUpDTO();
//    private SendEmailToUser sendEmailToUser = new SendEmailToUser();
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        HttpSession session = req.getSession();
//        User user = (User) session.getAttribute("user");
//        if (user != null) {
//            String verificationCode = Utility.generateVerificationCode();
//            signUpDTO.setUsername(user.getUsername());
//            signUpDTO.setEmail(user.getEmail());
//            user.setVerificationCode(verificationCode);
//            userDao.updateVerificationCode(user.getEmail(), verificationCode);
//            User user1 = userDao.getUserById(String.valueOf(user.getUserId()));
//            sendMail(user1);
//            RequestDispatcher requestDispatcher = req.getRequestDispatcher("checkVerification.jsp");
//            requestDispatcher.forward(req, resp);
//            String verificationCode1 = req.getParameter("code");
//            if (verificationCode1.equals(user.getVerificationCode())) {
//                try {
//                    userDao.checkUserVerified(true, user.getEmail());
//                } catch (DaoException e) {
//                    e.printStackTrace();
//                }
//            }
//        } else {
//            String userId = req.getParameter("userId");
//            if (user == null) {
//                String verificationCode1 = req.getParameter("code");
//                try {
//                    user = userDao.getUserById(userId);
//                    if (verificationCode1.equals(user.getVerificationCode())) {
//                        userDao.checkUserVerified(true, user.getEmail());
//                        resp.sendRedirect("login.jsp");
//                    }
//                } catch (DaoException e) {
//                    e.printStackTrace();
//                }
//            }
//            RequestDispatcher requestDispatcher = req.getRequestDispatcher("login.jsp");
//            requestDispatcher.forward(req, resp);
//        }
//    }
//    private void sendMail(User user) {
//        StringBuilder mailContent = new StringBuilder();
//        mailContent.append("<H1>")
//                .append("Hi, ").append(user.getUsername())
//                .append("</H1>")
//                .append("Your verification code :").append(user.getVerificationCode());
//        sendEmailToUser.sendMail(user.getEmail(), "Online Auction: Account verification code", mailContent.toString());
//    }
//}

public class ResendEmailServlet extends HttpServlet {
    private UserDao userDao = new UserDao();
    private SendEmailToUser sendEmailToUser = new SendEmailToUser();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        if (user != null) {
            String verificationCode = Utility.generateVerificationCode();
            user.setVerificationCode(verificationCode);
            userDao.updateVerificationCode(user.getEmail(), verificationCode);
            sendMail(user);
            req.setAttribute("userId", user.getUserId());
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("checkVerification.jsp");
            requestDispatcher.forward(req, resp);
        } else {
            String userId = req.getParameter("userId");
            User user1 = userDao.getUserById(userId);
            String verificationCode1 = Utility.generateVerificationCode();
            user1.setVerificationCode(verificationCode1);
            userDao.updateVerificationCode(user1.getEmail(), verificationCode1);
            sendMail(user1);
            req.setAttribute("userId", user1.getUserId());
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("checkVerification.jsp");
            requestDispatcher.forward(req, resp);
        }
    }
//            if (userId != null && verificationCode != null) {
//                try {
//                    user = userDao.getUserById(userId);
//                    if (verificationCode.equals(user.getVerificationCode())) {
//                        userDao.checkUserVerified(true, user.getEmail());
//                        return;
//                    }
//                } catch (DaoException e) {
//                    e.printStackTrace();
//                }
//            }
//        RequestDispatcher requestDispatcher = req.getRequestDispatcher("login.jsp");
//        requestDispatcher.forward(req, resp);


    private void sendMail(User user) {
        StringBuilder mailContent = new StringBuilder();
        mailContent.append("<h1>")
                .append("Hi, ").append(user.getUsername())
                .append("</h1>")
                .append("Your verification code: ").append(user.getVerificationCode());
        sendEmailToUser.sendMail(user.getEmail(), "Online Auction: Account Verification Code", mailContent.toString());
    }
}

