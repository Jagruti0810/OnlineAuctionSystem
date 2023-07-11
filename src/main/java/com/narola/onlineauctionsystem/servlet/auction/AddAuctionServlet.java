package com.narola.onlineauctionsystem.servlet.auction;

import com.narola.onlineauctionsystem.dao.ProductDao;
import com.narola.onlineauctionsystem.dto.AuctionDTO;
import com.narola.onlineauctionsystem.exception.DaoException;
import com.narola.onlineauctionsystem.exception.Error;
import com.narola.onlineauctionsystem.model.Product;
import com.narola.onlineauctionsystem.model.User;
import com.narola.onlineauctionsystem.service.AuctionService;
import com.narola.onlineauctionsystem.validation.AuctionValidation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class AddAuctionServlet extends HttpServlet {
    private AuctionDTO auctionDTO = new AuctionDTO();
    private AuctionService auctionService = new AuctionService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        int sellerId = user.getUserId();
        auctionDTO.setSellerId(sellerId);
        fillAuctionMasterData(req);
        req.getRequestDispatcher("addAuction.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        auctionDTO.setTitle(req.getParameter("auction-title"));
        auctionDTO.setProductId(req.getParameter("productId"));
        auctionDTO.setMinimumBidPrice(req.getParameter("minimum-bid-price"));
        auctionDTO.setStartDate(req.getParameter("start-date"));
        auctionDTO.setEndDate(req.getParameter("end-date"));
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        int sellerId = user.getUserId();
        auctionDTO.setSellerId(sellerId);
        fillAuctionMasterData(req);
        List<Error> errorList = AuctionValidation.validateAuctionCredentials(auctionDTO);
        if (!errorList.isEmpty()) {
            fillAuctionMasterData(req);
            req.setAttribute("errorList", errorList);
            req.getRequestDispatcher("addAuction.jsp").forward(req, resp);
            return;
        }
        try {
            auctionService.createAuction(sellerId, auctionDTO);
            req.getRequestDispatcher("seller.jsp").forward(req, resp);
        } catch (DaoException e) {
            resp.sendRedirect("error.jsp");
        }
    }

    private void fillAuctionMasterData(HttpServletRequest request) {
        ProductDao productDao = new ProductDao();
        List<Product> products = productDao.showProductsByCredentials(auctionDTO.getSellerId());
        request.setAttribute("products", products);
    }
}
