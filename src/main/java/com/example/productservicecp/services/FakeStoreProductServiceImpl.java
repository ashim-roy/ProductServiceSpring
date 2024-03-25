package com.example.productservicecp.services;

import com.example.productservicecp.dtos.FakeStoreProductDto;
import com.example.productservicecp.exceptions.ProductNotFoundException;
import com.example.productservicecp.models.Category;
import com.example.productservicecp.models.Product;
import com.example.productservicecp.thirdpartyclients.FakeStoreClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedList;
import java.util.List;

// Service Layer: FakeStoreProductServiceImpl.
// This service class delegates product-related operations to the FakeStoreClient.
@Service("FakeProductService")
public class FakeStoreProductServiceImpl implements ProductService {
    private FakeStoreClient fakeStoreClient;
    @Autowired
    public FakeStoreProductServiceImpl(FakeStoreClient fakeStoreClient) {
        this.fakeStoreClient = fakeStoreClient;
    }

    // Service Methods: getProductByID, getAllProducts, deleteProductById, addProduct.
    // The service class provides methods for retrieving, deleting, adding, and updating products, which internally call the respective methods of the FakeStoreClient.
    //These methods handle any necessary conversions between DTOs (Data Transfer Objects) used by the client and domain objects used internally.
    @Override
    public Product getProductByID(Long id) throws ProductNotFoundException {
        return getProductFromFakeSToreProductDto(fakeStoreClient.getProductByID(id));
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> productList = new LinkedList<>();
        for (FakeStoreProductDto fakeStoreProductDto : fakeStoreClient.getAllProducts()) {
            productList.add(getProductFromFakeSToreProductDto(fakeStoreProductDto));
        }
        return productList;
    }

    @Override
    public Product deleteProductById(Long id) {
        return getProductFromFakeSToreProductDto(fakeStoreClient.deleteProductById(id));
    }

    @Override
    public Product addProduct(Product product) {
        return getProductFromFakeSToreProductDto(
                fakeStoreClient.addProduct(getFakeSToreProductDtoFromProduct(product)));
    }

    @Override
    public Product updateProductById(Long id, Product productDetails){
        return getProductFromFakeSToreProductDto(
                fakeStoreClient.updateProductById(id, getFakeSToreProductDtoFromProduct(productDetails)));
    }
    /*
    Request Flow:
When a request is made to retrieve, delete, add, or update a product:
The service method delegates the request to the corresponding method of the FakeStoreClient.
The FakeStoreClient uses RestTemplate to make HTTP requests to the fake store API endpoints.
The response received from the API is processed and possibly converted between DTOs and domain objects.
Finally, the result is returned to the caller.
In summary, the request flow involves client methods invoking RestTemplate to interact with the fake store API,
with service methods acting as intermediaries for handling business logic and conversions between DTOs and domain objects.
     */

    //convert DTO to product
    private Product getProductFromFakeSToreProductDto(FakeStoreProductDto fakeStoreProductDto) {
        Product product = new Product();
        //product.setId(fakeStoreProductDto.getId());
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setDescription(fakeStoreProductDto.getDescription());
        Category category = new Category();
        category.setName(fakeStoreProductDto.getCategory());
        product.setCategory(category);
        product.setPrice(fakeStoreProductDto.getPrice());

        return product;
    }

    //Reverse Mapper to convert product to dto.
    //I will get product from client. but the fakestore API understands fakestoreDTO.
    private FakeStoreProductDto getFakeSToreProductDtoFromProduct(Product product) {
        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        //we will not have the id from client..
        fakeStoreProductDto.setTitle(product.getTitle());
        fakeStoreProductDto.setDescription(product.getDescription());
        fakeStoreProductDto.setCategory(product.getCategory().getName());
        fakeStoreProductDto.setPrice(product.getPrice());

        return fakeStoreProductDto;
    }

}
