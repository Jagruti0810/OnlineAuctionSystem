<%@ page import="java.util.List" %>
<%@ page import="com.narola.onlineauctionsystem.exception.Error" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Sign Up</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
        }

        .container {
            max-width: 400px;
            margin: 5px auto;
            padding: 20px;
            background-color: #ffffff;
            border-radius: 5px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
        }

        h2 {
            text-align: center;
            color: #333333;
        }

        label {
            display: block;
            margin-top: 10px;
            color: #666666;
            font-weight: bold;
        }

        input[type="text"],
        input[type="password"] {
            width: 95%;
            padding: 10px;
            margin-top: 5px;
            margin-bottom: 20px;
            border: 1px solid #dddddd;
            border-radius: 3px;
            font-weight: bold;
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

        input[type="submit"]:hover {
            background-color: #45a049;
        }

        .errorList {
            color: #ff0000;
            margin-bottom: 10px;
        }
    </style>
</head>
<body>
<div class="container">
    <h2>Registration</h2>
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
    <form action="editBidderProfile" method="post">
    <input type="hidden" name="userId" value="${user.userId}">
    <label class="form-label" for="user-name">User Name:</label>
    <input class="form-input" type="text" id="user-name" name="user-name" value="${user.username}">

    <label class="form-label" for="mobile-no">Mobile no:</label>
    <input class="form-input" type="text" id="mobile-no" name="mobile-no" value="${user.mobileNo}">

    <label class="form-label" for="mobile-no">Email:</label>
    <input class="form-input" type="text" id="email" name="email" value="${user.email}">

    <label class="form-label" for="address">Address:</label>
    <input class="form-input" type="text" id="address" name="address" value="${user.address}">

    <input type="submit" value="Update">
    </form>
</div>
</body>
</html>
