package com.narola.onlineauctionsystem.servlet.product;

import com.narola.onlineauctionsystem.dao.ProductDao;
import com.narola.onlineauctionsystem.dto.ProductDTO;
import com.narola.onlineauctionsystem.model.Product;
import com.narola.onlineauctionsystem.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class ViewProductServlet extends HttpServlet {
    private ProductDTO productDTO = new ProductDTO();
    private ProductDao productDao = new ProductDao();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        int sellerId = user.getUserId();
        productDTO.setSellerId(sellerId);
        List<Product> products = productDao.showProducts(sellerId);
        req.setAttribute("productList", products);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("viewProducts.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        int sellerId = user.getUserId();
        productDTO.setSellerId(sellerId);
        List<Product> products = productDao.showProducts(sellerId);
        req.setAttribute("productList", products);
    }
//    private void fillProductMasterData(HttpServletRequest request) {
//        ProductDao productDao = new ProductDao();
//        List<Product> products = productDao.showProducts();
//        request.setAttribute("productList", products);
//    }
}
