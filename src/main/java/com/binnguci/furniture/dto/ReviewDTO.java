package com.binnguci.furniture.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReviewDTO {
    private Integer id;
    @NotBlank(message = "Comment must not be blank")
    @Size(min = 10, max = 500, message = "Comment must be between 10 and 500 characters")
    private String comment;
    @NotNull(message = "Rating is required")
    @DecimalMin(value = "0.5", message = "Rating must be at least 0.5")
    @DecimalMax(value = "5.0", message = "Rating must be at most 5.0")
    private Double rating;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    @NotNull(message = "Need id of product to review")
    private ProductDTO product;
    @NotNull(message = "Need id of user to review")
    private UserDTO user;
}