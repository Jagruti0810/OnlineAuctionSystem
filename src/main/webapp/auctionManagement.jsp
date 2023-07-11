<%--
  Created by IntelliJ IDEA.
  User: pjagruti
  Date: 23-06-2023
  Time: 14:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Auction Management</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 20px;
        }

        h1 {
            text-align: center;
        }

        .auction-container {
            display: flex;
            flex-direction: column;
            align-items: center;
            margin-top: 40px;
        }

        .auction {
            display: flex;
            flex-direction: column;
            align-items: center;
            margin-bottom: 20px;
            padding: 20px;
            border: 1px solid #ccc;
            border-radius: 5px;
            width: 400px;
            max-width: 100%;
        }

        .button-container {
            margin-top: 10px;
        }

        .button {
            display: inline-block;
            padding: 10px 20px;
            background-color: #4CAF50;
            color: white;
            text-decoration: none;
            border-radius: 5px;
            transition: background-color 0.3s;
        }

        .button:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
<h1>Auction Management</h1>
<div>
    <form action="auctionManagement" method="post">
        <div class="auction-container">
            <div class="auction">
                <h2 class="auction">Create Auction</h2>
                <div class="button-container">
                    <a href="addAuction" class="button">Create</a>
                </div>
            </div>
        </div>

        <div class="auction-container">
            <div class="auction">
                <h2 class="auction">View Auction</h2>
                <div class="button-container">
                    <a href="viewAuction" class="button">View</a>
                </div>
            </div>
        </div>
    </form>
</div>
</body>
</html>

