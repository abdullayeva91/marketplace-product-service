package com.marketplace.marketplaceproductservice.controller;

import com.marketplace.marketplaceproductservice.dto.ProductCreateRequest;
import com.marketplace.marketplaceproductservice.dto.ProductResponse;
import com.marketplace.marketplaceproductservice.dto.ProductUpdateRequest;
import com.marketplace.marketplaceproductservice.mapper.ProductMapper;
import com.marketplace.marketplaceproductservice.model.Product;
import com.marketplace.marketplaceproductservice.repository.ProductRepository;
import com.marketplace.marketplaceproductservice.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductMapper productMapper;

    @GetMapping
    public ResponseEntity<List<Product>>findAllProducts(){
        return ResponseEntity.ok(productService.findAllProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> findProductById(@PathVariable Long id){
        return ResponseEntity.ok(productService.findProductById(id));
    }

    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@Valid @RequestBody ProductCreateRequest productRequest){
        Product product = productService.createProduct(productRequest);

        ProductResponse response = productMapper.toResponse(product);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @Valid @RequestBody ProductUpdateRequest product){
        return ResponseEntity.ok(productService.updateProduct(id, product));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
        return ResponseEntity.ok().build();
    }


}


