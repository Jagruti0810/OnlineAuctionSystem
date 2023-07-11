package com.narola.onlineauctionsystem.servlet.bid;

import com.narola.onlineauctionsystem.dao.BidDao;
import com.narola.onlineauctionsystem.exception.DaoException;
import com.narola.onlineauctionsystem.model.Bid;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ViewBidServlet extends HttpServlet {
    private BidDao bidDao = new BidDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String auctionId = req.getParameter("id");
        List<Bid> bidList = null;
        Bid highestBid = null;

        try {
            bidList = bidDao.getBid(auctionId);
            highestBid = bidDao.getHighestBid(auctionId);
        } catch (DaoException e) {
            e.printStackTrace();
        }

        req.setAttribute("bidList", bidList);
        req.setAttribute("highestBid", highestBid);
        req.getRequestDispatcher("viewBid.jsp").forward(req, resp);
    }

}
