package com.narola.onlineauctionsystem.dto;

import java.util.Date;

public class AuctionDTO {
    private String title;
    private String minimumBidPrice;
    private String productId;
    private String startDate;
    private String endDate;
    private String auctionId;
    private int sellerId;
    private String status;

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMinimumBidPrice() {
        return minimumBidPrice;
    }

    public void setMinimumBidPrice(String minimumBidPrice) {
        this.minimumBidPrice = minimumBidPrice;
    }

    public String getAuctionId() {
        return auctionId;
    }

    public void setAuctionId(String auctionId) {
        this.auctionId = auctionId;
    }

    public int getSellerId() {
        return sellerId;
    }

    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
    }
}
