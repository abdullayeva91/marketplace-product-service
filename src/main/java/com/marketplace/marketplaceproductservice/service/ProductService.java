package com.marketplace.marketplaceproductservice.service;

import com.marketplace.marketplaceproductservice.dto.ProductCreateRequest;
import com.marketplace.marketplaceproductservice.dto.ProductUpdateRequest;
import com.marketplace.marketplaceproductservice.enums.Category;
import com.marketplace.marketplaceproductservice.exception.ProductNotFoundException;
import com.marketplace.marketplaceproductservice.mapper.ProductMapper;
import com.marketplace.marketplaceproductservice.model.Product;
import com.marketplace.marketplaceproductservice.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductMapper productMapper;

    @Cacheable(value = "products", key = "'all'")
    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }
    public List<Product> getProductsByCategory(Category category) {
        return productRepository.findByCategory(category);
    }

    @Cacheable(value = "products", key = "#id")
    public Product findProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(()->new ProductNotFoundException("Product not found"));
    }

    @CachePut(value = "products", key = "#result.id")
    @CacheEvict(value = "products", key = "'all'")
    public Product createProduct(ProductCreateRequest createRequest) {
        Product product = productMapper.toEntity(createRequest);
        return productRepository.save(product);
    }
    public Product updateProduct(Long id, ProductUpdateRequest updateRequest) {
        Product existingProduct = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found"));

        productMapper.updateEntityFromDto(updateRequest, existingProduct);
        return productRepository.save(existingProduct);
    }

    @CacheEvict(value = "products", allEntries = true)
    public void deleteProduct(Long id) {
        if (!productRepository.existsById(id)) {
            throw new ProductNotFoundException("Product not found");
        }
        productRepository.deleteById(id);
    }
}

