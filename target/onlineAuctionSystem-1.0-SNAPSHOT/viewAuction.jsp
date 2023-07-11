<%@ page import="java.util.List" %>
<%@ page import="com.narola.onlineauctionsystem.model.Auction" %>
<!DOCTYPE html>
<html>
<head>
    <title>Auction List</title>
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
        $(document).ready(function () {
            var selectedOption = new URLSearchParams(window.location.search).get('sort');
            if (selectedOption) {
                $('#sort').val(selectedOption);
            }
            $('#sort').change(function () {
                $('#sortForm').submit();
            });
        });
    </script>
</head>
<body>
<h1>Auction List</h1>
<div style="display: flex; flex-direction: column; align-items: center;">
    <form id="sortForm" action="auctions" method="get">
        <label for="sort">Sort by:</label>
        <select name="sort" id="sort">
            <option value="" disabled selected>Select your choice</option>
            <option value="asc">Auction Title (A-Z)</option>
            <option value="desc">Auction Title (Z-A)</option>
            <option value="Low">Minimum bid price (Low to High)</option>
            <option value="High">Minimum bid price (High to Low)</option>
        </select>
    </form>
</div>
<%--    <div>--%>
<%--        <form id="sortForm1" action="auctions" method="get" style="text-align: center;">--%>
<%--            <label for="sorting">Sort by:</label>--%>
<%--            <select name="sorting" id="sorting">--%>
<%--                <option value="" disabled selected>Select your choice</option>--%>
<%--                <option value="Low">Minimum bid price (Low to High)</option>--%>
<%--                <option value="High">Minimum bid price (High to Low)</option>--%>
<%--            </select>--%>
<%--        </form>--%>
<%--    </div>--%>
<%--</div>--%>
<table>
    <tr>
        <th>Auction Id</th>
        <th>Auction Name</th>
        <th>Product Name</th>
        <th>Minimum Bid Price</th>
        <th>Start date</th>
        <th>End date</th>
        <th>Status</th>
        <th></th>
        <th></th>
        <th></th>
    </tr>
    <%
        List<Auction> auctionList = (List<Auction>) request.getAttribute("auctionList");
        for (Auction auction : auctionList) {
    %>
    <tr>
        <td><%= auction.getAuctionId() %>
        </td>
        <td><%= auction.getTitle() %>
        </td>
        <td><%= auction.getProductName() %>
        </td>
        <td><%= auction.getMinimumBidPrice() %>
        </td>
        <td><%= auction.getStartDate() %>
        </td>
        <td><%= auction.getEndDate() %>
        </td>
        <td><%= auction.getStatus() %>
        </td>
        <td>
            <form action="editAuction">
                <input type="hidden" name="id" value="<%= auction.getAuctionId() %>">
                <button type="submit" class="button">Edit</button>
            </form>
        </td>
        <td>
            <form action="deleteAuction" method="post"
                  onsubmit="return confirm('Are you sure you want to delete this product?');">
                <input type="hidden" name="id" value="<%= auction.getAuctionId() %>">
                <button type="submit" class="button">Delete</button>
            </form>
        </td>
        <td>
            <form action="editAuctionStatus">
                <input type="hidden" name="id" value="<%= auction.getAuctionId() %>">
                <button type="submit" class="button">Update status</button>
            </form>
        </td>
    </tr>
    <%
        }
    %>
</table>
</body>
</html>

