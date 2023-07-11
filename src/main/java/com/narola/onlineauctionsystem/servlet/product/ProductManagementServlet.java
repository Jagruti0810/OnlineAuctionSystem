package com.narola.onlineauctionsystem.servlet.product;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ProductManagementServlet extends HttpServlet {
    //    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String action = request.getParameter("productManagement");
//
//        if (action != null) {
//            switch (action) {
//                case "addProduct":
//                    response.sendRedirect("addProduct");
//                    break;
//                case "viewProducts":
//                    response.sendRedirect("viewProduct");
//                    break;
//                case "deleteProduct":
//                    response.sendRedirect("deleteProduct.jsp");
//                    break;
//                default:
//                    response.sendRedirect("error.jsp");
//                    break;
//            }
//        }
//    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/productManagement.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String action = req.getParameter("productManagement");
//
//        if (action != null) {
//            switch (action) {
//                case "addProduct":
//                    resp.sendRedirect("addProduct");
//                    break;
//                case "viewProducts":
//                    resp.sendRedirect("viewProduct");
//                    break;
//                case "deleteProduct":
//                    resp.sendRedirect("deleteProduct.jsp");
//                    break;
//                default:
//                    resp.sendRedirect("error.jsp");
//                    break;
//            }
//        }
        resp.sendRedirect("productManagement.jsp");
    }
}


