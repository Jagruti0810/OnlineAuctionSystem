package com.narola.onlineauctionsystem.model;

import java.util.Comparator;

public class ProductComparator implements Comparator<Product> {
    public int compare(Product name1, Product name2) {
        return name1.getProductName().compareTo(name2.getProductName());
    }
}
