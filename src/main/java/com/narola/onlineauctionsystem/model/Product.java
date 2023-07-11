package com.narola.onlineauctionsystem.model;

public class Product {
    private String productName;
    private String description;
    private String categoryName;
    private int categoryId;
    private int sellerId;
    private int productId;
    private String image;

    public Product() {

    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Product(Integer productId, String productName, String description, String categoryName, String image) {
        this.productId = productId;
        this.productName = productName;
        this.description = description;
        this.categoryName = categoryName;
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Product(Integer productId, String productName, String description, String categoryName) {
        this.productId = productId;
        this.productName = productName;
        this.description = description;
        this.categoryName = categoryName;
    }

    public Product(Integer productId, String productName) {
        this.productId = productId;
        this.productName = productName;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getSellerId() {
        return sellerId;
    }

    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
    }
}
