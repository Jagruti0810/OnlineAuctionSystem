package com.narola.onlineauctionsystem.servlet.seller;

import com.narola.onlineauctionsystem.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class SellerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        User user1 = (User) session.getAttribute("Role");
        req.setAttribute("username", user.getUsername());
        req.setAttribute("roleType", user.getRoleType());
        req.setAttribute("roleId", user.getRoleId());
        req.getRequestDispatcher("seller.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String managementType = request.getParameter("managementType");
//        if (managementType != null && managementType.equals("product")) {
//            response.sendRedirect("productManagement");
//        } else if (managementType != null && managementType.equals("auction")) {
//            response.sendRedirect("auctionManagement");
//        } else {
//            response.getWriter().println("Invalid management type");
//        }
        response.sendRedirect("seller.jsp");
    }
}


