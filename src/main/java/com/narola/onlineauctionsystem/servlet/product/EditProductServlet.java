package com.narola.onlineauctionsystem.servlet.product;

import com.narola.onlineauctionsystem.dao.CategoryDao;
import com.narola.onlineauctionsystem.dto.ProductDTO;
import com.narola.onlineauctionsystem.exception.Error;
import com.narola.onlineauctionsystem.model.Product;
import com.narola.onlineauctionsystem.model.ProductCategory;
import com.narola.onlineauctionsystem.model.User;
import com.narola.onlineauctionsystem.service.ProductService;
import com.narola.onlineauctionsystem.validation.ProductValidation;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;
import java.io.*;
import java.util.List;

@MultipartConfig(fileSizeThreshold = 4096 * 4096,
        maxFileSize = 4096 * 4096 * 5,
        maxRequestSize = 4096 * 4096 * 5 * 5)
public class EditProductServlet extends HttpServlet {
    private ProductService productService = new ProductService();
    private ProductDTO productDTO = new ProductDTO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String productId = req.getParameter("id");
        Product product = productService.getProductById(productId);
        if (product != null) {
            req.setAttribute("product", product);
            req.setAttribute("categoryId", product.getCategoryId());
            fillProductMasterData(req);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("editProduct.jsp");
            requestDispatcher.forward(req, resp);
        }
    }
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        fillProductMasterData(req);
//        RequestDispatcher requestDispatcher = req.getRequestDispatcher("editProduct.jsp");
//        requestDispatcher.forward(req, resp);
//    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String productId = req.getParameter("productId");
        productDTO.setProductId(productId);
//        productDTO.setProductId(req.getParameter("productId"));
        productDTO.setProductName(req.getParameter("product-name"));
        productDTO.setDescription(req.getParameter("product-desc"));
        productDTO.setCategoryId(req.getParameter("categoryId"));
        Part filePart = req.getPart("newImage");
        ServletContext servletContext = getServletContext();
        String fileName = getFileName(filePart);
        String imagePath = uploadFileAndGetImagePath(filePart, fileName);
        productDTO.setImage(imagePath);
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        int sellerId = user.getUserId();
        productDTO.setSellerId(sellerId);
        List<Error> errorList = ProductValidation.validateProductCredentials(productDTO);
        if (!errorList.isEmpty()) {
            fillProductMasterData(req);
            req.setAttribute("errorList", errorList);
            req.setAttribute("product", productDTO);
            RequestDispatcher requestDispatcher = req.getRequestDispatcher("editProduct.jsp");
            requestDispatcher.forward(req, resp);
            return;
        } else {
            productService.editProduct(productDTO);
//            resp.sendRedirect(req.getContextPath() + "/editProduct?id=" + productId);
            resp.sendRedirect(req.getContextPath() + "/viewProduct");
        }
    }

    private void fillProductMasterData(HttpServletRequest request) {
        CategoryDao categoryDao = new CategoryDao();
        List<ProductCategory> productCategories = categoryDao.showCategory();
        request.setAttribute("ProductCategory", productCategories);
    }

    private String getFileName(Part part) {
        String contentDisposition = part.getHeader("content-disposition");
        String[] elements = contentDisposition.split(";");
        for (String element : elements) {
            if (element.trim().startsWith("filename")) {
                return element.substring(element.indexOf('=') + 1).trim()
                        .replace("\"", "");
            }
        }
        return null;
    }

    private String uploadFileAndGetImagePath(Part filePart, String fileName) throws IOException {
        String directoryPath = "static/";

        // Create the directory if it doesn't exist
        File directory = new File(directoryPath);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        String filePath = directoryPath + File.separator + fileName;

        try (InputStream inputStream = filePart.getInputStream();
             FileOutputStream outputStream = new FileOutputStream(filePath)) {
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
        }
        return filePath;
    }
}