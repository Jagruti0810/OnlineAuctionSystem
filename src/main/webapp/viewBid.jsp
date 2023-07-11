<%@ page import="com.narola.onlineauctionsystem.model.Bid" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Bid List</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f5f5f5;
            padding: 20px;
        }

        h1 {
            text-align: center;
            color: #333333;
        }

        h2 {
            text-align: center;
            color: #333333;
            align-items: center;
        }

        table {
            margin: 0 auto;
            border-collapse: collapse;
            width: 80%;
        }

        th, td {
            padding: 10px;
            text-align: left;
            border-bottom: 1px solid #dddddd;
        }

        tr:hover {
            background-color: #f2f2f2;
        }

        p {
            text-align: center;
            color: #333333;
            align-items: center;
        }
    </style>
</head>
<body>
<h1>Bid List</h1>
<table>
    <tr>
        <th>Bidder Id</th>
        <th>Bid Amount</th>
    </tr>
    <% List<Bid> bidList = (List<Bid>) request.getAttribute("bidList");
        for (Bid bid : bidList) { %>
    <tr>
        <td><%= bid.getUserId() %>
        </td>
        <td><%= bid.getAmount() %>
        </td>
    </tr>
    <% } %>
</table>
<h2>Highest Bid</h2>
<p>
    <strong>Bidder ID:</strong> ${highestBid.userId}<br>
    <strong>Amount:</strong> ${highestBid.amount}<br>
</p>
</body>
</html>
