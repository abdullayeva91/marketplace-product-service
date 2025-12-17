package com.marketplace.marketplaceproductservice.exception;

import com.marketplace.marketplaceproductservice.dto.ProductResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<String> handleProductNotFoundException(ProductNotFoundException e){
        ProductResponse productResponse = new ProductResponse();
        return ResponseEntity.status(400).body("Məhsul Xətası: "+e.getMessage());
    }

}
