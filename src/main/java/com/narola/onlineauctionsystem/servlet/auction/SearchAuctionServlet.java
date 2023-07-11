package com.narola.onlineauctionsystem.servlet.auction;

import com.narola.onlineauctionsystem.dao.AuctionDao;
import com.narola.onlineauctionsystem.dto.AuctionDTO;
import com.narola.onlineauctionsystem.model.Auction;
import com.narola.onlineauctionsystem.service.AuctionService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class SearchAuctionServlet extends HttpServlet {
    private AuctionService auctionService = new AuctionService();
    private AuctionDTO auctionDTO = new AuctionDTO();
    private AuctionDao auctionDao = new AuctionDao();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String startDateStr = request.getParameter("startDate");
        String endDateStr = request.getParameter("endDate");
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date startDate = null;
            Date endDate = null;
            if (startDateStr != null && !startDateStr.isEmpty()) {
                startDate = dateFormat.parse(startDateStr);
            }
            if (endDateStr != null && !endDateStr.isEmpty()) {
                endDate = dateFormat.parse(endDateStr);
            }
//            List<Auction> auctions = auctionDao.searchAuctionByDate(startDate, endDate);
//            request.setAttribute("auctionList", auctions);
            request.getRequestDispatcher("auctionSearch.jsp").forward(request, response);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
