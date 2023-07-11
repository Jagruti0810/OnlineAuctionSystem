<%@ page import="com.narola.onlineauctionsystem.model.ProductCategory" %>
<%@ page import="java.util.List" %>
<%@ page import="com.narola.onlineauctionsystem.exception.Error" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Add Product</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f5f5f5;
            padding: 20px;
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

        .container {
            max-width: 400px;
            margin: 0 auto;
            background-color: #fff;
            border-radius: 5px;
            padding: 20px;
            box-shadow: 0px 2px 10px rgba(0, 0, 0, 0.1);
        }

        .form-group {
            margin-bottom: 20px;
            display: block;
            font-weight: bold;
            margin-bottom: 10px;
        }

        .form-label {
            display: block;
            font-weight: bold;
            margin-bottom: 5px;
        }

        .form-input {
            width: 100%;
            padding: 10px;
            border-radius: 5px;
            border: 1px solid #ccc;
            box-sizing: border-box;
            margin-bottom: 10px;
        }

        .errorList {
            color: #ff0000;
            margin-bottom: 10px;
        }

    </style>
</head>
<body>
<div class="container">
    <h2>Add Product</h2>
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
    <form action="addProduct" method="post" enctype="multipart/form-data">

        <label class="form-label" for="product-name">Product Name:</label>
        <input class="form-input" type="text" id="product-name" name="product-name">

        <div class="form-group">
            <label for="categoryId">Category:</label>
            <select name="categoryId" id="categoryId">
                <option disabled selected>Select your Category</option>
                <%
                    List<ProductCategory> productCategoryList = (List<ProductCategory>) request.getAttribute("ProductCategory");
                    for (ProductCategory productCategory : productCategoryList) {
                %>
                <option value="<%= productCategory.getProductCategoryId() %>"><%= productCategory.getCategoryName() %>
                </option>
                <% } %>
            </select>
        </div>
        <label class="form-label" for="image">Image:</label>
        <input type="file" id="image" name="image"><br>
        <img id="preview" src="#" alt="Selected Image" style="display: none; max-width: 200px; max-height: 200px;">
        <script>
            const fileInput = document.getElementById('image');
            const imagePreview = document.getElementById('preview');
            fileInput.addEventListener('change', function(event) {
                const selectedFile = event.target.files[0];
                if (selectedFile) {
                    const reader = new FileReader();
                    reader.onload = function(event) {
                        imagePreview.src = event.target.result;
                        imagePreview.style.display = 'block';
                    };
                    reader.readAsDataURL(selectedFile);
                }
            });
        </script>

        <%--        <label class="form-label" for="image">Image:</label>--%>
        <%--        <input type="file" name="image" id="image">--%>

        <%--        <% String uploadedImagePath = (String) request.getAttribute("uploadedImagePath");--%>
        <%--            if (uploadedImagePath != null) { %>--%>
        <%--        <img class="uploaded-image" src="<%= uploadedImagePath %>" alt="Uploaded Image">--%>
        <%--        <% } %>--%>

        <%--        <img src="${uploadDirectory}" alt="Uploaded Image">--%>
        <label class="form-label" for="product-desc">Description:</label>
        <textarea class="form-input" id="product-desc" name="product-desc" rows="4"></textarea>

        <input type="submit" value="Add">
    </form>
</div>
</body>
</html>


