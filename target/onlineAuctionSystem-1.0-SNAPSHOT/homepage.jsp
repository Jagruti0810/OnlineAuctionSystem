<%@ page import="com.narola.onlineauctionsystem.model.User" %>
<%@ page import="com.narola.onlineauctionsystem.model.Role" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Welcome</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
            padding: 20px;
            display: flex;
            flex-direction: column;
            align-items: center;
            height: 100vh;
            text-align: center;
        }

        h1 {
            color: #333;
            font-size: 24px;
            margin-bottom: 10px;
        }

        p {
            color: #666;
            font-size: 16px;
            margin-bottom: 20px;
        }

        form {
            display: inline-block;
            margin-right: 10px;
        }

        input[type="submit"] {
            background-color: #4CAF50;
            color: white;
            border: none;
            padding: 10px 20px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            font-size: 16px;
            margin-top: 10px;
            cursor: pointer;
            border-radius: 4px;
        }
        .button {
            display: inline-block;
            padding: 8px 12px;
            background-color: #4CAF50;
            color: #ffffff;
            text-align: center;
            text-decoration: none;
            font-size: 16px;
            border-radius: 4px;
            transition: background-color 0.3s ease;
            margin-bottom: 20px;
        }

        .button:hover {
            background-color: #45a049;
        }

        input[type="submit"]:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
<%--<h1>Welcome to Online Auction System <%= ((User) session.getAttribute("user")).getUsername() %>!</h1>--%>
<h1>Your role bidder</h1>
</p>
<form action="buyer-dashboard" method="post">
    <button type="submit" class="button">Proceed</button>
</form>
<form action="logout" method="post">
    <button type="submit" class="button">Logout</button>
</form>
</body>
</html>



