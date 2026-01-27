package com.marketplace.marketplaceproductservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturnForbidden_WhenUserIsNotAdmin() throws Exception {
        String productJson = """
                {
                    "name": "Test Product",
                    "price": 100.0,
                    "quantity": 5,
                    "category": "ELECTRONICS"
                }
                """;

        mockMvc.perform(post("/api/products")
                        .header("X-User-Role", "USER")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(productJson))
                .andExpect(status().isForbidden());
    }

    @Test
    void shouldCreateProduct_WhenUserIsAdmin() throws Exception {
        String productJson = """
                {
                    "name": "Admin Product",
                    "price": 250.0,
                    "quantity": 10,
                    "category": "ELECTRONICS"
                }
                """;

        mockMvc.perform(post("/api/products")
                        .header("X-User-Role", "ADMIN")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(productJson))
                .andExpect(status().isCreated());
    }
}