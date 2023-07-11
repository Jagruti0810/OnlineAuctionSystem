<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Search Results</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f5f5f5;
            padding: 20px;
        }

        .container {
            max-width: 800px;
            margin: 0 auto;
            padding: 20px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
        }

        th, td {
            padding: 10px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }

        th {
            background-color: #f2f2f2;
        }

        .container {
            max-width: 800px;
            margin: 0 auto;
            padding: 20px;
        }

        input[type="submit"] {
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

        .auction-box {
            background-color: #f2f2f2;
            border-radius: 5px;
            padding: 10px;
        }

        .auction-box-header h3 {
            margin: 0;
        }

        .auction-box-content {
            padding: 10px;
            display: inline-block;
        }

        label {
            display: block;
            font-weight: bold;
            margin-bottom: 5px;
        }

        .error-message {
            color: #cc0000;
            font-size: 14px;
            margin-top: 10px;
        }

        button[type="submit"] {
            width: 100px;
            height: 40px;
            background-color: #4CAF50;
            color: #ffffff;
            border: none;
            border-radius: 3px;
            cursor: pointer;
        }
    </style>
</head>
<body>
<div class="container">
    <h2>Auction Details</h2>
    <% String errorMessage = (String) request.getAttribute("errorMessage"); %>
    <% if (errorMessage != null && !errorMessage.isEmpty()) { %>
    <p class="error-message"><%= errorMessage %>
    </p>
    <% } %>

    <div class="auction-box">
        <div class="auction-box-content">
            <input type="hidden" name="auctionId" value="${auction.getAuctionId()}">
            <label><strong>Auction Id:</strong></label>
            <span>${auction.getAuctionId()}</span>
            <br>

            <label><strong>Auction Title:</strong></label>
            <span>${auction.getTitle()}</span>
            <br>

            <label><strong>Product Name:</strong></label>
            <span>${auction.getProductName()}</span>
            <br>

            <label><strong>Auction minimum bid price:</strong></label>
            <span>${auction.getMinimumBidPrice()}</span>
            <br>

            <label><strong>Auction start date:</strong></label>
            <span>${auction.getStartDate()}</span>
            <br>

            <label><strong>Auction end date:</strong></label>
            <span>${auction.getEndDate()}</span>
            <br>

        </div>
        <%--        <div class="form-group">--%>
        <%--                <p>--%>
        <%--                    <% if (request.getAttribute("existingBid") != null) { %>--%>
        <%--                    Your bid: <span id="existing-bid"><%= ((Bid) request.getAttribute("existingBid")).getAmount() %></span>--%>
        <%--                    <% } else { %>--%>
        <%--                    Current highest bid: <span id="highest-bid">0</span>--%>
        <%--                    <% } %>--%>
        <%--                </p>--%>
        <%--            <%--%>
        <%--                String hasBidAlready = (String) request.getAttribute("hasBidAlready");--%>
        <%--                boolean disableBidButton = hasBidAlready.equals("yes");--%>
        <%--            %>--%>
        <%--            <form action="auctionDetails" method="post">--%>
        <%--                <label for="amount">Enter bid amount:</label>--%>
        <%--                <input type="text" id="amount" name="amount">--%>
        <%--                <input type="hidden" name="id" value="${auction.auctionId}">--%>
        <%--                <button id="bidButton" <% if (disableBidButton) { %>disabled <% } %>>Bid</button>--%>

        <%--                <% if (disableBidButton) { %>--%>
        <%--                <script>--%>
        <%--                    document.getElementById("bidButton").disabled = true;--%>
        <%--                </script>--%>
        <%--                <% } %>--%>

        <%--            </form>--%>
        <%--            <% Bid bid = (Bid) request.getAttribute("existingBid"); %>--%>
        <%--            <% Integer bidderId = (Integer) request.getSession().getAttribute("bidderId"); %>--%>
        <%--            <% boolean hasPlacedBid = bid != null && bid.getUserId() == bidderId; %>--%>
        <%--            <div class="form-group">--%>
        <%--                <p>--%>
        <%--                    <% if (request.getAttribute("existingBid") != null) { %>--%>
        <%--                    Your bid: <span id="existing-bid"><%= ((Bid) request.getAttribute("existingBid")).getAmount() %></span>--%>
        <%--                    <% } else { %>--%>
        <%--                    Current highest bid: <span id="highest-bid">0</span>--%>
        <%--                    <% } %>--%>
        <%--                </p>--%>
        <%--                <form action="auctionDetails" method="post">--%>
        <%--                    <label for="amount">Enter bid amount:</label>--%>
        <%--                    <input type="text" id="amount" name="amount">--%>
        <%--                    <input type="hidden" name="id" value="${auction.auctionId}">--%>
        <%--                    <button id="bidButton" onclick="bid()">Bid</button>--%>
        <%--                </form>--%>
        <%--                <p id="error-message"></p>--%>
        <%--            </div>--%>


        <c:if test="${not empty existingBid}">
            <h3>Your bid amount: ${existingBid.amount}</h3>
        </c:if>
        <c:if test="${showBidButton}">
            <form action="auctionDetails" method="post">
                <input type="hidden" name="id" value="${auction.auctionId}">
                <label for="amount">Enter bid amount:</label>
                <input type="text" name="amount" id="amount">
                <c:set var="disabled" value="${empty existingBid ? '' : 'disabled'}"/>
                <input type="submit" value="Place Bid" ${disabled}>
            </form>
        </c:if>
        <c:if test="${!showBidButton}">
        </c:if>

        <form action="viewBid">
            <input type="hidden" name="id" value="${auction.auctionId}">
            <button type="submit" class="button">Bid List</button>
        </form>

        <%--            <script>--%>
        <%--                var hasPlacedBid = <%= hasPlacedBid %>;--%>

        <%--                // Get the bid button element--%>
        <%--                var bidButton = document.getElementById("bidButton");--%>

        <%--                // Function to handle the bid action--%>
        <%--                function bid() {--%>
        <%--                    // Check if the bidder has already placed a bid--%>
        <%--                    if (hasPlacedBid) {--%>
        <%--                        // Display an error message--%>
        <%--                        var errorMessage = document.getElementById("error-message");--%>
        <%--                        errorMessage.textContent = "You have already placed a bid.";--%>

        <%--                        // Prevent form submission--%>
        <%--                        event.preventDefault();--%>
        <%--                    }--%>
        <%--                }--%>

        <%--                // Disable the bid button if the bidder has already placed a bid--%>
        <%--                if (hasPlacedBid) {--%>
        <%--                    bidButton.disabled = true;--%>
        <%--                }--%>
        <%--            </script>--%>


        <%--            <div class="form-group">--%>
        <%--                <p id="bid-amount">Bid Amount: </p>--%>
        <%--                <form action="auctionDetails" method="post">--%>
        <%--                    <input type="hidden" name="action" value="auctionDetails">--%>
        <%--                    <label for="amount">Enter bid amount:</label>--%>
        <%--                    <input type="text" id="amount" name="amount">--%>
        <%--                    <input type="hidden" name="id" value="${auction.auctionId}">--%>
        <%--                    <button type="submit" id="bid-button">Place Bid</button>--%>
        <%--                </form>--%>
        <%--                <p id="error-message"></p>--%>
        <%--            </div>--%>


        <%--            <%--%>
        <%--                // Retrieve the existing bid from the request attribute--%>
        <%--                Bid bid = (Bid) request.getAttribute("Bid");--%>

        <%--                // Retrieve the bidder ID from the session--%>
        <%--                int bidderId = (int) request.getAttribute("bidderId");--%>

        <%--                // Check if the bidder has already placed a bid--%>
        <%--                boolean hasPlacedBid = bid != null && bid.getUserId() == bidderId;--%>
        <%--            %>--%>

        <%--            <div class="form-group">--%>
        <%--                <% if (hasPlacedBid) { %>--%>
        <%--                <p>Your Bid Amount: <%= bid.getAmount() %></p>--%>
        <%--                <% } else { %>--%>
        <%--                <p>Current Highest Bid: <%= bid.getAmount() %></p>--%>
        <%--                <% } %>--%>

        <%--                <form action="auctionDetails" method="post">--%>
        <%--                    <label for="amount">Enter bid amount:</label>--%>
        <%--                    <input type="text" id="amount" name="amount" <% if (hasPlacedBid) { %>disabled<% } %>>--%>
        <%--                    <input type="hidden" name="auctionId" value="${auction.auctionId}">--%>
        <%--                    <input type="hidden" name="bidderId" value="<%= bidderId %>">--%>
        <%--                    <button type="submit" <% if (hasPlacedBid) { %>disabled<% } %>>Place Bid</button>--%>
        <%--                </form>--%>
        <%--                <% if (!hasPlacedBid) { %>--%>
        <%--                <p id="error-message"></p>--%>
        <%--                <% } %>--%>
        <%--            </div>--%>

    </div>
</div>
</div>
</body>
</html>

