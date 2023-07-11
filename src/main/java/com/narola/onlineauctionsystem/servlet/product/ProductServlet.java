package com.narola.onlineauctionsystem.servlet.product;

import com.narola.onlineauctionsystem.dao.ProductDao;
import com.narola.onlineauctionsystem.dto.ProductDTO;
import com.narola.onlineauctionsystem.model.Product;
import com.narola.onlineauctionsystem.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class ProductServlet extends HttpServlet {
    private ProductDTO productDTO = new ProductDTO();
    private ProductDao productDao = new ProductDao();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        int sellerId = user.getUserId();
        productDTO.setSellerId(sellerId);
        String sortOption = request.getParameter("sort");
        List<Product> productList = null;
        if (sortOption != null && (sortOption.equals("asc") || sortOption.equals("desc"))) {
            if (sortOption.equals("asc")) {
                productList = productDao.getAllProducts(sellerId);
            } else {
                productList = productDao.getAllProductsDesc(sellerId);
            }
        } else {
            productList = productDao.showProducts(sellerId);
        }

        request.setAttribute("productList", productList);
        request.setAttribute("selectedOption", sortOption);
        request.getRequestDispatcher("viewProducts.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       doGet(req, resp);
    }
}
