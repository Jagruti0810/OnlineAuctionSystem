package com.narola.onlineauctionsystem.service;

import com.narola.onlineauctionsystem.dao.BidDao;
import com.narola.onlineauctionsystem.exception.DaoException;
import com.narola.onlineauctionsystem.model.Bid;

import java.sql.SQLException;
import java.util.List;

public class BidService {
    private static BidDao bidDao = new BidDao();

    public void createBid(Bid bid) throws DaoException, SQLException {
        bidDao.addBid(bid);
    }

    public boolean hasBidderPlacedBid(String auctionId, int bidderId) throws SQLException {
        List<Bid> bids = bidDao.getBidsByAuctionAndBidder(auctionId, bidderId);
        return !bids.isEmpty();
    }

    public Bid getBidByBidderId(int bidderId) {
        return bidDao.getBidById(bidderId);
    }

    public boolean hasUserPlacedBid(int userId) {
        return bidDao.hasUserPlacedBid(userId);
    }

    public Bid getBidderAndAuction(String auctionId, int bidderId)
    {
       return bidDao.getBidByAuctionAndBidder(auctionId, bidderId);
    }
}
