package com.narola.onlineauctionsystem.service;

import com.narola.onlineauctionsystem.dao.ProductDao;
import com.narola.onlineauctionsystem.dto.ProductDTO;
import com.narola.onlineauctionsystem.exception.DaoException;
import com.narola.onlineauctionsystem.model.Product;

public class ProductService {
    private static ProductDao productDao = new ProductDao();

    public static void createProduct(ProductDTO productDTO, int sellerId) throws DaoException {
        productDao.addProductDetails(productDTO, sellerId);
    }

    public static void deleteProduct(int productId) throws DaoException {
        productDao.removeProduct(productId);
    }

    //    public static void editProduct(Product product)
//    {
//        productDao.editProductDetails(product);
//    }
    public Product getProductById(String productId) {
        return productDao.getProductById(productId);
    }

    public void editProduct(ProductDTO productDTO) {
        productDao.editProductDetails(productDTO);
    }
//    public List<Product> getSortedProducts(String sortOption, int sellerId) {
////        List<Product> productList = productDao.showProducts(sellerId);
//        if (sortOption.equals("asc")) {
//            productDao.getAllProducts(sellerId);
////            Collections.sort(productList, Comparator.comparing(Product::getProductName));
//        } else if (sortOption.equals("desc")) {
//            productDao.getAllProductsDesc(sellerId);
////            Collections.sort(productList, Comparator.comparing(Product::getProductName).reversed());
//        }
//    }
}
