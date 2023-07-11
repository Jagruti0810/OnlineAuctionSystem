package com.narola.onlineauctionsystem.service;

import com.narola.onlineauctionsystem.dao.AuctionDao;
import com.narola.onlineauctionsystem.dto.AuctionDTO;
import com.narola.onlineauctionsystem.exception.DaoException;
import com.narola.onlineauctionsystem.model.Auction;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AuctionService {
    private static AuctionDao auctionDao = new AuctionDao();

    public static void createAuction(int userId, AuctionDTO auctionDTO) throws DaoException {
        auctionDTO.setStatus("pending");
        auctionDao.addAuctionDetails(userId, auctionDTO);
    }

    public static void editAuction(AuctionDTO auctionDTO) throws DaoException {
        auctionDao.editAuctionDetails(auctionDTO);
    }

    public static void deleteAuction(int userId) throws DaoException {
        auctionDao.removeAuction(userId);
    }

    public Auction getAuctionById(String auctionId) {
        return auctionDao.getAuctionById(auctionId);
    }

    public String getAuctionStatus(String auctionId) {
        // Implement the retrieval of auction status
        // Assuming auctionDao is an instance of the AuctionDao class

        Auction auction = auctionDao.getAuctionById(auctionId);

        // Compare the current date with the auction's start and end dates
        LocalDate currentDate = LocalDate.now();
        Date startDate = auction.getStartDate();
        Date endDate = auction.getEndDate();

        LocalDate localStartDate = startDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate localEndDate = endDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        if (currentDate.isBefore(localStartDate)) {
            return "Pending"; // Auction has not started yet
        } else if (currentDate.isAfter(localEndDate)) {
            return "Closed"; // Auction has ended
        } else {
            return "Ongoing"; // Auction is currently ongoing
        }
    }


    public static void updateAuctionStatus(Auction auction) throws DaoException {
        auctionDao.editAuctionStatus(auction);
    }

    // Other methods
}

