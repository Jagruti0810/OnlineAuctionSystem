package com.narola.onlineauctionsystem.servlet.product;

import com.narola.onlineauctionsystem.dto.ProductDTO;
import com.narola.onlineauctionsystem.exception.DaoException;
import com.narola.onlineauctionsystem.service.ProductService;

import javax.servlet.ServletException;
import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteProductServlet extends HttpServlet {
    private ProductService productService = new ProductService();
    private ProductDTO productDTO = new ProductDTO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//        HttpSession session = request.getSession();
//        User user = (User) session.getAttribute("user");
        int productId = Integer.parseInt(request.getParameter("id"));
//        int productId = productDTO.getProductId();
//        productDTO.setProductId(productId);
        try {
            productService.deleteProduct(productId);
            response.sendRedirect(request.getContextPath() + "/viewProduct");
//            request.getRequestDispatcher("viewProducts.jsp").forward(request, response);
        } catch (DaoException e) {
            response.sendRedirect("error.jsp");
        }
    }
}



