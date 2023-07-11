package com.narola.onlineauctionsystem.dao;

import com.narola.onlineauctionsystem.dto.ProductDTO;
import com.narola.onlineauctionsystem.exception.DaoException;
import com.narola.onlineauctionsystem.model.Product;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDao {
    public Product addProductDetails(ProductDTO productDTO, int sellerId) throws DaoException {
        Product product = null;
        try {
            String query3 = "insert into product (product_name, description, seller_id, product_category_id, image) " +
                    "values (?, ?, ?, ?, ?)";
            PreparedStatement stmt2 = DbConnection.getInstance().getConnection().prepareStatement(query3);
            stmt2.setString(1, productDTO.getProductName());
            stmt2.setString(2, productDTO.getDescription());
            stmt2.setInt(3, sellerId);
            stmt2.setString(4, productDTO.getCategoryId());
            stmt2.setString(5, productDTO.getImage());
            stmt2.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("oops something went wrong", e);
        } catch (Exception e) {
            throw new DaoException("oops something went wrong", e);
        }
        return product;
    }

    public List<Product> showProducts(int seller_id) {
        List<Product> products = new ArrayList<>();
        try {
            String query = "SELECT p.product_id, p.product_name, p.description, pc.category_name, p.image FROM product p JOIN product_category pc ON p.product_category_id = pc.product_category_id WHERE p.seller_id = ?";
            PreparedStatement stmt4 = DbConnection.getInstance().getConnection().prepareStatement(query);
            stmt4.setInt(1, seller_id);
            ResultSet resultSet1 = stmt4.executeQuery();
            while (resultSet1.next()) {
                Integer productId = resultSet1.getInt("product_id");
                String productName = resultSet1.getString("product_name");
                String description = resultSet1.getString("description");
                String categoryName = resultSet1.getString("category_name");
                String image = resultSet1.getString("image");
                Product product = new Product(productId, productName, description, categoryName, image);
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return products;
    }

    //    public List<Product> showProducts() {
//        List<Product> products = new ArrayList<>();
//        try {
//            String query = "select * from product";
//            PreparedStatement stmt4 = DbConnection.getInstance().getConnection().prepareStatement(query);
//            ResultSet resultSet1 = stmt4.executeQuery();
//            while (resultSet1.next()) {
//                Integer productId = resultSet1.getInt("product_id");
//                String productName = resultSet1.getString("product_name");
//                String description = resultSet1.getString("description");
//                Integer categoryId = resultSet1.getInt("product_category_id");
//                Product product = new Product(productId, productName, description, categoryId);
//                products.add(product);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return products;
//    }
    public List<Product> showProductsByCredentials(int seller_id) {
        List<Product> products = new ArrayList<>();
        try {
            String query = "select * from product where seller_id = ?";
            PreparedStatement stmt4 = DbConnection.getInstance().getConnection().prepareStatement(query);
            stmt4.setInt(1, seller_id);
            ResultSet resultSet1 = stmt4.executeQuery();
            while (resultSet1.next()) {
                Integer productId = resultSet1.getInt("product_id");
                String productName = resultSet1.getString("product_name");
                Product product = new Product(productId, productName);
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return products;
    }

    public void editProductDetails(ProductDTO productDTO) {
        try {
            String sql1 = "UPDATE product SET product_name = ?, description = ?, product_category_id= ?, image = ?  where product_id = ?";
            PreparedStatement ps1 = DbConnection.getInstance().getConnection().prepareStatement(sql1);
            ps1.setString(1, productDTO.getProductName());
            ps1.setString(2, productDTO.getDescription());
            ps1.setString(3, String.valueOf(productDTO.getCategoryId()));
            ps1.setString(4, productDTO.getImage());
            ps1.setString(5, productDTO.getProductId());
            ps1.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //    public void removeProduct(ProductDTO productDTO) throws DaoException {
//        try {
//            String sql2 = "DELETE FROM product where product_id= ?";
//            PreparedStatement ps2 = DbConnection.getInstance().getConnection().prepareStatement(sql2);
//            ps2.setInt(1, productDTO.getProductId());
//            ps2.executeUpdate();
//        } catch (SQLException e) {
//            throw new DaoException("oops something went wrong", e);
//        } catch (Exception e) {
//            throw new DaoException("oops something went wrong", e);
//        }
//    }
    public void removeProduct(int productId) throws DaoException {
        try {
            String sql = "DELETE FROM product WHERE product_id = ?";
            PreparedStatement ps2 = DbConnection.getInstance().getConnection().prepareStatement(sql);
            ps2.setInt(1, productId);
            ps2.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Oops! Something went wrong while deleting the product.", e);
        }
    }

    public Product getProductById(String productId) {
        Product product = null;
        try {
            String query = "SELECT * FROM product WHERE product_id = ?";
            PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement(query);
            preparedStatement.setString(1, productId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                product = new Product();
                product.setProductId(resultSet.getInt(1));
                product.setProductName(resultSet.getString(2));
                product.setCategoryId(resultSet.getInt(3));
                product.setDescription(resultSet.getString(4));
                product.setSellerId(resultSet.getInt(5));
                product.setImage(resultSet.getString(6));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return product;
    }

    public List<Product> getAllProducts(int seller_id) {
        List<Product> productList = new ArrayList<>();
        try {
            String sql = "SELECT p.product_id, p.product_name, p.description, pc.category_name, p.image FROM product p JOIN product_category pc ON p.product_category_id = pc.product_category_id WHERE p.seller_id = ? ORDER BY product_name asc";
            PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement(sql);
            preparedStatement.setInt(1, seller_id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int productId = resultSet.getInt("product_id");
                String productName = resultSet.getString("product_name");
                String description = resultSet.getString("description");
                String categoryName = resultSet.getString("category_name");
                String image = resultSet.getString("image");
                Product product = new Product(productId, productName, description, categoryName, image);
                productList.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productList;
    }

    public List<Product> getAllProductsDesc(int sellerId) {
        List<Product> productList = new ArrayList<>();
        try {
            String sql = "SELECT p.product_id, p.product_name, p.description, pc.category_name, p.image FROM product p JOIN product_category pc ON p.product_category_id = pc.product_category_id WHERE p.seller_id = ? ORDER BY product_name desc";
            PreparedStatement preparedStatement = DbConnection.getInstance().getConnection().prepareStatement(sql);
            preparedStatement.setInt(1, sellerId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int productId = resultSet.getInt("product_id");
                String productName = resultSet.getString("product_name");
                String description = resultSet.getString("description");
                String categoryName = resultSet.getString("category_name");
                String image = resultSet.getString("image");
                Product product = new Product(productId, productName, description, categoryName, image);
                productList.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productList;
    }
}
