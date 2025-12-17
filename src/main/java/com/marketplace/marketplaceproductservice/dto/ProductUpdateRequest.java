package com.marketplace.marketplaceproductservice.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductUpdateRequest {
    private Long id;
    @NotBlank
    @Size(max = 255, message = "Name cannot exceed 255 characters.")
    private String name;

    @DecimalMin(value = "0.01", message = "Price must be greater than zero.")
    private BigDecimal price;

    @Min(value = 0, message = "Quantity cannot be negative.")
    private  Integer quantity;

    @Size(max = 1000, message = "Description is too long.")
    private String description;
}
