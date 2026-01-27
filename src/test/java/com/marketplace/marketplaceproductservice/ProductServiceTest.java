package com.marketplace.marketplaceproductservice;

import com.marketplace.marketplaceproductservice.mapper.ProductMapper;
import com.marketplace.marketplaceproductservice.model.Product;
import com.marketplace.marketplaceproductservice.repository.ProductRepository;
import com.marketplace.marketplaceproductservice.service.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;


import com.marketplace.marketplaceproductservice.dto.ProductCreateRequest;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;
    @Mock
    private ProductMapper productMapper;

    @InjectMocks
    private ProductService productService;

    @Test
    void shouldCreateProductSuccessfully() {
        ProductCreateRequest request = new ProductCreateRequest();
        request.setName("Laptop");
        request.setPrice(new BigDecimal("1500.00"));
        request.setQuantity(10);

        Product mappedProduct = new Product();
        mappedProduct.setName("Laptop");
        mappedProduct.setPrice(new BigDecimal("1500.00"));
        mappedProduct.setQuantity(10);

        Product savedProduct = Product.builder()
                .id(1L)
                .name("Laptop")
                .build();

        when(productMapper.toEntity(any(ProductCreateRequest.class))).thenReturn(mappedProduct);
        when(productRepository.save(any(Product.class))).thenReturn(savedProduct);

        Product result = productService.createProduct(request);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Laptop", result.getName());

        verify(productMapper).toEntity(any(ProductCreateRequest.class));
        verify(productRepository).save(any(Product.class));
    }
}