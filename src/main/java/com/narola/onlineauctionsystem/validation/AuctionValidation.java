package com.narola.onlineauctionsystem.validation;

import com.narola.onlineauctionsystem.dto.AuctionDTO;
import com.narola.onlineauctionsystem.exception.Error;

import java.util.ArrayList;
import java.util.List;

public class AuctionValidation {
    public static List<Error> validateAuctionCredentials(AuctionDTO auctionDTO) {
        List<Error> errorList = new ArrayList<>();
        if (Validation.isEmpty(auctionDTO.getTitle())) {
            errorList.add(new Error("Please enter auction title"));
        }
        if (auctionDTO.getProductId() == null || auctionDTO.getProductId().isEmpty()) {
            errorList.add(new Error("Please select a product"));
        }
        if (Validation.isEmpty(auctionDTO.getMinimumBidPrice())) {
            errorList.add(new Error("Please enter minimum bid price"));
        }
        if (Validation.isEmpty(auctionDTO.getStartDate())) {
            errorList.add(new Error("Please enter auction start date"));
        }
        if (Validation.isEmpty(auctionDTO.getEndDate())) {
            errorList.add(new Error("Please enter auction end date"));
        }
        return errorList;
    }
}
