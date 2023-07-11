<%@ page import="com.narola.onlineauctionsystem.model.User" %>
<%@ page import="com.narola.onlineauctionsystem.model.Auction" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<!DOCTYPE html>--%>
<%--<html>--%>
<%--<head>--%>
<%--    <title>Seller dashboard</title>--%>
<%--    <style>--%>
<%--        body {--%>
<%--            margin: 10px;--%>
<%--            padding: 20px;--%>
<%--            font-family: Arial, sans-serif;--%>
<%--            justify-content: center;--%>
<%--        }--%>

<%--        .container {--%>
<%--            max-width: 400px;--%>
<%--            margin: 0 auto;--%>
<%--            padding: 20px;--%>
<%--            background-color: #fff;--%>
<%--            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);--%>
<%--            border-radius: 5px;--%>
<%--        }--%>

<%--        .navbar {--%>
<%--            font-size: 20px;--%>
<%--            background-color: #4CAF50;--%>
<%--            position: fixed;--%>
<%--            top: 0;--%>
<%--            left: 0;--%>
<%--            width: 100%;--%>
<%--            height: 30px;--%>
<%--            padding: 10px;--%>
<%--            color: #fff;--%>
<%--            align-items: center;--%>
<%--            justify-content: center;--%>
<%--        }--%>

<%--        label {--%>
<%--            display: block;--%>
<%--            margin-bottom: 10px;--%>
<%--            font-weight: bold;--%>
<%--        }--%>

<%--        input[type="date"] {--%>
<%--            width: 100%;--%>
<%--            padding: 10px;--%>
<%--            margin-bottom: 20px;--%>
<%--            border: 1px solid #ccc;--%>
<%--            border-radius: 4px;--%>
<%--        }--%>

<%--        button[type="submit"] {--%>
<%--            display: block;--%>
<%--            width: 100%;--%>
<%--            padding: 10px;--%>
<%--            background-color: #4CAF50;--%>
<%--            color: #fff;--%>
<%--            border: none;--%>
<%--            border-radius: 4px;--%>
<%--            cursor: pointer;--%>
<%--            align-items: center;--%>
<%--        }--%>

<%--        button[type="submit"]:hover {--%>
<%--            background-color: #45a049;--%>
<%--        }--%>

<%--        h1 {--%>
<%--            color: #333;--%>
<%--            font-size: 24px;--%>
<%--            margin-bottom: 10px;--%>
<%--            text-align: center;--%>
<%--        }--%>

<%--        .button {--%>
<%--            display: inline-block;--%>
<%--            padding: 10px 20px;--%>
<%--            background-color: #4CAF50;--%>
<%--            color: white;--%>
<%--            text-decoration: none;--%>
<%--            border-radius: 5px;--%>
<%--            transition: background-color 0.3s;--%>
<%--            text-align: center;--%>
<%--            font-size: 16px;--%>
<%--            align-items: center;--%>
<%--        }--%>

<%--        .navbar a {--%>
<%--            color: #fff;--%>
<%--            text-decoration: none;--%>
<%--            margin-right: 10px;--%>
<%--            margin: 0 10px;--%>
<%--            align-items: center;--%>
<%--            justify-content: center;--%>
<%--        }--%>

<%--        .content {--%>
<%--            margin-top: 80px;--%>
<%--            padding: 20px;--%>
<%--        }--%>
<%--    </style>--%>
<%--</head>--%>
<%--<body>--%>
<%--<div class="navbar">--%>
<%--    <a href="homePage">Home</a>--%>
<%--    <a href="bidderProfile">My Profile</a>--%>
<%--    <a href="">Auction</a>--%>
<%--    <a href="logout">Logout</a>--%>
<%--</div>--%>
<%--<div class="container">--%>
<%--    <h1>Welcome to Online Auction System <%= ((User) session.getAttribute("user")).getUsername() %>!</h1>--%>
<%--    <form action="bidderDashboard">--%>
<%--        <label for="startDate">Start Date:</label>--%>
<%--        <input type="date" id="startDate" name="startDate">--%>
<%--        <br>--%>
<%--        <label for="endDate">End Date:</label>--%>
<%--        <input type="date" id="endDate" name="endDate">--%>
<%--        <br>--%>
<%--        <a href="searchAuction?startDate="+startDate class="button">Search</a>--%>
<%--        <input type="hidden" name="startDate" value="${startDate}">--%>
<%--        <input type="hidden" name="endDate" value="${endDate}">--%>
<%--    </form>--%>
<%--    &lt;%&ndash;    <script>&ndash;%&gt;--%>
<%--    &lt;%&ndash;        function setMinEndDate() {&ndash;%&gt;--%>
<%--    &lt;%&ndash;            var startDateInput = document.getElementById("startDate");&ndash;%&gt;--%>
<%--    &lt;%&ndash;            var endDateInput = document.getElementById("endDate");&ndash;%&gt;--%>

<%--    &lt;%&ndash;            var startDate = new Date(startDateInput.value);&ndash;%&gt;--%>
<%--    &lt;%&ndash;            var endDate = new Date(endDateInput.value);&ndash;%&gt;--%>

<%--    &lt;%&ndash;            if (endDate < startDate) {&ndash;%&gt;--%>
<%--    &lt;%&ndash;                endDateInput.value = startDateInput.value;&ndash;%&gt;--%>
<%--    &lt;%&ndash;            }&ndash;%&gt;--%>
<%--    &lt;%&ndash;        }&ndash;%&gt;--%>
<%--    &lt;%&ndash;    </script>&ndash;%&gt;--%>
<%--</div>--%>
<%--</body>--%>
<%--</html>--%>
<!DOCTYPE html>
<html>
<head>
    <title>Seller dashboard</title>
    <style>
        body {
            margin: 10px;
            padding: 20px;
            font-family: Arial, sans-serif;
            justify-content: center;
        }

        .navbar {
            font-size: 20px;
            background-color: #4CAF50;
            position: fixed;
            top: 0;
            left: 0;
            width: 100%;
            height: 30px;
            padding: 10px;
            color: #fff;
            align-items: center;
            justify-content: center;
        }

        label {
            display: block;
            margin-bottom: 10px;
            font-weight: bold;
        }

        tr:hover {
            background-color: #f2f2f2;
        }

        table {
            margin: 0 auto;
            border-collapse: collapse;
            width: 80%;
        }

        input[type="date"] {
            width: 100%;
            padding: 10px;
            margin-bottom: 20px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }

        button[type="submit"] {
            display: block;
            width: 100%;
            padding: 10px;
            background-color: #4CAF50;
            color: #fff;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            align-items: center;
        }

        button[type="submit"]:hover {
            background-color: #45a049;
        }

        h1 {
            color: #333;
            font-size: 24px;
            margin-bottom: 10px;
            text-align: center;
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
            align-items: center;
        }

        th, td {
            padding: 10px;
            text-align: left;
            border-bottom: 1px solid #dddddd;
        }
        input[type="text"] {
            width: 95%;
            padding: 10px;
            border: 1px solid #dddddd;
            border-radius: 4px;
        }

        .navbar a {
            color: #fff;
            text-decoration: none;
            margin-right: 10px;
            margin: 0 10px;
            align-items: center;
            justify-content: center;
        }

        .content {
            margin-top: 80px;
            padding: 20px;
        }

    </style>
</head>
<body>
<div class="navbar">
    <a href="bidderProfile">My Profile</a>
    <a href="logout">Logout</a>
</div>
<h1>Welcome to Online Auction System <%= ((User) session.getAttribute("user")).getUsername() %>!</h1>
<%--    <form action="searchAuction">--%>
<%--        <label for="startDate">Start Date:</label>--%>
<%--        <input type="date" id="startDate" name="startDate">--%>
<%--        <br>--%>
<%--        <label for="endDate">End Date:</label>--%>
<%--        <input type="date" id="endDate" name="endDate">--%>
<%--        <br>--%>
<%--        <input type="submit" class="button" value="Search">--%>
<%--    </form>--%>
<table>
    <tr>
        <th>Auction Title</th>
        <th>Status</th>
    </tr>

    <%
        List<Auction> auctionList = (List<Auction>) request.getAttribute("auctionList");
        for (Auction auction : auctionList) {
    %>
    <tr>
        <td>
            <input type="hidden" name="id" value="<%= auction.getAuctionId() %>">
            <a href="auctionDetails?id=<%= auction.getAuctionId() %>"><%= auction.getTitle() %>
            </a>
        </td>
        <td><%= auction.getStatus() %>
        </td>
        <%--            <input type="hidden" name="id" value="<%= auction.getAuctionId() %>">--%>
    </tr>
    <% } %>
</table>
</body>
</html>
