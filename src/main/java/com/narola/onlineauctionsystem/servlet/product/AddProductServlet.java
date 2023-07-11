package com.narola.onlineauctionsystem.servlet.product;

import com.narola.onlineauctionsystem.dao.CategoryDao;
import com.narola.onlineauctionsystem.dto.ProductDTO;
import com.narola.onlineauctionsystem.exception.DaoException;
import com.narola.onlineauctionsystem.exception.Error;
import com.narola.onlineauctionsystem.model.ProductCategory;
import com.narola.onlineauctionsystem.model.User;
import com.narola.onlineauctionsystem.service.ProductService;
import com.narola.onlineauctionsystem.validation.ProductValidation;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;
import java.io.*;
import java.util.List;

@MultipartConfig(fileSizeThreshold = 4096 * 4096,
        maxFileSize = 4096 * 4096 * 5,
        maxRequestSize = 4096 * 4096 * 5 * 5)
public class AddProductServlet extends HttpServlet {
    private ProductDTO productDTO = new ProductDTO();
    private ProductService productService = new ProductService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        fillProductMasterData(req);
        req.getRequestDispatcher("addProduct.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        productDTO.setProductName(req.getParameter("product-name"));
        productDTO.setDescription(req.getParameter("product-desc"));
        productDTO.setCategoryId(req.getParameter("categoryId"));
        fillProductMasterData(req);
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        int sellerId = user.getUserId();
        productDTO.setSellerId(sellerId);
        Part filePart = req.getPart("image");
        ServletContext servletContext = getServletContext();
        String fileName = getFileName(filePart);
        String imagePath = uploadFileAndGetImagePath(filePart, fileName, servletContext);
        productDTO.setImage(imagePath);
        List<Error> errorList = ProductValidation.validateProductCredentials(productDTO);
        if (!errorList.isEmpty()) {
            fillProductMasterData(req);
            req.setAttribute("errorList", errorList);
            req.getRequestDispatcher("addProduct.jsp").forward(req, resp);
            return;
        }
//        productDTO.setImage(imagePath);
        try {
            productService.createProduct(productDTO, sellerId);
            req.getRequestDispatcher("seller.jsp").forward(req, resp);
        } catch (DaoException e) {
            e.printStackTrace();
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

    private String uploadFileAndGetImagePath(Part filePart, String fileName, ServletContext servletContext) throws IOException {
        String basePath = servletContext.getRealPath("/");
        String savePath = basePath + "static/" + fileName;
        File outputFile = new File(savePath);
        InputStream fileContent = filePart.getInputStream();
        OutputStream fileOutputStream = new FileOutputStream(outputFile);
        int read;
        byte[] buffer = new byte[4096];
        while ((read = fileContent.read(buffer)) != -1) {
            fileOutputStream.write(buffer, 0, read);
        }
        fileContent.close();
        fileOutputStream.close();
        String imagePath = "static/" + fileName;
        return imagePath;
    }
}
//        String uploadPath = servletContext.getRealPath("") + File.separator + UPLOAD_DIRECTORY;
//        String filePath = uploadPath + File.separator + fileName;
//
//        try (InputStream inputStream = part.getInputStream();
//             OutputStream outputStream = new FileOutputStream(filePath)) {
//
//            byte[] buffer = new byte[1024];
//            int bytesRead;
//            while ((bytesRead = inputStream.read(buffer)) != -1) {
//                outputStream.write(buffer, 0, bytesRead);
//            }
//        }
//        return filePath;
