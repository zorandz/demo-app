package com.zoran.demo.services;

import java.io.IOException;
import java.math.BigDecimal;

import com.zoran.demo.entities.Product;
import com.zoran.demo.entities.ProductCategory;

public interface ProductService {

    void addNewProduct(ProductCategory categoryId, String sku, String name, String description, BigDecimal unitPrice, String imageUrl, boolean active, Integer unitsInStock);
    
    void deleteProduct(Long id);

}