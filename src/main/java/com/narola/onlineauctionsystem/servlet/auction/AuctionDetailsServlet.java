package com.narola.onlineauctionsystem.servlet.auction;

import com.narola.onlineauctionsystem.dao.AuctionDao;
import com.narola.onlineauctionsystem.exception.DaoException;
import com.narola.onlineauctionsystem.model.Auction;
import com.narola.onlineauctionsystem.model.Bid;
import com.narola.onlineauctionsystem.model.User;
import com.narola.onlineauctionsystem.service.BidService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

public class AuctionDetailsServlet extends HttpServlet {
    private AuctionDao auctionDao = new AuctionDao();
    private BidService bidService = new BidService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String auctionId = req.getParameter("id");
        Auction auction = auctionDao.getAuctionById(auctionId);
        req.setAttribute("auction", auction);
//        req.getRequestDispatcher("auctionDetails.jsp").forward(req, resp);
//        HttpSession session = req.getSession();
//        User user = (User) session.getAttribute("user");
//        int userId = user.getUserId();
//
//        // Check if the user has already placed a bid
//        boolean hasPlacedBid = bidService.hasUserPlacedBid(userId);
//
//        // Retrieve the bid amount if the user has placed a bid
//        double bidAmount = 0;
//        if (hasPlacedBid) {
//            Bid userBid = bidService.getBidByBidderId(userId);
//            bidAmount = userBid.getAmount();
//        }
//
//        // Set the request attributes for JSP rendering
//        req.setAttribute("hasPlacedBid", hasPlacedBid);
//        req.setAttribute("bidAmount", bidAmount);
        HttpSession session = req.getSession();
        User loginUser = (User) session.getAttribute("user");
        Bid existingBid = bidService.getBidderAndAuction(auctionId, loginUser.getUserId());
        req.setAttribute("existingBid", existingBid);

        boolean showBidButton = (existingBid == null);
        req.setAttribute("showBidButton", showBidButton);
        req.getRequestDispatcher("auctionDetails.jsp").forward(req, resp);
    }

//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String auctionId = req.getParameter("id");
//        String bidAmountStr = req.getParameter("amount");
//
//        double bidAmount;
//        try {
//            bidAmount = Double.parseDouble(bidAmountStr);
//        } catch (NumberFormatException e) {
//            req.setAttribute("errorMessage", "Invalid bid amount. Please enter a numeric value.");
//            forwardToAuctionDetails(req, resp, auctionId);
//            return;
//        }
//
//        Auction auction = auctionDao.getAuctionById(auctionId);
//        req.setAttribute("auction", auction);
//        HttpSession session = req.getSession();
//        User bidder = (User) session.getAttribute("user");
//        int bidderId = bidder.getUserId();
//
//
//        if (auction != null && bidAmount <= auction.getMinimumBidPrice()) {
//            String errorMessage = "Bid amount must be higher than the minimum bid price.";
//            req.setAttribute("errorMessage", errorMessage);
//            forwardToAuctionDetails(req, resp, auctionId);
//            return;
//        }
//
//        BidDTO bidDTO = new BidDTO();
//        bidDTO.setAuctionId(auctionId);
//        bidDTO.setBidderId(bidderId);
//        bidDTO.setAmount(bidAmountStr);
//        try {
//            bidService.createBid(bidDTO);
//            resp.sendRedirect(req.getContextPath() + "/bidderDashboard");
//        } catch (DaoException e) {
//            e.printStackTrace();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private void forwardToAuctionDetails(HttpServletRequest req, HttpServletResponse resp, String auctionId) throws ServletException, IOException {
//        Auction auction = auctionDao.getAuctionById(auctionId);
//        req.setAttribute("auction", auction);
//        req.getRequestDispatcher("auctionDetails.jsp").forward(req, resp);
//    }


//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String auctionId = req.getParameter("id");
//        String bidAmountStr = req.getParameter("amount");
//        String action = req.getParameter("action");
//
//        double bidAmount;
//        try {
//            bidAmount = Double.parseDouble(bidAmountStr);
//        } catch (NumberFormatException e) {
//            req.setAttribute("errorMessage", "Invalid bid amount. Please enter a numeric value.");
//            forwardToAuctionDetails(req, resp, auctionId);
//            return;
//        }
//
//        HttpSession session = req.getSession();
//        User bidder = (User) session.getAttribute("user");
//        int bidderId = bidder.getUserId();
//
//        if ("auctionDetails".equals(action)) {
//            try {
//                boolean hasPlacedBid = bidService.hasBidderPlacedBid(auctionId, bidderId);
//                if (hasPlacedBid) {
//                    req.setAttribute("errorMess", "You have already placed a bid for this auction.");
//                } else {
//                    Auction auction = auctionDao.getAuctionById(auctionId);
//                    if (auction != null && bidAmount <= auction.getMinimumBidPrice()) {
//                        req.setAttribute("error", "Bid amount must be higher than the minimum bid price.");
//                    } else {
//                        Bid bid = bidService.getBidderAndAuction(auctionId, bidderId);
//                        if (bid != null) {
//                            req.setAttribute("errorMess", "You have already placed a bid for this auction.");
//                        } else {
//                            BidDTO bidDTO = new BidDTO();
//                            bidDTO.setAuctionId(auctionId);
//                            bidDTO.setBidderId(bidderId);
//                            bidDTO.setAmount(bidAmountStr);
//                            bidService.createBid(bidDTO);
//                            resp.sendRedirect(req.getContextPath() + "/bidderDashboard");
//                            return;
//                        }
//                    }
//                }
//            } catch (SQLException | DaoException e) {
//                e.printStackTrace();
//                // Handle the exception and show an appropriate error message
//                req.setAttribute("errorMessage", "An error occurred while placing the bid.");
//            }
//        }
//
//        Auction auction = auctionDao.getAuctionById(auctionId);
//        req.setAttribute("auction", auction);
//
//        forwardToAuctionDetails(req, resp, auctionId);
//    }
//
//    private void forwardToAuctionDetails(HttpServletRequest req, HttpServletResponse resp, String auctionId) throws ServletException, IOException {
//        Auction auction = auctionDao.getAuctionById(auctionId);
//        req.setAttribute("auction", auction);
//        req.getRequestDispatcher("auctionDetails.jsp").forward(req, resp);
//    }


    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String auctionId = req.getParameter("id");
        Auction auction = auctionDao.getAuctionById(auctionId);
        req.setAttribute("auction", auction);

        HttpSession session = req.getSession();
        User loginUser = (User) session.getAttribute("user");
        Bid existingBid = bidService.getBidderAndAuction(auctionId, loginUser.getUserId());

        if (existingBid != null) {
            req.setAttribute("errorMessage", "You have already placed a bid for this auction.");
            req.setAttribute("existingBid", existingBid);
            req.getRequestDispatcher("auctionDetails.jsp").forward(req, resp);
        } else {
            String bidAmountStr = req.getParameter("amount");
            double bidAmount;
            try {
                bidAmount = Double.parseDouble(bidAmountStr);
            } catch (NumberFormatException e) {
                req.setAttribute("errorMessage", "Please enter bid amount");
                req.getRequestDispatcher("auctionDetails.jsp").forward(req, resp);
                return;
            }

            if (bidAmount <= auction.getMinimumBidPrice()) {
                req.setAttribute("errorMessage", "Bid amount should be greater than or equal to the minimum bid price.");
                boolean showBidButton = (existingBid == null);
                req.setAttribute("showBidButton", showBidButton);
                req.getRequestDispatcher("auctionDetails.jsp").forward(req, resp);
            } else {
                Bid newBid = new Bid();
                newBid.setAmount(bidAmount);
                newBid.setAuctionId(auctionId);
                newBid.setUserId(loginUser.getUserId());

                try {
                    bidService.createBid(newBid);
                    resp.sendRedirect(req.getContextPath() + "/bidderDashboard");
                    req.setAttribute("existingBid", newBid);
                    req.setAttribute("showBidButton", true);
                } catch (DaoException | SQLException e) {
                    req.setAttribute("errorMessage", "Failed to place the bid. Please try again later.");
                }
            }
        }
    }
}

