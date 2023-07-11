<%@ page import="java.util.List" %>
<%@ page import="com.narola.onlineauctionsystem.exception.Error" %>
<%@ page import="com.narola.onlineauctionsystem.model.ProductCategory" %>
<%@ page import="com.narola.onlineauctionsystem.model.Product" %><%--
  Created by IntelliJ IDEA.
  User: pjagruti
  Date: 23-06-2023
  Time: 18:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Add Auction</title>
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
            display: block;
            font-weight: bold;
            margin-bottom: 10px;
        }

        .form-label {
            display: block;
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
    <script>
        function validateDates() {
            var startDateInput = document.getElementById("start-date");
            var endDateInput = document.getElementById("end-date");

            var startDate = new Date(startDateInput.value);
            var endDate = new Date(endDateInput.value);

            var currentDate = new Date();

            if (startDate < currentDate) {
                alert("Please select a start date after the current date.");
                return false;
            }

            if (endDate <= startDate) {
                alert("Please select an end date after the start date.");
                return false;
            }
            return true;
        }
    </script>
</head>
<body>
<div class="container">
    <h2>Create Auction</h2>
    <div class="errorList">
        <%
            List<Error> errorList = (List<Error>) request.getAttribute("errorList");
            if (errorList != null && !errorList.isEmpty()) {
        %>
        <ul>
            <% for (Error error : errorList) { %>
            <div>
                <li><%= error.getMessage() %>
                </li>
            </div>
            <% } %>
        </ul>
        <% } %>
    </div>
    <form action="addAuction" method="post" onsubmit="return validateDates()">

        <label class="form-label" for="auction-title">Auction title:</label>
        <input class="form-input" type="text" id="auction-title" name="auction-title">
        <div class="form-group">
            <label for="productId">Product:</label>
            <select name="productId" id="productId">
                <option disabled selected>Select your Product</option>
                <%
                    List<Product> productList = (List<Product>) request.getAttribute("products");
                    for (Product product : productList) {
                %>
                <option value="<%= product.getProductId() %>"><%= product.getProductName() %>
                </option>
                <% } %>
            </select>
        </div>
        <label class="form-label" for="minimum-bid-price">Minimum bid price:</label>
        <input class="form-input" type="text" id="minimum-bid-price" name="minimum-bid-price">

        <label class="form-label" for="start-date">Start date:</label>
        <input class="form-input" type="date" id="start-date" name="start-date">

        <label class="form-label" for="end-date">End date:</label>
        <input class="form-input" type="date" id="end-date" name="end-date">

        <input type="submit" value="Add">
    </form>
</div>
</body>
</html>

