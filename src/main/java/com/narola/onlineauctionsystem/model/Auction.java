package com.narola.onlineauctionsystem.model;

import java.time.LocalDate;
import java.util.Date;

public class Auction {

    private String title;
    private double minimumBidPrice;
    private int productId;
    private Date startDate;
    private Date endDate;
    private int auctionId;
    private int sellerId;
    private String productName;
    private Date auctionDate;
    private String status;

    public String getStatus() {
        return status;
//        LocalDate currentDate = LocalDate.now();
//
//        if (currentDate.isBefore(startDate)) {
//            return "Pending";
//        } else if (currentDate.isAfter(endDate)) {
//            return "Closed";
//        } else {
//            return "Ongoing";
//        }
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Auction(Integer auctionId, String title, String status) {
        this.auctionId = auctionId;
        this.title = title;
        this.status = status;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getAuctionDate() {
        return auctionDate;
    }

    public void setAuctionDate(Date auctionDate) {
        this.auctionDate = auctionDate;
    }

    public Auction() {

    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getAuctionId() {
        return auctionId;
    }

    public void setAuctionId(int auctionId) {
        this.auctionId = auctionId;
    }

    public Auction(Integer auctionId, String title, double minimumBidPrice, String productName, Date startDate, Date endDate, String status) {
        this.auctionId = auctionId;
        this.title = title;
        this.minimumBidPrice = minimumBidPrice;
        this.productName = productName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getMinimumBidPrice() {
        return minimumBidPrice;
    }

    public void setMinimumBidPrice(double minimumBidPrice) {
        this.minimumBidPrice = minimumBidPrice;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getSellerId() {
        return sellerId;
    }

    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
    }
}
