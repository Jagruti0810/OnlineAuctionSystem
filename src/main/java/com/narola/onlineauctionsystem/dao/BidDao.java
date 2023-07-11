package com.narola.onlineauctionsystem.dao;

import com.narola.onlineauctionsystem.dto.BidDTO;
import com.narola.onlineauctionsystem.exception.DaoException;
import com.narola.onlineauctionsystem.model.Auction;
import com.narola.onlineauctionsystem.model.Bid;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BidDao {
    public void addBid(Bid bid) throws SQLException, DaoException {
        String sql = "INSERT INTO bid (bidder_id, auction_id, bid_price) VALUES (?, ?, ?)";
        PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement(sql);
        try {
            preparedStatement.setInt(1, bid.getUserId());
            preparedStatement.setInt(2, Integer.parseInt(bid.getAuctionId()));
            preparedStatement.setDouble(3, bid.getAmount());
//            preparedStatement.setBoolean(4, bidDTO.isHasPlacedBid());
            preparedStatement.executeUpdate();
        } catch (SQLException se) {
            throw new DaoException("oops something went wrong", se);
        } catch (Exception e) {
            throw new DaoException("oops something went wrong", e);
        }
    }

//    public void addBid(Bid bid) throws SQLException, DaoException {
//        String sql = "INSERT INTO bid (bidder_id, auction_id, bid_price) VALUES (?, ?, ?)";
//        PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement(sql);
//        try {
//            preparedStatement.setInt(1, bid.getUserId());
//            preparedStatement.setString(2, bid.getAuctionId());
//            preparedStatement.setDouble(3, bid.getAmount());
////            preparedStatement.setBoolean(4, bidDTO.isHasPlacedBid());
//            preparedStatement.executeUpdate();
//        } catch (SQLException se) {
//            throw new DaoException("oops something went wrong", se);
//        } catch (Exception e) {
//            throw new DaoException("oops something went wrong", e);
//        }
//    }

//    public Bid getHighestBid(String auctionId) throws SQLException {
//        String sql = "SELECT * FROM bid WHERE auction_id = ? ORDER BY bid_price DESC LIMIT 1";
//        PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement(sql);
//        try {
//            preparedStatement.setString(1, auctionId);
//            try (ResultSet resultSet = preparedStatement.executeQuery()) {
//                if (resultSet.next()) {
//                    int bidId = resultSet.getInt("bid_id");
//                    int userId = resultSet.getInt("user_id");
//                    double amount = resultSet.getDouble("bid_price");
//                    return new Bid(bidId, userId, auctionId, amount);
//                }
//            }
//        } catch (SQLException se) {
//            se.printStackTrace();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        return null;
//    }

    public void updateBid(Bid bid) throws DaoException, SQLException {
        String sql = "UPDATE bid SET bid_price = ? WHERE bid_id = ?";
        PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement(sql);
        try {
            preparedStatement.setDouble(1, bid.getAmount());
            preparedStatement.setInt(2, bid.getBidId());
            preparedStatement.executeUpdate();
        } catch (SQLException se) {
            throw new DaoException("oops something went wrong", se);
        } catch (Exception e) {
            throw new DaoException("oops something went wrong", e);
        }
    }

    public void deleteBid(int bidId) throws SQLException, DaoException {
        String sql = "DELETE FROM bid WHERE bid_id = ?";
        PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement(sql);
        try {
            preparedStatement.setInt(1, bidId);
            preparedStatement.executeUpdate();
        } catch (SQLException se) {
            throw new DaoException("oops something went wrong", se);
        } catch (Exception e) {
            throw new DaoException("oops something went wrong", e);
        }
    }

    public List<Bid> getBidsByAuctionAndBidder(String auctionId, int bidderId) throws SQLException {
        List<Bid> bids = new ArrayList<>();
        try {
            String sql = "SELECT * FROM bids WHERE auctionId = ? AND bidderId = ?";
            PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement(sql);
            preparedStatement.setString(1, auctionId);
            preparedStatement.setInt(2, bidderId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int bidId = resultSet.getInt("bid_id");
                double amount = resultSet.getDouble("bid_price");
                Bid bid = new Bid(bidId, bidderId, auctionId, amount);
                bids.add(bid);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bids;
    }

    public boolean hasBidderPlacedBid(String auctionId, int bidderId) {
        try {
            String query = "SELECT COUNT(*) FROM bid WHERE auction_id = ? AND bidder_id = ?";
            PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement(query);
            preparedStatement.setString(1, auctionId);
            preparedStatement.setInt(2, bidderId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                return count > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean checkUserHasBid(Boolean isVerified, int userId) throws DaoException {
        try {
            String query = "UPDATE bid SET has_placed_bid = ? where bidder_id=?";
            PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement(query);
            preparedStatement.setBoolean(1, isVerified);
            preparedStatement.setInt(2, userId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("oops something went wrong", e);
        } catch (Exception e) {
            throw new DaoException("oops something went wrong", e);
        }
        return true;
    }

    public Bid getBidById(int userId) {
        Bid bid = null;
        try {
            String query = "SELECT * FROM bid WHERE bidder_id = ?";
            PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement(query);
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                bid = new Bid();
                bid.setBidId(resultSet.getInt(1));
                bid.setUserId(resultSet.getInt(2));
                bid.setAuctionId(String.valueOf(resultSet.getInt(3)));
                bid.setAmount(resultSet.getDouble(4));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bid;
    }

    public boolean hasUserPlacedBid(int userId) {
        try {
            String sql = "SELECT COUNT(*) FROM bid WHERE bidder_id = ?";
            PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement(sql);
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                return count > 0;
            }
        } catch (SQLException e) {
            // Handle any exceptions
            e.printStackTrace();
        }
        return false;
    }

    public Bid getBidByAuctionAndBidder(String auctionId, int bidderId) {
        // Implement the logic to query the database and retrieve the existing bid by auction and bidder
        Bid bid = null;
        try {
            String sql = "SELECT * FROM bid WHERE auction_id = ? AND bidder_id = ?";
            PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement(sql);
            preparedStatement.setString(1, auctionId);
            preparedStatement.setInt(2, bidderId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                bid = new Bid();
                bid.setBidId(resultSet.getInt("bid_id"));
                bid.setAmount(resultSet.getDouble("bid_price"));
                bid.setAuctionId(resultSet.getString("auction_id"));
                bid.setUserId(resultSet.getInt("bidder_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bid;
    }

    public List<Bid> getBid(String auctionId) throws DaoException {
        List<Bid> bids = new ArrayList<>();
        try {
            String query = "SELECT * from bid where auction_id = ?";
            PreparedStatement stmt = DbConnection.getInstance().getConnection().prepareStatement(query);
            stmt.setString(1, auctionId);
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                Integer bidderId = resultSet.getInt("bidder_id");
                double amount = resultSet.getDouble("bid_price");
                Bid bid = new Bid(auctionId, bidderId, amount);
                bids.add(bid);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bids;
    }

    public Bid getHighestBid(String auctionId) {
       Bid bid = null;
        try {
            String sql = "SELECT * FROM bid WHERE auction_id = ? ORDER BY bid_price DESC LIMIT 1";
            PreparedStatement stmt = DbConnection.getInstance().getConnection().prepareStatement(sql);
            stmt.setString(1, auctionId);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                double amount = resultSet.getDouble("bid_price");
                Integer userId = resultSet.getInt("bidder_id");
                bid = new Bid(auctionId, amount, userId);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bid;
    }
}
