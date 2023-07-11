<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="com.narola.onlineauctionsystem.model.User" %>
<!DOCTYPE html>
<html>
<head>
    <title>User Profile</title>
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

        .profile-card {
            display: flex;
            align-items: center;
            justify-content: center;
            flex-direction: column;
            padding: 20px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }

        .profile-info {
            text-align: center;
        }

        .profile-info label {
            display: inline-block;
            width: 100px;
            margin-bottom: 10px;
            font-weight: bold;
        }

        .profile-info span {
            display: inline-block;
            margin-bottom: 10px;
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
    <h2>User Profile</h2>
    <div class="profile-card">
        <div class="profile-info">
            <label>Name:</label>
            <span>${user.username}</span>
            <br>

            <label>Mobile no:</label>
            <span>${user.mobileNo}</span>
            <br>

            <label>Email:</label>
            <span>${user.email}</span>
            <br>

            <label>Address:</label>
            <span>${user.address}</span>
            <br>

            <form action="editSellerProfile">
                <input type="hidden" name="id" value="${user.userId}">
                <button type="submit" class="button">Edit</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>
