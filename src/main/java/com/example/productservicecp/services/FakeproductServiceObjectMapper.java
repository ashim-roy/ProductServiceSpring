//package com.example.productservicejanbatch.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
/*
public class FakeproductServiceObjectMapper implements ProductService{
    private RestTemplateBuilder restTemplateBuilder;
    private String getProductURL = "https://fakestoreapi.com/products/1";
    private String genericProductUrl = "https://fakestoreapi.com/products";

    private final ObjectMapper objectMapper;

    @Autowired
    public FakeproductServiceObjectMapper(RestTemplateBuilder restTemplateBuilder, ObjectMapper objectMapper) {
        this.restTemplateBuilder = restTemplateBuilder;
        this.objectMapper = objectMapper;
    }

    @Override
    public Product getProductByID(Long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> responseEntity = restTemplate.getForEntity(getProductURL, FakeStoreProductDto.class);
        return getProductFromFakeSToreProductDto(responseEntity.getBody());
    }

    @Override
    public List<Product> getAllProducts() {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto[]> responseEntity =
                restTemplate.getForEntity(genericProductUrl, FakeStoreProductDto[].class);
        List<Product> productList = new LinkedList<>();
        for (FakeStoreProductDto fakeStoreProductDto : responseEntity.getBody()) {
            productList.add(getProductFromFakeSToreProductDto(fakeStoreProductDto));
        }
        return productList;
    }

    // Convert DTO to product using ObjectMapper
    private Product getProductFromFakeSToreProductDto(FakeStoreProductDto fakeStoreProductDto) {
        return objectMapper.convertValue(fakeStoreProductDto, Product.class);
    }

    @Override
    public void deleteProductById() {
        // Implementation
    }

    @Override
    public void addProduct() {
        // Implementation
    }

    @Override
    public void updateProductById() {
        // Implementation
    }

}
*/
