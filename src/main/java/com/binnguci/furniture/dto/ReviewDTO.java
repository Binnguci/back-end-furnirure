package com.binnguci.furniture.dto;

import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ReviewDTO {
    Integer id;
    @NotBlank(message = "Comment must not be blank")
    @Size(min = 10, max = 500, message = "Comment must be between 10 and 500 characters")
    String comment;
    @NotNull(message = "Rating is required")
    @DecimalMin(value = "0.5", message = "Rating must be at least 0.5")
    @DecimalMax(value = "5.0", message = "Rating must be at most 5.0")
    Double rating;
    Instant createdAt;
    Instant updatedAt;
    @NotNull(message = "Need id of product to review")
    ProductDTO product;
    @NotNull(message = "Need id of user to review")
    UserDTO user;
}