package com.example.productservicecp.services;

import com.example.productservicecp.exceptions.ProductNotFoundException;
import com.example.productservicecp.models.Category;
import com.example.productservicecp.models.Product;
import com.example.productservicecp.repository.CategoryRepo;
import com.example.productservicecp.repository.ProductRepo;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service("SelfProductService") // this is the name of the service that will be used in the controller to autowire it in the controller class with qualifier annotation
public class ProductServiceImpl implements ProductService{
    private ProductRepo productRepo;
    private CategoryRepo categoryRepo;

    public ProductServiceImpl(ProductRepo productRepo, CategoryRepo categoryRepo){
        this.productRepo = productRepo;
        this.categoryRepo = categoryRepo;
    }

    @Override
    public Product getProductByID(Long id) { // here i dont need to mention exception because i am using optional
        //return null; // null for now

        Optional<Product> product = this.productRepo.findById(id);     // this is the method that will be used in the service class to get the product by id. optional means it can return null if the product is not found
        //return product.orElse(null); // this will return the product if it is found or null if it is not found
        //or other way to do it is to use the get method of the optional class

        Category category = product.get().getCategory();
        if(product.isPresent()){
            Category category1 = product.get().getCategory();
        }
        return product.get(); // this will return the product if it is found or throw an exception if it is not found
        /*
        if we hadnt use optional code would look like:
        Product product = this.productrepo.findById(id);
        Category category = product.getCategory();
         */
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
    public Product addProduct(Product newproduct) {
        //this.productRepo.save(product); // the save function already returning me the product that is saved in the database. how can i say its returning a product? because the return type of the save function is the same as the type of the object that is being saved. so it is returning the object that is being saved. also it is returning the object that is saved in the database. so it is returning the object that is saved in the database.
        Optional<Category> categoryOptional = this.categoryRepo.findByName(newproduct.getCategory().getName());
        if (categoryOptional.isPresent()) {
            newproduct.setCategory(categoryOptional.get());
        }else{ // if category is not present.
            Category category = categoryRepo.save(newproduct.getCategory());
            newproduct.setCategory(category);
        }
        // next we need to do is in optional it present or not.. if category is present just set the category with ID. Else I need to save that category..
        // if category is not present then I need to save that category and then set the category with the category that is saved in the database.
        // if category is present then I need to set the category with the category that is present in the database.
        return this.productRepo.save(newproduct);
    }


    @Override
    public Product updateProductById(Long id, Product productDetails) throws ProductNotFoundException {
        Optional<Product> productOptional = productRepo.findById(id);
        if (!productOptional.isPresent()) {
            // Handle product not found
            throw new ProductNotFoundException("Product not found with id: " + id);
        }

        Product product = productOptional.get();
        product.setTitle(productDetails.getTitle());
        product.setDescription(productDetails.getDescription());
        product.setPrice(productDetails.getPrice());

        Optional<Category> categoryOptional = categoryRepo.findByName(productDetails.getCategory().getName());
        if (categoryOptional.isPresent()) {
            product.setCategory(categoryOptional.get());
        } else {
            Category category = categoryRepo.save(productDetails.getCategory());
            product.setCategory(category);
        }

        return productRepo.save(product);
    }
}
