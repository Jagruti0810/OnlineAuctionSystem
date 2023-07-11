package com.narola.onlineauctionsystem.servlet.auction;

import com.narola.onlineauctionsystem.dao.AuctionDao;
import com.narola.onlineauctionsystem.dto.AuctionDTO;
import com.narola.onlineauctionsystem.exception.DaoException;
import com.narola.onlineauctionsystem.model.Auction;
import com.narola.onlineauctionsystem.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class ViewAuctionServlet extends HttpServlet {
    private AuctionDTO auctionDTO = new AuctionDTO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        int sellerId = user.getUserId();
        auctionDTO.setSellerId(sellerId);
        AuctionDao auctionDao = new AuctionDao();
        try {
            List<Auction> auctions = auctionDao.getAuction(sellerId);
            req.setAttribute("auctionList", auctions);
        } catch (DaoException e) {
            e.printStackTrace();
        }
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("viewAuction.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        int sellerId = user.getUserId();
        auctionDTO.setSellerId(sellerId);
        try {
            AuctionDao auctionDao = new AuctionDao();
            List<Auction> auctions = auctionDao.getAuction(sellerId);
            req.setAttribute("auctionList", auctions);
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }
}
