package com.narola.onlineauctionsystem.servlet.user;

import com.narola.onlineauctionsystem.dto.CredentialDTO;
import com.narola.onlineauctionsystem.exception.Error;
import com.narola.onlineauctionsystem.model.User;
import com.narola.onlineauctionsystem.service.LoginService;
import com.narola.onlineauctionsystem.validation.UserValidation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginServlet extends HttpServlet {
    private CredentialDTO credentialDTO = new CredentialDTO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        response.setContentType("text/html");
        credentialDTO.setEmail(request.getParameter("email"));
        credentialDTO.setPassword(request.getParameter("password"));
        credentialDTO.setVerificationCode(request.getParameter("verificationCode"));
        List<Error> errorList = UserValidation.validate(credentialDTO);
        if (!errorList.isEmpty()) {
            request.setAttribute("errorList", errorList);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("login.jsp");
            requestDispatcher.forward(request, response);
            return;
        }

        User user = LoginService.getUserByCredentials(credentialDTO);
        if (user == null) {
            errorList = new ArrayList<>();
            request.setAttribute("errorList", errorList);
            errorList.add(new Error("Wrong credentials"));
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("login.jsp");
            requestDispatcher.forward(request, response);
            return;
        }
        if (user != null) {
            HttpSession session = request.getSession(true);
            session.setAttribute("user", user);
            if (user.isVerified()) {
                int role = user.getRoleId();
                if (role == 1) {
                    response.sendRedirect(request.getContextPath() + "/homePage");
                } else if (role == 2) {
                    response.sendRedirect(request.getContextPath() + "/sellerDashboard");
                } else if (role == 3) {
                    response.sendRedirect(request.getContextPath() + "/bidderDashboard");
                } else {
                    response.sendRedirect("error.jsp");
                }
            } else {
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("checkVerification.jsp");
                requestDispatcher.forward(request, response);
            }
        } else {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("seller.jsp");
            requestDispatcher.forward(request, response);
        }
    }
}






