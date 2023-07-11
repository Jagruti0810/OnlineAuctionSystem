package com.narola.onlineauctionsystem.model;

public class Bid {
    private int bidId;
    private int userId;
    private double amount;
    private String auctionId;
    private boolean hasPlacedBid;

    public Bid(String auctionId, int bidderId, double bidAmount) {
        this.auctionId = auctionId;
        this.userId = bidderId;
        this.amount = bidAmount;
    }

    public Bid(String auctionId, double amount, Integer userId) {
        this.auctionId = auctionId;
        this.userId = userId;
        this.amount = amount;
    }

    public Bid(String auctionId, double amount) {
        this.auctionId = auctionId;
        this.amount = amount;
    }

    public boolean isHasPlacedBid() {
        return hasPlacedBid;
    }

    public void setHasPlacedBid(boolean hasPlacedBid) {
        this.hasPlacedBid = hasPlacedBid;
    }

    public Bid(int bidId, int userId, String auctionId, double amount) {
        this.auctionId = auctionId;
        this.bidId = bidId;
        this.userId = userId;
        this.amount = amount;
    }

    public Bid() {

    }

    public String getAuctionId() {
        return auctionId;
    }

    public void setAuctionId(String auctionId) {
        this.auctionId = auctionId;
    }

    public int getBidId() {
        return bidId;
    }

    public void setBidId(int bidId) {
        this.bidId = bidId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
