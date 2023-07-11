package com.narola.onlineauctionsystem.servlet.bidder;

import com.narola.onlineauctionsystem.dao.AuctionDao;
import com.narola.onlineauctionsystem.model.Auction;
import com.narola.onlineauctionsystem.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;


public class BidderServlet extends HttpServlet {
private AuctionDao auctionDao = new AuctionDao();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        req.setAttribute("username", user.getUsername());
        List<Auction> auctions = auctionDao.auctionDetails();
        req.setAttribute("auctionList", auctions);
        req.getRequestDispatcher("bidder.jsp").forward(req, resp);
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//        try {
//            Date startDate = null;
//            Date endDate = null;
//            if (startDateStr != null && !startDateStr.isEmpty()) {
//                startDate = dateFormat.parse(startDateStr);
//            }
//            if (endDateStr != null && !endDateStr.isEmpty()) {
//                endDate = dateFormat.parse(endDateStr);
//            }
//            List<Auction> auctions = auctionDao.searchAuctionByDate(startDate, endDate);
//            request.setAttribute("auctionList", auctions);
//        req.getRequestDispatcher("bidder.jsp").forward(req, resp);
    }
}
