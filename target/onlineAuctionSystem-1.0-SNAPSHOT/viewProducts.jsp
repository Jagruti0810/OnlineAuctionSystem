<%@ page import="com.narola.onlineauctionsystem.model.Product" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
    <title>Product List</title>
    <link rel="stylesheet" href="Home.css">
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

        .button {
            display: inline-block;
            padding: 8px 12px;
            background-color: #4CAF50;
            color: #ffffff;
            text-align: center;
            text-decoration: none;
            font-size: 14px;
            border-radius: 4px;
            transition: background-color 0.3s ease;
        }

        .button:hover {
            background-color: #45a049;
        }
    </style>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script>
        $(document).ready(function() {
            var selectedOption = new URLSearchParams(window.location.search).get('sort');
            if (selectedOption) {
                $('#sort').val(selectedOption);
            }
            $('#sort').change(function() {
                $('#sortForm').submit();
            });
        });
    </script>
</head>
<body>
<h1>Product List</h1>
<form id="sortForm" action="products" method="get" style="text-align: center;">
    <label for="sort">Sort by:</label>
    <select name="sort" id="sort">
        <option value="" disabled selected>Select your choice</option>
        <option value="asc">Product Name (A-Z)</option>
        <option value="desc">Product Name (Z-A)</option>
    </select>
</form>
<table>
    <tr>
        <th>Product Id</th>
        <th>Image</th>
        <th>Product Name</th>
        <th>Description</th>
        <th>Category Name</th>
        <th></th>
        <th></th>
    </tr>
    <%
        List<Product> productList = (List<Product>) request.getAttribute("productList");
        for (Product product : productList) {
    %>
    <tr>
        <td><%= product.getProductId() %></td>
        <td><img src="<%= product.getImage() %>" alt="Product Image" width="60" height="60"></td>
        <td><%= product.getProductName() %></td>
        <td><%= product.getDescription() %></td>
        <td><%= product.getCategoryName() %></td>
        <td><form action="editProduct">
            <input type="hidden" name="id" value="<%= product.getProductId() %>">
            <button type="submit" class="button">Edit</button>
        </form></td>
        <td>
            <form action="deleteProduct" method="post" onsubmit="return confirm('Are you sure you want to delete this product?');">
                <input type="hidden" name="id" value="<%= product.getProductId() %>">
                <button type="submit" class="button">Delete</button>
            </form>
        </td>
    </tr>
    <%
        }
    %>
</table>
</body>
</html>

