package com.example.productservicecp.controllers;

import com.example.productservicecp.exceptions.ProductNotFoundException;
import com.example.productservicecp.models.Product;
import com.example.productservicecp.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@Controller
@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductService productService; // need instance of the service class to use its methods. so autowiring it.
    /*
    @Autowired
    public ProductController(@Qualifier("FakeProductService") ProductService productService){
        this.productService = productService;
    }
*/
    @Autowired
    public ProductController(@Qualifier("SelfProductService") ProductService productService){
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public Product getProductByID(@PathVariable("id") Long id) throws ProductNotFoundException { // here i dont need to mention exception because i am using optional
        return productService.getProductByID(id);
    }
    @GetMapping()
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }
    @PostMapping
    public Product createProduct(@RequestBody Product product){
        // @requestBody  will be doing the json mapping to product object. to bind the HTTP request body
        // to a method parameter. It indicates that a method parameter should be bound to the body of the HTTP request.
        return  productService.addProduct(product);
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable("id") Long id, @RequestBody Product productDetails) throws ProductNotFoundException {
        return productService.updateProductById(id, productDetails);
    }


    @DeleteMapping("/{id}")
    public Product deleteProductByID(@PathVariable("id") Long id) throws ProductNotFoundException {
        return productService.deleteProductById(id);
    }

    // handling exception by dto.
    /*
    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    private ExceptionDto handleProductNotFoundException(ProductNotFoundException e){
        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setMessage(e.getMessage());
        exceptionDto.setStatus("failure");

        return exceptionDto;
    }
*/
  /*
   @ExceptionHandler(ProductNotFoundException.class)
    //@ResponseStatus(HttpStatus.NOT_FOUND)
    //@ResponseBody
    private ResponseEntity<ExceptionDto> handleProductNotFoundException(ProductNotFoundException e){
        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setMessage(e.getMessage());
        exceptionDto.setStatus("failure");
        ResponseEntity<ExceptionDto> responseEntity = new ResponseEntity<>(exceptionDto, HttpStatus.NOT_FOUND);

        return responseEntity;
    }*/
   /* public String getProductsByCategory(String category){

    }*/
}
