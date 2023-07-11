<%@ page import="com.narola.onlineauctionsystem.model.User" %>
<%@ page import="com.narola.onlineauctionsystem.model.Role" %>
<%--<%@ page import="com.narola.onlineauctionsystem.model.User" %>--%>
<%--<%@ page import="com.narola.onlineauctionsystem.model.Role" %>&lt;%&ndash;--%>
<%--  Created by IntelliJ IDEA.--%>
<%--  User: pjagruti--%>
<%--  Date: 20-06-2023--%>
<%--  Time: 14:48--%>
<%--  To change this template use File | Settings | File Templates.--%>
<%--&ndash;%&gt;--%>
<%--<!DOCTYPE html>--%>
<%--<html>--%>
<%--<head>--%>
<%--    <title>Welcome</title>--%>
<%--    <style>--%>
<%--        body {--%>
<%--            font-family: Arial, sans-serif;--%>
<%--            background-color: #f2f2f2;--%>
<%--            padding: 20px;--%>
<%--            margin-bottom: 10px;--%>
<%--        }--%>

<%--        .container {--%>
<%--            display: flex;--%>
<%--            flex-direction: column;--%>
<%--            align-items: center;--%>
<%--            height: 100vh;--%>
<%--            margin-bottom: 10px;--%>
<%--        }--%>

<%--        h1 {--%>
<%--            color: #333;--%>
<%--            font-size: 24px;--%>
<%--            margin-bottom: 20px;--%>
<%--        }--%>

<%--        form {--%>
<%--            display: inline-block;--%>
<%--            margin-right: 10px;--%>
<%--            margin-bottom: 20px;--%>
<%--        }--%>

<%--        input[type="radio"] {--%>
<%--            margin-right: 5px;--%>
<%--            margin-bottom: 10px;--%>
<%--        }--%>

<%--        .button {--%>
<%--            display: inline-block;--%>
<%--            padding: 8px 12px;--%>
<%--            background-color: #4CAF50;--%>
<%--            color: #ffffff;--%>
<%--            text-align: center;--%>
<%--            text-decoration: none;--%>
<%--            font-size: 14px;--%>
<%--            border-radius: 4px;--%>
<%--            transition: background-color 0.3s ease;--%>
<%--            border: none;--%>
<%--            cursor: pointer;--%>
<%--            margin-bottom: 10px;--%>
<%--        }--%>

<%--        .button:hover {--%>
<%--            background-color: #45a049;--%>
<%--        }--%>
<%--    </style>--%>
<%--</head>--%>
<%--<body>--%>
<%--<div class="container">--%>
<%--    <h1>Welcome to Online Auction System <%= ((User) session.getAttribute("user")).getUsername() %>!</h1>--%>
<%--    &lt;%&ndash;    <h1>Your role is <%= ((User) session.getAttribute("roleType")).getRoleType() %></h1>&ndash;%&gt;--%>

<%--    &lt;%&ndash;    <h1>Select an option:</h1>&ndash;%&gt;--%>
<%--    &lt;%&ndash;    <form action="productManagement" method="post">&ndash;%&gt;--%>
<%--    &lt;%&ndash;        <input type="radio" id="productManagement" name="managementType" value="product">&ndash;%&gt;--%>
<%--    &lt;%&ndash;        <label for="productManagement">Product Management</label>&ndash;%&gt;--%>
<%--    &lt;%&ndash;        <br>&ndash;%&gt;--%>
<%--    &lt;%&ndash;        <input type="radio" id="auctionManagement" name="managementType" value="auction">&ndash;%&gt;--%>
<%--    &lt;%&ndash;        <label for="auctionManagement">Auction Management</label>&ndash;%&gt;--%>
<%--    &lt;%&ndash;        <br>&ndash;%&gt;--%>
<%--    &lt;%&ndash;        <button type="submit" class="button">Submit</button>&ndash;%&gt;--%>
<%--    &lt;%&ndash;    </form>&ndash;%&gt;--%>
<%--    <form action="productManagement" method="post">--%>
<%--        <button type="submit" class="button">Product Management</button>--%>
<%--    </form>--%>
<%--    <form action="auctionManagement" method="post">--%>
<%--        <button type="submit" class="button">Auction Management</button>--%>
<%--    </form>--%>
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
        h1 {
            color: #333;
            font-size: 24px;
            margin-bottom: 10px;
            text-align: center;
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
    <a href="homePage">Home</a>
    <a href="productManagement">Product</a>
    <a href="auctionManagement">Auction</a>
    <a href="sellerProfile">My Account</a>
    <a href="logout">Logout</a>
</div>
<div class="content">
    <h1>Welcome to Online Auction System <%= ((User) session.getAttribute("user")).getUsername() %>!</h1>
    <h1>Your role is Seller</h1>
</div>
</body>
</html>



