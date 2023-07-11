package com.narola.onlineauctionsystem.servlet.bidder;

import com.narola.onlineauctionsystem.dto.SignUpDTO;
import com.narola.onlineauctionsystem.exception.DaoException;
import com.narola.onlineauctionsystem.exception.Error;
import com.narola.onlineauctionsystem.model.User;
import com.narola.onlineauctionsystem.service.UserProfileService;
import com.narola.onlineauctionsystem.validation.UserValidation;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class EditBidderProfileServlet extends HttpServlet {
    private UserProfileService userProfileService = new UserProfileService();
    private SignUpDTO signUpDTO = new SignUpDTO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userId = req.getParameter("id");
        User user = userProfileService.getUserById(userId);
        if (user != null) {
            req.setAttribute("user", user);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("editBidderProfile.jsp");
            requestDispatcher.forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        signUpDTO.setUsername(req.getParameter("user-name"));
        signUpDTO.setMobileNo(req.getParameter("mobile-no"));
        signUpDTO.setEmail(req.getParameter("email"));
        signUpDTO.setAddress(req.getParameter("address"));
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        int userId = user.getUserId();
        signUpDTO.setUserId(userId);
        List<Error> errorList = UserValidation.validateUserProfileCredentials(signUpDTO);
        if (!errorList.isEmpty()) {
            req.setAttribute("errorList", errorList);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("editBidderProfile.jsp");
            requestDispatcher.forward(req, resp);
            return;
        } else {
            try {
                userProfileService.editUserProfile(signUpDTO);
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("bidder.jsp");
                requestDispatcher.forward(req, resp);
            } catch (DaoException e) {
                e.printStackTrace();
            }
        }
    }
}
