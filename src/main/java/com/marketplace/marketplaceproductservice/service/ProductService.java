package com.marketplace.marketplaceproductservice.service;

import com.marketplace.marketplaceproductservice.dto.ProductCreateRequest;
import com.marketplace.marketplaceproductservice.dto.ProductUpdateRequest;
import com.marketplace.marketplaceproductservice.exception.ProductNotFoundException;
import com.marketplace.marketplaceproductservice.mapper.ProductMapper;
import com.marketplace.marketplaceproductservice.model.Product;
import com.marketplace.marketplaceproductservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductMapper productMapper;

    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }
    public Product findProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(()->new ProductNotFoundException("Product not found"));
    }
    public Product createProduct(ProductCreateRequest createRequest) {
        Product product = productMapper.toEntity(createRequest);
        return productRepository.save(product);
    }
    public Product updateProduct(Long id, ProductUpdateRequest updateRequest) {
        Product existingProduct= productRepository.findById(id)
                .orElseThrow(()->new ProductNotFoundException("Product not found"));
        existingProduct.setName(updateRequest.getName());
        existingProduct.setDescription(updateRequest.getDescription());
        existingProduct.setPrice(updateRequest.getPrice());
        return productRepository.save(existingProduct);
    }
    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ProductNotFoundException("Product not found");
        }
        productRepository.deleteById(id);
    }
}

