package com.narola.onlineauctionsystem.model;

import java.util.Comparator;

public class ProductComparatorForDesc implements Comparator<Product> {
    public int compare(Product name1, Product name2) {
        return name2.getProductName().compareTo(name1.getProductName());
    }
}
