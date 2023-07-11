package com.narola.onlineauctionsystem.servlet.auction;

import com.narola.onlineauctionsystem.dao.AuctionDao;
import com.narola.onlineauctionsystem.dto.AuctionDTO;
import com.narola.onlineauctionsystem.exception.DaoException;
import com.narola.onlineauctionsystem.model.Auction;
import com.narola.onlineauctionsystem.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class AuctionServlet extends HttpServlet {
    private AuctionDTO auctionDTO = new AuctionDTO();
    private AuctionDao auctionDao = new AuctionDao();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        int sellerId = user.getUserId();
        auctionDTO.setSellerId(sellerId);
        String sortOption = request.getParameter("sort");
        List<Auction> auctionList = null;
        if (sortOption.equals("asc")) {
            auctionList = auctionDao.getAllAuctionsAsc(sellerId);
        } else if (sortOption.equals("desc")) {
            auctionList = auctionDao.getAllAuctionsDesc(sellerId);
        } else if (sortOption.equals("Low")) {
            auctionList = auctionDao.getAllAuctionsByMinimumBidPriceLowToHigh(sellerId);
        } else if (sortOption.equals("High")) {
            auctionList = auctionDao.getAllAuctionsByMinimumBidPriceHighToLow(sellerId);
        } else {
            try {
                auctionList = auctionDao.getAuction(sellerId);
            } catch (DaoException e) {
                e.printStackTrace();
            }
        }
        request.setAttribute("auctionList", auctionList);
        request.setAttribute("selectedOption", sortOption);
        request.getRequestDispatcher("viewAuction.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
