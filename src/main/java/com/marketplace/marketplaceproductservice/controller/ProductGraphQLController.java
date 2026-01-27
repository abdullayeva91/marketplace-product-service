package com.marketplace.marketplaceproductservice.controller;

import com.marketplace.marketplaceproductservice.model.Product;
import com.marketplace.marketplaceproductservice.service.ProductService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class ProductGraphQLController {
    private final ProductService productService;

    public ProductGraphQLController(ProductService productService) {
        this.productService = productService;
    }

    @QueryMapping
    public List<Product> getAllProducts() {
        return productService.findAllProducts();
    }

    @QueryMapping
    public Product getProductById(@Argument Long id) {
        return productService.findProductById(id);
    }
}

