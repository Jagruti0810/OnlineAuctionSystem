package com.narola.onlineauctionsystem.servlet.seller;

import com.narola.onlineauctionsystem.dao.UserDao;
import com.narola.onlineauctionsystem.dto.SignUpDTO;
import com.narola.onlineauctionsystem.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SellerProfileServlet extends HttpServlet {
    private SignUpDTO signUpDTO = new SignUpDTO();
    private UserDao userDao = new UserDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        int userId = user.getUserId();
        signUpDTO.setUserId(userId);
        userDao.getUseProfileDetails(userId);
        req.setAttribute("user", user);
        RequestDispatcher dispatcher = req.getRequestDispatcher("bidderProfile.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
