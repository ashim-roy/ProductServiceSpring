package com.example.productservicecp.controllers.advices;
import com.example.productservicecp.controllers.ProductController;
import com.example.productservicecp.dtos.ExceptionDto;
import com.example.productservicecp.exceptions.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//@ControllerAdvice(basePackages = "com.example.productservicecp.controllers")
//@ControllerAdvice(assignableTypes = ProductController.class)
@RestControllerAdvice(assignableTypes = ProductController.class)
// @RestControllerAdvice: This annotation is a combination of @ControllerAdvice and @ResponseBody. It allows you to use this class to handle exceptions thrown from controllers and automatically convert the response to JSON using the ResponseEntity class.
public class ProductControllerAdvice {
    // Exception handling method
    @ExceptionHandler(ProductNotFoundException.class)
    //@ResponseStatus(HttpStatus.NOT_FOUND)
    //@ResponseBody
    private ResponseEntity<ExceptionDto> handleProductNotFoundException(ProductNotFoundException e){
        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setMessage(e.getMessage());
        exceptionDto.setStatus("failure");
        ResponseEntity<ExceptionDto> responseEntity
                = new ResponseEntity<>(exceptionDto, HttpStatus.NOT_FOUND);

        return responseEntity;
    }
}
/*
The purpose of this advice class is to provide centralized exception handling for the ProductController. When a ProductNotFoundException occurs within any method of the ProductController, Spring will intercept the exception and invoke the handleProductNotFoundException method in this advice class.
The method then constructs an appropriate response entity (ExceptionDto wrapped in ResponseEntity) with the details of the exception and returns it to the client.
This approach helps in maintaining a consistent error response format across the application and separates the error handling logic from the controller methods, improving code readability and maintainability.
Overall, this advice class ensures that exceptions thrown by the ProductController are properly handled and transformed into meaningful error responses for the client.
 */