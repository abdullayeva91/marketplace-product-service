package com.marketplace.marketplaceproductservice.controller;

import com.marketplace.marketplaceproductservice.dto.ProductCreateRequest;
import com.marketplace.marketplaceproductservice.dto.ProductResponse;
import com.marketplace.marketplaceproductservice.dto.ProductUpdateRequest;
import com.marketplace.marketplaceproductservice.enums.Category;
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
    private ProductService productService;
    @Autowired
    private ProductMapper productMapper;

    @GetMapping
    public ResponseEntity<List<ProductResponse>> findAllProducts() {
        List<Product> products = productService.findAllProducts();
        List<ProductResponse> responses = products.stream()
                .map(productMapper::toResponse)
                .toList();
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> findProductById(@PathVariable Long id) {
        Product product = productService.findProductById(id);
        return ResponseEntity.ok(productMapper.toResponse(product));
    }

        @GetMapping("/category/{category}")
        public ResponseEntity<List<ProductResponse>> getByCategory(@PathVariable Category category){
            List<Product> products = productService.getProductsByCategory(category);
            List<ProductResponse> responses = products.stream()
                    .map(productMapper::toResponse)
                    .toList();
            return ResponseEntity.ok(responses);
        }


    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(@Valid @RequestBody ProductCreateRequest productRequest){
        Product product = productService.createProduct(productRequest);

        ProductResponse response = productMapper.toResponse(product);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> updateProduct(
            @PathVariable Long id,
            @Valid @RequestBody ProductUpdateRequest product) {
        Product updated = productService.updateProduct(id, product);
        return ResponseEntity.ok(productMapper.toResponse(updated));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
        return ResponseEntity.ok().build();
    }


}


