package com.narola.onlineauctionsystem.validation;

import com.narola.onlineauctionsystem.dto.ProductDTO;
import com.narola.onlineauctionsystem.exception.Error;

import java.util.ArrayList;
import java.util.List;

public class ProductValidation {
    public static List<Error> validateProductCredentials(ProductDTO productDTO) {
        List<Error> errorList = new ArrayList<>();
        if (Validation.isEmpty(productDTO.getProductName())) {
            errorList.add(new Error("Please enter Product name"));
        }
        if (productDTO.getCategoryId() == null || productDTO.getCategoryId().isEmpty()) {
            errorList.add(new Error("Please select a category"));
        }
        if (Validation.isEmpty(productDTO.getImage())) {
            errorList.add(new Error("Please select a image"));
        }
        if (Validation.isEmpty(productDTO.getDescription())) {
            errorList.add(new Error("Please enter description"));
        }
        return errorList;
    }
}
