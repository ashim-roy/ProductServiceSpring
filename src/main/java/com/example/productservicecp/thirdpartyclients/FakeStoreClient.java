package com.example.productservicecp.thirdpartyclients;

import com.example.productservicecp.dtos.FakeStoreProductDto;
import com.example.productservicecp.exceptions.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

@Component
public class FakeStoreClient {
    private RestTemplateBuilder restTemplateBuilder; // RestTemplateBuilder is used to create instances of RestTemplate
    // retrieving a specific product by its ID: specificProductURL and genericProductUrl  for all products
    private String specificProductURL;
    private String genericProductUrl = "https://fakestoreapi.com/products"; //getAllProductUrl

    // the client provides several methods for interacting with the fake store API: getProductByID, getAllProducts, deleteProductById, addProduct and updateProductById.
    @Autowired
    public FakeStoreClient(RestTemplateBuilder restTemplateBuilder,
                           @Value("${fakestore.api.url}") String fakeStoreUrl) {
        this.restTemplateBuilder = restTemplateBuilder;
        this.specificProductURL = fakeStoreUrl;
    }

    public FakeStoreProductDto getProductByID(Long id) throws ProductNotFoundException {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> responseEntity
                = restTemplate.getForEntity(specificProductURL, FakeStoreProductDto.class, id);
        if (responseEntity.getBody() == null) {
            // throw exception....
            throw new ProductNotFoundException("Product not found for id: " + id);
        }
        return responseEntity.getBody();
    }

    public FakeStoreProductDto[] getAllProducts() {  //returns DTO array
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto[]> responseEntity =
                restTemplate.getForEntity(genericProductUrl, FakeStoreProductDto[].class);

        return responseEntity.getBody();
    }
/*
responseEntity represents the HTTP response received from the server after making a GET request to the genericProductUrl URL. The response body contains an array of FakeStoreProductDto objects.
getBody() is a method provided by the ResponseEntity class. It returns the body of the HTTP response. In this case, since the response body is expected to be an array of FakeStoreProductDto objects, the return type of getBody() is FakeStoreProductDto[].
 */

    // deleteProductById(Long id): Deletes a product from the fake store API by its ID.
    public FakeStoreProductDto deleteProductById(Long id) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        // restTemplate.delete(specificProductURL, id);
        RequestCallback requestCallback = restTemplate.acceptHeaderRequestCallback(FakeStoreProductDto.class);
        ResponseExtractor<ResponseEntity<FakeStoreProductDto>> responseExtractor
                = restTemplate.responseEntityExtractor(FakeStoreProductDto.class);

        ResponseEntity<FakeStoreProductDto> responseEntity = restTemplate.execute(specificProductURL, HttpMethod.DELETE, requestCallback, responseExtractor, id);
        return responseEntity.getBody();
    }

// addProduct(FakeStoreProductDto fakeStoreProductDto): Adds a new product to the fake store API.
// The method takes a FakeStoreProductDto object as an argument and returns a FakeStoreProductDto object.
    public FakeStoreProductDto addProduct(FakeStoreProductDto fakeStoreProductDto) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> responseEntity
                = restTemplate.postForEntity(genericProductUrl, fakeStoreProductDto, FakeStoreProductDto.class);

        return responseEntity.getBody();
    }
// updateProductById(FakeStoreProductDto fakeStoreProductDto): Updates an existing product in the fake store API.
    public void updateProductById() {
    }

    public FakeStoreProductDto updateProductById(Long id, FakeStoreProductDto fakeSToreProductDtoFromProduct) {
        RestTemplate restTemplate = restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductDto> responseEntity
                = restTemplate.exchange(specificProductURL, HttpMethod.PUT, null, FakeStoreProductDto.class, id, fakeSToreProductDtoFromProduct);

        return responseEntity.getBody();
    }
}
