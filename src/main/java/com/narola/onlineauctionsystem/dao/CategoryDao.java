package com.narola.onlineauctionsystem.dao;

import com.narola.onlineauctionsystem.model.ProductCategory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDao {
    public static List<ProductCategory> showCategory() {
        List<ProductCategory> productCategories = new ArrayList<>();
        try {
            String query = "select * from product_category";
            PreparedStatement stmt4 = DbConnection.getInstance().getConnection().prepareStatement(query);
            ResultSet resultSet1 = stmt4.executeQuery();
            while (resultSet1.next()) {
                ProductCategory productCategory = new ProductCategory();
                productCategory.setProductCategoryId(resultSet1.getInt(1));
                productCategory.setCategoryName(resultSet1.getString(2));
                productCategories.add(productCategory);
//                Integer productCategoryId = resultSet1.getInt("product_category_id");
//                String categoryName = resultSet1.getString("category_name");
//                ProductCategory productCategory = new ProductCategory(productCategoryId, categoryName);
//                productCategories.add(productCategory);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return productCategories;
    }
}
