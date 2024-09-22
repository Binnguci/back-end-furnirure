package com.binnguci.furniture.dto;

import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductDTO {

    Integer id;

    @NotBlank(message = "Product name cannot be empty")
    @Size(max = 255, message = "Product name cannot exceed 255 characters")
    String name;

    @NotNull(message = "Price cannot be null")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than zero")
    Double price;

    @NotNull(message = "Stock cannot be null")
    @Min(value = 0, message = "Stock cannot be negative")
    Integer stock;

    @Size(max = 1000, message = "Description cannot exceed 100 characters")
    String description;

    Short isActive;

    @NotBlank(message = "Image URL cannot be empty")
    String image;

    @NotNull(message = "Category is required")
    CategoryDTO category;

    @NotNull(message = "Supplier is required")
    SupplierDTO supplier;

    Set<CartItemDTO> cartItems;
    Set<ReviewDTO> reviews;
    Instant createdAt;
    Instant updatedAt;
}
