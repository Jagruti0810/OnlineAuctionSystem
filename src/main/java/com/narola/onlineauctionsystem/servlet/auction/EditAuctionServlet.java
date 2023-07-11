package com.narola.onlineauctionsystem.servlet.auction;

import com.narola.onlineauctionsystem.dao.ProductDao;
import com.narola.onlineauctionsystem.dto.AuctionDTO;
import com.narola.onlineauctionsystem.exception.DaoException;
import com.narola.onlineauctionsystem.exception.Error;
import com.narola.onlineauctionsystem.model.Auction;
import com.narola.onlineauctionsystem.model.Product;
import com.narola.onlineauctionsystem.model.User;
import com.narola.onlineauctionsystem.service.AuctionService;
import com.narola.onlineauctionsystem.validation.AuctionValidation;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class EditAuctionServlet extends HttpServlet {
    private AuctionService auctionService = new AuctionService();
    private AuctionDTO auctionDTO = new AuctionDTO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String auctionId = req.getParameter("id");
        Auction auction = auctionService.getAuctionById(auctionId);
        if (auction != null) {
            req.setAttribute("auction", auction);
            req.setAttribute("productId", auction.getProductId());
            HttpSession session = req.getSession();
            User user = (User) session.getAttribute("user");
            int sellerId = user.getUserId();
            auctionDTO.setSellerId(sellerId);
            fillAuctionMasterData(req);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("editAuction.jsp");
            requestDispatcher.forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        String auctionId = req.getParameter("auctionId");
        auctionDTO.setAuctionId(auctionId);
        auctionDTO.setTitle(req.getParameter("auction-title"));
        auctionDTO.setProductId(req.getParameter("productId"));
        auctionDTO.setMinimumBidPrice(req.getParameter("minimum-bid-price"));
        auctionDTO.setStartDate(req.getParameter("start-date"));
        auctionDTO.setEndDate(req.getParameter("end-date"));
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        int sellerId = user.getUserId();
        auctionDTO.setSellerId(sellerId);
        List<Error> errorList = AuctionValidation.validateAuctionCredentials(auctionDTO);
        if (!errorList.isEmpty()) {
            fillAuctionMasterData(req);
            req.setAttribute("errorList", errorList);
            req.setAttribute("auctionDTO", auctionDTO);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("editAuction.jsp");
            requestDispatcher.forward(req, resp);
            return;
        }

        try {
            auctionService.editAuction(auctionDTO);
            resp.sendRedirect(req.getContextPath() + "/viewAuction");
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }

    private void fillAuctionMasterData(HttpServletRequest request) {
        ProductDao productDao = new ProductDao();
        List<Product> products = productDao.showProducts(auctionDTO.getSellerId());
        request.setAttribute("products", products);
    }
}
