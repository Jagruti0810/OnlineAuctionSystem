package com.narola.onlineauctionsystem.servlet.bid;

import com.narola.onlineauctionsystem.dao.AuctionDao;
import com.narola.onlineauctionsystem.dao.BidDao;
import com.narola.onlineauctionsystem.dto.BidDTO;
import com.narola.onlineauctionsystem.model.Auction;
import com.narola.onlineauctionsystem.model.User;
import com.narola.onlineauctionsystem.service.BidService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class BidAmountServlet extends HttpServlet {
    private AuctionDao auctionDao = new AuctionDao();
    private BidService bidService = new BidService();
    private BidDao bidDao = new BidDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("auctionDetails.jsp").forward(req, resp);
    }

//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String bidAmountStr = request.getParameter("amount");
//        String auctionId = request.getParameter("auctionId"); // Corrected parameter name
//        Auction auction = auctionDao.getAuctionById(auctionId); // Removed redundant call to get auction
//
//        double bidAmount;
//        try {
//            if (bidAmountStr == null || bidAmountStr.isEmpty()) {
//                request.setAttribute("auction", auction);
//                throw new NumberFormatException();
//            }
//            bidAmount = Double.parseDouble(bidAmountStr);
//        } catch (NumberFormatException e) {
//            request.setAttribute("errorMessage", "Invalid bid amount. Please enter a numeric value.");
//            request.getRequestDispatcher("auctionDetails.jsp").forward(request, response);
//            return;
//        }
//
//        HttpSession session = request.getSession();
//        User bidder = (User) session.getAttribute("user");
//        int bidderId = bidder.getUserId();
//
//        // Check if the bidder has already placed a bid
//        boolean hasPlacedBid = false;
//        try {
//            hasPlacedBid = bidService.hasBidderPlacedBid(auctionId, bidderId);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        if (hasPlacedBid) {
//            String errorMessage = "You have already placed a bid for this auction.";
//            request.setAttribute("errorMessage", errorMessage);
//            request.getRequestDispatcher("auctionDetails.jsp").forward(request, response);
//            return;
//        }
//
//        BidDTO bidDTO = new BidDTO();
//        bidDTO.setAmount(bidAmountStr);
//        bidDTO.setAuctionId(auctionId);
//        bidDTO.setBidderId(bidderId);
//
//        // Process the bid
//        // ...
//
//       // Redirect to the bidder page after successful bid
//    }
//}


//    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String bidAmountStr = request.getParameter("amount");
//        String auctionId = request.getParameter("auctionId");
//        double bidAmount;
//        try {
//            bidAmount = Double.parseDouble(bidAmountStr);
//        } catch (NumberFormatException e) {
//            request.setAttribute("errorMessage", "Invalid bid amount. Please enter a numeric value.");
//            request.getRequestDispatcher("bidOnAuction.jsp").forward(request, response);
//            return;
//        }
//        Auction auction = auctionDao.getAuctionById(auctionId);
//        if (auction == null) {
//            request.setAttribute("errorMessage", "Invalid auction.");
//            request.getRequestDispatcher("bidOnAuction.jsp").forward(request, response);
//            return;
//        }
//        if (bidAmount <= auction.getMinimumBidPrice()) {
//            request.setAttribute("errorMessage", "Bid amount must be higher than the minimum bid price.");
//            request.getRequestDispatcher("bidOnAuction.jsp").forward(request, response);
//            return;
//        }
//        BidDTO bidDTO = new BidDTO();
//        bidDTO.setAmount(bidAmountStr);
//        bidDTO.setAuctionId(auctionId);
//        HttpSession session = request.getSession();
//        User user = (User) session.getAttribute("user");
//        try {
//            bidService.createBid(bidDTO);
//            request.getRequestDispatcher("bidder.jsp").forward(request, response);
//        } catch (DaoException | SQLException e) {
//            e.printStackTrace();
//            // Handle the exception appropriately
//        }
//    }


    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String auctionId = req.getParameter("id");
        String bidAmountStr = req.getParameter("amount");
        double bidAmount;

        try {
            if (bidAmountStr == null || bidAmountStr.isEmpty()) {
                throw new NumberFormatException();
            }
            bidAmount = Double.parseDouble(bidAmountStr);
        } catch (NumberFormatException e) {
            req.setAttribute("errorMessage", "Invalid bid amount. Please enter a numeric value.");
            req.getRequestDispatcher("auctionDetails?id=" + auctionId).forward(req, resp);
            return;
        }

        Auction auction = auctionDao.getAuctionById(auctionId);
        req.setAttribute("auction", auction);
        HttpSession session = req.getSession();
        User bidder = (User) session.getAttribute("user");
        int bidderId = bidder.getUserId();

//        try {
//            if (bidService.hasBidderPlacedBid(auctionId, bidderId)) {
//                // Bidder has already placed a bid, show the bid from the database
//                boolean existingBid = bidService.hasBidderPlacedBid(auctionId, bidderId);
//                req.setAttribute("existingBid", existingBid);
//                forwardToAuctionDetails(req, resp, auctionId);
//                return;
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

        if (bidAmount <= auction.getMinimumBidPrice()) {
            req.setAttribute("errorMessage", "Bid amount must be higher than the minimum bid price.");
            req.getRequestDispatcher("bidOnAuction.jsp").forward(req, resp);
            return;
        }

        // Create a BidDTO object and set the bid details
        BidDTO bidDTO = new BidDTO();
        bidDTO.setAmount(bidAmountStr);
        bidDTO.setAuctionId(auctionId);

        // Process the bid by calling the bidService
//        try {
//            bidService.createBid(bidDTO);
//            resp.sendRedirect(req.getContextPath() + "/bidderDashboard");
//        } catch (DaoException e) {
//            req.setAttribute("errorMessage", "Failed to create the bid. Please try again.");
//            req.getRequestDispatcher("auctionDetails?id=" + auctionId).forward(req, resp);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//    private void forwardToAuctionDetails(HttpServletRequest req, HttpServletResponse resp, String auctionId) throws ServletException, IOException {
//        Auction auction = auctionDao.getAuctionById(auctionId);
//        req.setAttribute("auction", auction);
//        req.getRequestDispatcher("auctionDetails.jsp").forward(req, resp);
//    }
    }
}




