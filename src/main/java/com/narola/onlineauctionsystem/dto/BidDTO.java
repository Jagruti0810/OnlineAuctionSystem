package com.narola.onlineauctionsystem.dto;

public class BidDTO {
    private String bidId;
    private int bidderId;
    private String productId;
    private String amount;
    private String auctionId;
    private boolean hasPlacedBid;

    public boolean isHasPlacedBid() {
        return hasPlacedBid;
    }

    public void setHasPlacedBid(boolean hasPlacedBid) {
        this.hasPlacedBid = hasPlacedBid;
    }

    public String getBidId() {
        return bidId;
    }

    public void setBidId(String bidId) {
        this.bidId = bidId;
    }

    public int getBidderId() {
        return bidderId;
    }

    public void setBidderId(int bidderId) {
        this.bidderId = bidderId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getAuctionId() {
        return auctionId;
    }

    public void setAuctionId(String auctionId) {
        this.auctionId = auctionId;
    }
}
