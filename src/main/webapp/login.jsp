<%@ page import="java.util.List" %>
<%@ page import="com.narola.onlineauctionsystem.exception.Error" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
        }

        .container {
            max-width: 400px;
            margin: 0 auto;
            padding: 20px;
            background-color: #ffffff;
            border-radius: 5px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0);
        }

        h2 {
            text-align: center;
            color: #333333;
        }

        label {
            display: block;
            margin-top: 10px;
            color: #666666;
        }

        input[type="text"],
        input[type="password"] {
            width: 95%;
            padding: 10px;
            margin-top: 5px;
            margin-bottom: 20px;
            border: 1px solid #dddddd;
            border-radius: 3px;
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

        .error-message {
            color: #ff0000;
            margin-bottom: 10px;
        }

        .errorList {
            color: #ff0000;
            margin-bottom: 10px;
        }

        .toggle-form {
            text-align: center;
            margin-top: 10px;
        }

        .toggle-form a {
            color: #666666;
            text-decoration: none;
        }
    </style>
</head>
<body>
<div class="container">
    <h2>Login</h2>
    <div>
        <c:forEach var="error" items="${errorList}">
            <p>${error.message}</p>
        </c:forEach>
    </div>
    <div class="errorList">
        <% if (request.getAttribute("errorList") != null) {
            List<Error> errorList1 = (List<Error>) request.getAttribute("errorList");
            if (!errorList1.isEmpty()) {
        %>
        <ul>
            <% for (Error error : errorList1) { %>
            <li><%= error.getMessage() %>
            </li>
            <% } %>
        </ul>
        <% }
        } %>
    </div>
    <form method="post" action="login">
        <label for="email">Email:</label>
        <input type="text" id="email" name="email">

        <label for="password">Password:</label>
        <input type="password" id="password" name="password">

        <input type="submit" value="Login">
    </form>

    <div class="toggle-form">
        <p>Don't have an account?<a href="registration" style="color: blue; text-decoration: underline;">
            Create new!</a></p>
    </div>
</div>
</body>
</html>


