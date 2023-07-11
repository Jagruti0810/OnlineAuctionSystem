<%@ page import="java.util.List" %>
<%@ page import="com.narola.onlineauctionsystem.exception.Error" %>
<%@ page import="com.narola.onlineauctionsystem.model.Auction" %>
<%@ page import="com.narola.onlineauctionsystem.model.Product" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Edit Auction</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f5f5f5;
            padding: 20px;
        }

        input[type="submit"] {
            width: 100%;
            padding: 10px;
            background-color: #4CAF50;
            color: #ffffff;
            border: none;
            border-radius: 3px;
            cursor: pointer;
        }

        .container {
            max-width: 400px;
            margin: 0 auto;
            background-color: #fff;
            border-radius: 5px;
            padding: 20px;
            box-shadow: 0px 2px 10px rgba(0, 0, 0, 0.1);
        }

        .form-group {
            margin-bottom: 20px;
            font-weight: bold;
        }

        .form-label {
            font-weight: bold;
            margin-bottom: 5px;
        }

        .form-input {
            width: 100%;
            padding: 10px;
            border-radius: 5px;
            border: 1px solid #ccc;
            box-sizing: border-box;
            margin-bottom: 10px;
        }

        .errorList {
            color: #ff0000;
            margin-bottom: 10px;
        }

    </style>
</head>
<body>
<div class="container">
    <h2>Edit Auction</h2>
    <div class="errorList">
        <% List<Error> errorList = (List<Error>) request.getAttribute("errorList");
            if (errorList != null && !errorList.isEmpty()) { %>
        <ul>
            <% for (Error error : errorList) { %>
            <li><%= error.getMessage() %></li>
            <% } %>
        </ul>
        <% } %>
    </div>
    <form action="editAuction" method="post">
        <input type="hidden" name="auctionId" value="${auction.auctionId}">

        <div class="form-group">
            <label class="form-label" for="auction-title">Auction Title:</label>
            <input class="form-input" type="text" id="auction-title" name="auction-title" value="${auction.title}">
        </div>

        <div class="form-group">
            <label for="productId">Product:</label>
            <select name="productId" id="productId">
                <% List<Product> productList = (List<Product>) request.getAttribute("products");
                    Integer productId = (Integer) request.getAttribute("productId");

                    if (productList != null) {
                        for (Product product : productList) {
                            String selected = "";
                            if (productId != null && product.getProductId() == productId) {
                                selected = "selected";
                            }
                %>
                <option value="<%= product.getProductId() %>" <%= selected %>><%= product.getProductName() %></option>
                <% }
                }
                %>
            </select>
        </div>

        <div class="form-group">
            <label class="form-label" for="minimum-bid-price">Minimum bid price:</label>
            <input class="form-input" type="text" id="minimum-bid-price" name="minimum-bid-price" value="${auction.minimumBidPrice}">
        </div>

        <div class="form-group">
            <label class="form-label" for="start-date">Start date:</label>
            <input class="form-input" type="date" id="start-date" name="start-date" value="${auction.startDate}">
        </div>

        <div class="form-group">
            <label class="form-label" for="end-date">End date:</label>
            <input class="form-input" type="date" id="end-date" name="end-date" value="${auction.endDate}">
        </div>

        <input type="submit" value="Update">
    </form>
</div>
</body>
</html>
