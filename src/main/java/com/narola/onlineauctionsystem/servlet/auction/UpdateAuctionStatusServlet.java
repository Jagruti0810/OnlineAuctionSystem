package com.narola.onlineauctionsystem.servlet.auction;

import com.narola.onlineauctionsystem.dto.AuctionDTO;
import com.narola.onlineauctionsystem.exception.DaoException;
import com.narola.onlineauctionsystem.model.Auction;
import com.narola.onlineauctionsystem.model.User;
import com.narola.onlineauctionsystem.service.AuctionService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;


public class UpdateAuctionStatusServlet extends HttpServlet {
    private AuctionDTO auctionDTO = new AuctionDTO();
    private AuctionService auctionService = new AuctionService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String auctionId = req.getParameter("id");
        Auction auction = auctionService.getAuctionById(auctionId);
        if (auction != null) {
            req.setAttribute("auction", auction);
            HttpSession session = req.getSession();
            User user = (User) session.getAttribute("user");
            int sellerId = user.getUserId();
            auctionDTO.setSellerId(sellerId);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("editAuctionStatus.jsp");
            requestDispatcher.forward(req, resp);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String auctionId = request.getParameter("auctionId");
        String updatedStatus = request.getParameter("auction-status");
        LocalDate currentDate = LocalDate.now();

        // Retrieve and parse the start date
        String startDateStr = request.getParameter("start_date");
        LocalDate startDate = null;
        if (startDateStr != null && !startDateStr.isEmpty()) {
            startDate = LocalDate.parse(startDateStr);
        }

        // Retrieve and parse the end date
        String endDateStr = request.getParameter("end_date");
        LocalDate endDate = null;
        if (endDateStr != null && !endDateStr.isEmpty()) {
            endDate = LocalDate.parse(endDateStr);
        }

        try {
            Auction auction = auctionService.getAuctionById(auctionId);
            String status;
            if (startDate != null && endDate != null &&
                    (currentDate.isEqual(startDate) || (currentDate.isAfter(startDate) && currentDate.isBefore(endDate)) || currentDate.isEqual(endDate))) {
                status = "active";
            } else if (endDate != null && currentDate.isAfter(endDate)) {
                status = "closed";
            } else {
                status = "canceled";
            }
            auction.setStatus(status);
            auctionService.updateAuctionStatus(auction);
            response.sendRedirect(request.getContextPath() + "/sellerDashboard");
        } catch (DaoException e) {
            e.printStackTrace(); // Handle the DaoException appropriately
        }
    }
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        // Retrieve the auction ID from the request parameter
//        String auctionId = request.getParameter("auctionId");
//
//        // Retrieve the auction details from the form or database
//        Auction auction = auctionDao.getAuctionById(auctionId);
//
//        // Get the current status of the auction
//        String currentStatus = auction.getStatus();
//
//        // Check if the current status needs to be updated
//        String updatedStatus = getAuctionStatus(auctionId);
//        if (!currentStatus.equals(updatedStatus)) {
//            // Update the status of the auction
//            auction.setStatus(updatedStatus);
//
//            // Update the auction status in the database
//            try {
//                updateAuctionStatus(auction);
//            } catch (DaoException e) {
//                e.printStackTrace();
//                // Handle the exception as needed
//            }
//        }
//
//        // Redirect to the auction details page or any other appropriate page
//        response.sendRedirect("auction-details.jsp?id=" + auctionId);
//    }
//
//    private String getAuctionStatus(String auctionId) {
//        // Implement the retrieval of auction status
//        // Assuming auctionDao is an instance of the AuctionDao class
//
//        Auction auction = auctionDao.getAuctionById(auctionId);
//
//        // Compare the current date with the auction's start and end dates
//        LocalDate currentDate = LocalDate.now();
//        LocalDate startDate = auction.getStartDate().toLocalDate();
//        LocalDate endDate = auction.getEndDate().toLocalDate();
//
//        if (currentDate.isBefore(startDate)) {
//            return "Pending"; // Auction has not started yet
//        } else if (currentDate.isAfter(endDate)) {
//            return "Closed"; // Auction has ended
//        } else {
//            return "Ongoing"; // Auction is currently ongoing
//        }
//    }
//
//    private void updateAuctionStatus(Auction auction) throws DaoException {
//        auctionDao.updateAuctionStatus(auction);
//    }
}
