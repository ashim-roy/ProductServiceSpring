package com.example.productservicecp.exceptions;

public class ProductNotFoundException extends Exception{
    public ProductNotFoundException(String message){
        super(message);
        // Calling the constructor of the Exception class with the provided message
    }
}



