<%--
  Created by IntelliJ IDEA.
  User: pjagruti
  Date: 23-06-2023
  Time: 09:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Product Management</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 20px;
        }

        h1 {
            text-align: center;
        }

        .product-container {
            display: flex;
            flex-direction: column;
            align-items: center;
            margin-top: 40px;
        }

        .product {
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
            text-align: center;
            font-size: 16px;
        }

        .button:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
<h1>Product Management</h1>
<div>
    <form action="productManagement" method="post">
        <div class="product-container">
            <div class="product">
                <h2 class="product">Add Product</h2>
                <div class="button-container">
                    <a href="addProduct" class="button">Add</a>
                </div>
            </div>
        </div>

        <div class="product-container">
            <div class="product">
                <h2 class="product">View Product</h2>
                <div class="button-container">
                    <a href="viewProduct" class="button">View</a>
                </div>
            </div>
        </div>

<%--        <div class="product-container">--%>
<%--            <div class="product">--%>
<%--                <h2 class="product">Delete Product</h2>--%>
<%--                <div class="button-container">--%>
<%--                    <a href="deleteProduct" class="button">Delete</a>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--        </div>--%>
    </form>
</div>
</body>
</html>

