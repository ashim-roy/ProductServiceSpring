package com.example.productservicecp.repository;

import com.example.productservicecp.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository   // not mendatory. as JPArepo takes care of it. its a stereotype annotation. it is used to indicate that the class provides the mechanism for storage, retrieval, search, update and delete operation on objects.
public interface ProductRepo extends JpaRepository<Product, Long>{
    Optional<Product> findById(Long id); // this is the method that will be used in the service class to get the product by id. optional means it can return null if the product is not found
}
