<%@ page import="java.util.List" %>
<%@ page import="com.narola.onlineauctionsystem.model.Product" %>
<%@ page import="com.narola.onlineauctionsystem.model.ProductCategory" %>
<%@ page import="com.narola.onlineauctionsystem.exception.Error" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Edit Product</title>
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
    <h2>Edit Product</h2>
    <div class="errorList">
        <% List<Error> errorList = (List<Error>) request.getAttribute("errorList");
            if (errorList != null && !errorList.isEmpty()) { %>
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
    <form action="editProduct" method="post" enctype="multipart/form-data">
        <input type="hidden" name="productId" value="${product.getProductId()}">

        <label class="form-label" for="product-name">Product Name:</label>
        <input class="form-input" type="text" id="product-name" name="product-name" value="${product.getProductName()}">

        <div class="form-group">
            <label for="categoryId">Category:</label>
            <select name="categoryId" id="categoryId">
                <% List<ProductCategory> productCategories = (List<ProductCategory>) request.getAttribute("ProductCategory");
                    Integer categoryId = (Integer) request.getAttribute("categoryId");

                    if (productCategories != null) {
                        for (ProductCategory productCategory : productCategories) {
                            String selected = "";
                            if (categoryId != null && productCategory.getProductCategoryId() == categoryId) {
                                selected = "selected";
                            }
                %>
                <option value="<%= productCategory.getProductCategoryId() %>" <%= selected %>><%= productCategory.getCategoryName() %>
                </option>
                <% }
                }
                %>
            </select>
        </div>

        <div class="form-group">
            <label for="image">Existing Image:</label>
            <img src="${product.getImage()}" alt="Existing Image" width="200" height="200" id="image">
        </div>

        <label class="form-label" for="newImage">Image:</label>
        <input type="file" id="newImage" name="newImage">

        <img id="preview" src="#" style="display: none; max-width: 200px; max-height: 200px;">

        <script>
            const existingImage = document.getElementById('image');
            const fileInput = document.getElementById('newImage');
            const imagePreview = document.getElementById('preview');

            // Show the existing image initially
            existingImage.style.display = 'block';

            fileInput.addEventListener('change', function(event) {
                const selectedFile = event.target.files[0];
                if (selectedFile) {
                    const reader = new FileReader();
                    reader.onload = function(event) {
                        // Hide the existing image and display the new image preview
                        existingImage.style.display = 'none';
                        imagePreview.src = event.target.result;
                        imagePreview.style.display = 'block';
                    };
                    reader.readAsDataURL(selectedFile);
                } else {
                    // Show the existing image again when no new image is selected
                    existingImage.style.display = 'block';
                    imagePreview.src = '#';
                    imagePreview.style.display = 'none';
                }
            });
        </script>



        <label class="form-label" for="product-desc">Description:</label>
        <textarea class="form-input" id="product-desc" name="product-desc"
                  rows="4">${product.getDescription()}</textarea>

        <input type="submit" value="Update">
    </form>
</div>
</body>
</html>