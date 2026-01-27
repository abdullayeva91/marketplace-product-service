package com.marketplace.marketplaceproductservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class MarketplaceProductServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MarketplaceProductServiceApplication.class, args);
    }

}
