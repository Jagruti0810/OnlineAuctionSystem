package com.narola.onlineauctionsystem.dao;

import com.narola.onlineauctionsystem.dto.AuctionDTO;
import com.narola.onlineauctionsystem.exception.DaoException;
import com.narola.onlineauctionsystem.model.Auction;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AuctionDao {
    public Auction addAuctionDetails(int userId, AuctionDTO auctionDTO) {
        Auction auction = null;
        try {
            String sql = "INSERT INTO auction(title, minimum_bid_price, start_date, end_date, seller_id, product_id, status)" +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement(sql);
            preparedStatement.setString(1, auctionDTO.getTitle());
            preparedStatement.setDouble(2, Double.parseDouble(auctionDTO.getMinimumBidPrice()));
            preparedStatement.setDate(3, java.sql.Date.valueOf(auctionDTO.getStartDate()));
            preparedStatement.setDate(4, java.sql.Date.valueOf(auctionDTO.getEndDate()));
            preparedStatement.setInt(5, userId);
            preparedStatement.setInt(6, Integer.parseInt(auctionDTO.getProductId()));
            preparedStatement.setString(7, auctionDTO.getStatus());
            preparedStatement.executeUpdate();
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return auction;
    }

    //showAuction(),
    public List<Auction> getAuction(int userId) throws DaoException {
        List<Auction> auctions = new ArrayList<>();
        try {
            String query = "SELECT a.auction_id, a.title, p.product_name, a.minimum_bid_price, a.start_date, a.end_date, a.status FROM auction a JOIN product p ON a.product_id = p.product_id WHERE a.seller_id = ?";
            PreparedStatement stmt = DbConnection.getInstance().getConnection().prepareStatement(query);
            stmt.setInt(1, userId);
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                Integer auctionId = resultSet.getInt("auction_id");
                String title = resultSet.getString("title");
                int minimumBidPrice = resultSet.getInt("minimum_bid_price");
                String productName = resultSet.getString("product_name");
                java.sql.Date startDate = resultSet.getDate("start_date");
                java.sql.Date endDate = resultSet.getDate("end_date");
                String status = resultSet.getString("status");
                Auction auction = new Auction(auctionId, title, minimumBidPrice, productName, startDate, endDate, status);
                auctions.add(auction);
            }
        } catch (SQLException e) {
            throw new DaoException("oops something went wrong", e);
        } catch (Exception e) {
            throw new DaoException("oops something went wrong", e);
        }
        return auctions;
    }

//    public Auction showAuctionDetails(int auctionId) throws DaoException {
//        Auction auction = null;
//        try {
//            String query1 = "select * from auction where auction_id = ?";
//            PreparedStatement stmt = DbConnection.getInstance().getConnection().prepareStatement(query1);
//            stmt.setInt(1, auctionId);
//            ResultSet resultSet = stmt.executeQuery();
//            if (resultSet.next()) {
//                auction = new Auction(auctionId, title, minimumBidPrice, productName, startDate, endDate);
//                auction.setAuctionId(resultSet.getInt(1));
//                auction.setTitle(resultSet.getString(2));
//                auction.setProductId(resultSet.getInt(3));
//                auction.setMinimumBidPrice(resultSet.getDouble(4));
//                auction.setStartDate(resultSet.getString(5));
//                auction.setEndDate(resultSet.getString(6));
//                auction.setSellerId(resultSet.getInt(7));
//            }
//            return auction;
//        } catch (SQLException e) {
//            throw new DaoException("oops something went wrong", e);
//        } catch (Exception e) {
//            throw new DaoException("oops something went wrong", e);
//        }
//    }

    //    public void editAuctionDetails(EditProductsDetails editProductsDetails) throws DaoException {
//        try {
//            String sql1 = "UPDATE auction set title = ?, minimum_bid_price = ?, start_date = ?, end_date = ? where auction_id= ?";
//            PreparedStatement ps1 = DbConnection.getInstance().getConnection().prepareStatement(sql1);
//            ps1.setString(1, editProductsDetails.getProductname());
//            ps1.setString(2, editProductsDetails.getProductcategory());
//            ps1.setString(3, editProductsDetails.getProductdescription());
//            ps1.setInt(4, editProductsDetails.getProductid());
//            ps1.executeUpdate();
//        } catch (SQLException e) {
//            throw new DaoException("oops something went wrong", e);
//        } catch (Exception e) {
//            throw new DaoException("oops something went wrong", e);
//        }
//    }
//    public void editAuctionDetails(AuctionDTO auctionDTO) throws DaoException{
//        try {
//            String sql1 = "UPDATE auction SET title = ?, minimum_bid_price = ?, product_id = ?, start_date = ?, end_date = ? where auction_id = ?";
//            PreparedStatement ps1 = DbConnection.getInstance().getConnection().prepareStatement(sql1);
//            ps1.setString(1, auctionDTO.getTitle());
//            ps1.setDouble(2, auctionDTO.getMinimumBidPrice());
//            ps1.setString(3, String.valueOf(auctionDTO.getProductId()));
//            ps1.setString(4, auctionDTO.getStartDate());
//            ps1.setString(5, auctionDTO.getEndDate());
//            ps1.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
    public void editAuctionDetails(AuctionDTO auctionDTO) {
        try {
            String sql1 = "UPDATE auction SET title = ?, product_id = ?, minimum_bid_price= ?, start_date = ?, end_date = ?  where auction_id = ?";
            PreparedStatement ps1 = DbConnection.getInstance().getConnection().prepareStatement(sql1);
            ps1.setString(1, auctionDTO.getTitle());
            ps1.setString(2, auctionDTO.getProductId());
            ps1.setDouble(3, Double.parseDouble(auctionDTO.getMinimumBidPrice()));
            ps1.setDate(4, java.sql.Date.valueOf(auctionDTO.getStartDate()));
            ps1.setDate(5, java.sql.Date.valueOf(auctionDTO.getEndDate()));
            ps1.setString(6, auctionDTO.getAuctionId());
            ps1.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void removeAuction(int auctionId) throws DaoException {
        try {
            String sql2 = "DELETE FROM auction where auction_id= ?";
            PreparedStatement ps2 = DbConnection.getInstance().getConnection().prepareStatement(sql2);
            ps2.setInt(1, auctionId);
            ps2.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("oops something went wrong", e);
        } catch (Exception e) {
            throw new DaoException("oops something went wrong", e);
        }
    }

    public Auction getAuctionById(String auctionId) {
        Auction auction = null;
        try {
            String query = "SELECT a.auction_id, a.title, p.product_name, a.minimum_bid_price, a.start_date, a.end_date, a.status FROM auction a JOIN product p ON a.product_id = p.product_id WHERE auction_id = ?";
            PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement(query);
            preparedStatement.setString(1, auctionId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                auction = new Auction();
                auction.setAuctionId(resultSet.getInt(1));
                auction.setTitle(resultSet.getString(2));
                auction.setProductName(resultSet.getString(3));
                auction.setMinimumBidPrice(resultSet.getDouble(4));
                auction.setStartDate(resultSet.getDate(5));
                auction.setEndDate(resultSet.getDate(6));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return auction;
    }

    public List<Auction> getAllAuctionsAsc(int seller_id) {
        List<Auction> auctions = new ArrayList<>();
        try {
            String sql = "SELECT a.auction_id, a.title, p.product_name, a.minimum_bid_price, a.start_date, a.end_date, a.status FROM auction a JOIN product p ON a.product_id = p.product_id WHERE a.seller_id = ? ORDER BY title asc";
            PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement(sql);
            preparedStatement.setInt(1, seller_id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Integer auctionId = resultSet.getInt("auction_id");
                String title = resultSet.getString("title");
                int minimumBidPrice = resultSet.getInt("minimum_bid_price");
                String productName = resultSet.getString("product_name");
                java.sql.Date startDate = resultSet.getDate("start_date");
                java.sql.Date endDate = resultSet.getDate("end_date");
                String status = resultSet.getString("status");
                Auction auction = new Auction(auctionId, title, minimumBidPrice, productName, startDate, endDate, status);
                auctions.add(auction);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return auctions;
    }

    public List<Auction> getAllAuctionsDesc(int seller_id) {
        List<Auction> auctions = new ArrayList<>();
        try {
            String sql1 = "SELECT a.auction_id, a.title, p.product_name, a.minimum_bid_price, a.start_date, a.end_date, a.status FROM auction a JOIN product p ON a.product_id = p.product_id WHERE a.seller_id = ? ORDER BY title desc";
            PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement(sql1);
            preparedStatement.setInt(1, seller_id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Integer auctionId = resultSet.getInt("auction_id");
                String title = resultSet.getString("title");
                int minimumBidPrice = resultSet.getInt("minimum_bid_price");
                String productName = resultSet.getString("product_name");
                java.sql.Date startDate = resultSet.getDate("start_date");
                java.sql.Date endDate = resultSet.getDate("end_date");
                String status = resultSet.getString("status");
                Auction auction = new Auction(auctionId, title, minimumBidPrice, productName, startDate, endDate, status);
                auctions.add(auction);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return auctions;
    }

    public List<Auction> getAllAuctionsByMinimumBidPriceLowToHigh(int sellerId) {
        List<Auction> auctionList = new ArrayList<>();
        try {
            String query = "SELECT a.auction_id, a.title, p.product_name, a.minimum_bid_price, a.start_date, a.end_date, a.status FROM auction a JOIN product p ON a.product_id = p.product_id WHERE a.seller_id = ? ORDER BY minimum_bid_price ASC";
            PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement(query);
            preparedStatement.setInt(1, sellerId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Integer auctionId = resultSet.getInt("auction_id");
                String title = resultSet.getString("title");
                int minimumBidPrice = resultSet.getInt("minimum_bid_price");
                String productName = resultSet.getString("product_name");
                java.sql.Date startDate = resultSet.getDate("start_date");
                java.sql.Date endDate = resultSet.getDate("end_date");
                String status = resultSet.getString("status");
                Auction auction = new Auction(auctionId, title, minimumBidPrice, productName, startDate, endDate, status);
                auctionList.add(auction);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return auctionList;
    }

    public List<Auction> getAllAuctionsByMinimumBidPriceHighToLow(int sellerId) {
        List<Auction> auctionList = new ArrayList<>();
        try {
            String query = "SELECT a.auction_id, a.title, p.product_name, a.minimum_bid_price, a.start_date, a.end_date, a.status FROM auction a JOIN product p ON a.product_id = p.product_id WHERE a.seller_id = ? ORDER BY minimum_bid_price DESC";
            PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement(query);
            preparedStatement.setInt(1, sellerId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Integer auctionId = resultSet.getInt("auction_id");
                String title = resultSet.getString("title");
                int minimumBidPrice = resultSet.getInt("minimum_bid_price");
                String productName = resultSet.getString("product_name");
                java.sql.Date startDate = resultSet.getDate("start_date");
                Date endDate = resultSet.getDate("end_date");
                String status = resultSet.getString("status");
                Auction auction = new Auction(auctionId, title, minimumBidPrice, productName, startDate, endDate, status);
                auctionList.add(auction);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return auctionList;
    }

    public List<Auction> auctionDetails() {
        List<Auction> auctionList = new ArrayList<>();
        try {
            String sql = "SELECT * FROM auction";
            PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Integer auctionId = resultSet.getInt("auction_id");
                String title = resultSet.getString("title");
                String status = resultSet.getString("status");
                Auction auction = new Auction(auctionId, title, status);
                auctionList.add(auction);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return auctionList;
    }

    public void editAuctionStatus(Auction auction) {
        try {
            String sql1 = "UPDATE auction SET status= ? where auction_id = ?";
            PreparedStatement ps1 = DbConnection.getInstance().getConnection().prepareStatement(sql1);
            ps1.setString(1, auction.getStatus());
            ps1.setInt(2, auction.getAuctionId());
            ps1.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

