package com.marketplace.marketplaceproductservice.dto;

import com.marketplace.marketplaceproductservice.enums.Category;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductCreateRequest {
    @NotBlank(message = "Product name is mandatory.")
    private String name;

    @NotNull(message = "Price field must be provided.")
    @DecimalMin(value = "0.01", message = "Price must be greater than zero.")
    private BigDecimal price;

    @NotNull(message = "Quantity/Stock level must be provided.")
    @Min(value = 0, message = "Quantity cannot be negative.")
    private  Integer quantity;

    @Size(max = 1000, message = "Description is too long.")
    private String description;

    private Category category;

}
