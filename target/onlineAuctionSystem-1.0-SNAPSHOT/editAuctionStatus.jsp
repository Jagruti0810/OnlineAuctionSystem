<%--
  Created by IntelliJ IDEA.
  User: pjagruti
  Date: 07-07-2023
  Time: 14:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update Auction Status</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
            justify-content: center;
            align-items: center;
        }

        .container {
            max-width: 800px;
            margin: 0 auto;
            padding: 20px;
            background-color: #fff;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        h2 {
            color: #333;
            text-align: center;
            margin-bottom: 20px;
        }

        .update-status {
            display: flex;
            align-items: center;
            justify-content: center;
            flex-direction: column;
            padding: 20px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }

        .status-info {
            text-align: center;
        }

        .profile-info label {
            display: inline-block;
            width: 100px;
            margin-bottom: 30px;
            font-weight: bold;
        }

        .profile-info span {
            display: inline-block;
            margin-bottom: 30px;
        }

        .button {
            display: inline-block;
            padding: 10px 20px;
            background-color: #4CAF50;
            color: white;
            text-decoration: none;
            border-radius: 5px;
            transition: background-color 0.3s;
            text-align: center;
            font-size: 16px;
            margin-top: 50px;
        }
    </style>
</head>
<body>
<div class="container">
    <h2>Update auction status</h2>
    <div class="update-status">
        <div class="status-info">
            <label>Start Date:</label>
            <span>${auction.startDate}</span>
            <br>

            <label>End Date:</label>
            <span>${auction.endDate}</span>
            <br>

            <form action="editAuctionStatus" method="post">
                <input type="hidden" name="auctionId" value="${auction.auctionId}">
                <div class="form-group">
                    <label for="auction-status">Status:</label>
                    <select name="auction-status" id="auction-status">
                        <option disabled selected>Select your status</option>
                        <option>active</option>
                        <option>closed</option>
                        <option>cancelled</option>
                    </select>
                </div>
                <button type="submit" class="button">Update</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>
