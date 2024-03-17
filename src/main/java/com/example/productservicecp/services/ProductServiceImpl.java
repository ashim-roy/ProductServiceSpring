package com.example.productservicecp.services;

import com.example.productservicecp.models.Product;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("SelfProductService")
public class ProductServiceImpl implements ProductService{
    @Override
    public Product getProductByID(Long id) {

        return null; // null for now
    }
    @Override
    public List<Product> getAllProducts() {
        return null;
    }

    @Override
    public Product deleteProductById(Long id) {
        return null;
    }
    @Override
    public Product addProduct(Product product){
        return null;
    }
    @Override
    public void updateProductById() {
    }
}
