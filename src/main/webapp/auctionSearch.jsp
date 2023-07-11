<%@ page import="com.narola.onlineauctionsystem.model.Auction" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Search Results</title>
    <style>
        .container {
            max-width: 800px;
            margin: 0 auto;
            padding: 20px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
        }

        th, td {
            padding: 10px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }

        th {
            background-color: #f2f2f2;
        }
        .container {
            max-width: 800px;
            margin: 0 auto;
            padding: 20px;
        }

        .auction-boxes {
            display: grid;
            grid-template-columns: repeat(auto-fill, minmax(250px, 1fr));
            gap: 20px;
        }

        .auction-box {
            background-color: #f2f2f2;
            border-radius: 5px;
            padding: 10px;
        }

        .auction-box-header h3 {
            margin: 0;
        }

        .auction-box-content {
            padding: 10px;
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
        }
    </style>
</head>
<body>
<div class="container">
    <h2>Auctions</h2>
    <div class="auction-boxes">
        <%
            List<Auction> auctionList = (List<Auction>) request.getAttribute("auctionList");
            for (Auction auction : auctionList) {
        %>
        <div class="auction-box">
            <div class="auction-box-content">
                <p><strong>Auction Id:</strong><%= auction.getAuctionId() %></p>
                <p><strong>Auction Name:</strong> <%= auction.getTitle() %></p>
            </div>
            <form action="auctionDetails">
                <input type="hidden" name="id" value="<%= auction.getAuctionId() %>">
                <button type="submit" class="button">Auction details</button>
            </form>

        </div>
        <%
            }
        %>
    </div>
</div>

</body>
</html>
