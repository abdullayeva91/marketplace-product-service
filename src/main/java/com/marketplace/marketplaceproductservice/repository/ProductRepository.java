package com.marketplace.marketplaceproductservice.repository;

import com.marketplace.marketplaceproductservice.enums.Category;
import com.marketplace.marketplaceproductservice.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCategory(Category category);
}
