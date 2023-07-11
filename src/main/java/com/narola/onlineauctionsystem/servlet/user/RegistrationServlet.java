package com.narola.onlineauctionsystem.servlet.user;

import com.narola.onlineauctionsystem.common.SendEmailToUser;
import com.narola.onlineauctionsystem.common.Utility;
import com.narola.onlineauctionsystem.dao.RoleDao;
import com.narola.onlineauctionsystem.dto.SignUpDTO;
import com.narola.onlineauctionsystem.exception.DaoException;
import com.narola.onlineauctionsystem.exception.Error;
import com.narola.onlineauctionsystem.model.Role;
import com.narola.onlineauctionsystem.model.User;
import com.narola.onlineauctionsystem.service.SignUpService;
import com.narola.onlineauctionsystem.validation.UserValidation;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class RegistrationServlet extends HttpServlet {

    private SignUpDTO signUpDTO = new SignUpDTO();
    private SendEmailToUser sendEmailToUser = new SendEmailToUser();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        fillSignUpMasterData(req);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("signup.jsp");
        requestDispatcher.forward(req, resp);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        signUpDTO.setUsername(request.getParameter("username"));
        signUpDTO.setEmail(request.getParameter("email"));
        signUpDTO.setMobileNo(request.getParameter("mobileNo"));
        signUpDTO.setAddress(request.getParameter("address"));
        signUpDTO.setPassword(request.getParameter("password"));
        signUpDTO.setRoleId(request.getParameter("roleId"));
        List<Error> errorList = UserValidation.validateUserCredentials(signUpDTO);
        if (!errorList.isEmpty()) {
            fillSignUpMasterData(request);
            request.setAttribute("errorList", errorList);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("signup.jsp");
            requestDispatcher.forward(request, response);
            return;
        }
        try {
            String verificationCode = Utility.generateVerificationCode();
            signUpDTO.setVerificationCode(verificationCode);
            User user = SignUpService.createUser(signUpDTO);
            sendMail(signUpDTO);
//            response.sendRedirect(response.encodeRedirectURL("/verifyCode?userId="+user.getUserId()+"prevURL=/login"));
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            session.setAttribute("prevURL", request.getRequestURI());

            request.setAttribute("userId", signUpDTO.getUserId());
//            response.sendRedirect("verifyCode");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("checkVerification.jsp");
            requestDispatcher.forward(request, response);
        } catch (DaoException e) {
            e.printStackTrace();
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("error.jsp");
            requestDispatcher.forward(request, response);
        }
    }

    private void fillSignUpMasterData(HttpServletRequest request) {
        RoleDao roleDao = new RoleDao();
        List<Role> roles = roleDao.getRoles();
        request.setAttribute("Role", roles);
    }

    private void sendMail(SignUpDTO signUpDTO) {
        StringBuilder mailContent = new StringBuilder();
        mailContent.append("<h1>")
                .append("Hi, ").append(signUpDTO.getUsername())
                .append("</h1>")
                .append("Thank you for signing up with our website.<br>")
                .append("Your unique user ID: ").append(signUpDTO.getVerificationCode()).append("<br>")
                .append("Please use the verification code sent to your email to complete the registration process.");
        sendEmailToUser.sendMail(signUpDTO.getEmail(), "Online Auction: Account Verification", mailContent.toString());
    }
}
