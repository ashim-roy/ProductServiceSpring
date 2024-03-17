package com.example.productservicecp.services;

import com.example.productservicecp.exceptions.ProductNotFoundException;
import com.example.productservicecp.models.Product;

import java.util.List;

public interface ProductService {
    Product getProductByID(Long id) throws ProductNotFoundException;
    List<Product> getAllProducts();

    Product deleteProductById(Long id);
    Product addProduct(Product product);
    void updateProductById();
}
