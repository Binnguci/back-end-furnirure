package com.binnguci.furniture.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDTO {

    private Integer id;

    @NotBlank(message = "Product name cannot be empty")
    @Size(max = 255, message = "Product name cannot exceed 255 characters")
    private String name;

    @NotNull(message = "Price cannot be null")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than zero")
    private Double price;

    @NotNull(message = "Stock cannot be null")
    @Min(value = 0, message = "Stock cannot be negative")
    private Integer stock;

    @Size(max = 1000, message = "Description cannot exceed 100 characters")
    private String description;

    private Short isActive;

    @NotBlank(message = "Image URL cannot be empty")
    private String image;

    @NotNull(message = "Category is required")
    private CategoryDTO category;

    @NotNull(message = "Supplier is required")
    private SupplierDTO supplier;

    private Set<CartItemDTO> cartItems;
    private Set<ReviewDTO> reviews;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
