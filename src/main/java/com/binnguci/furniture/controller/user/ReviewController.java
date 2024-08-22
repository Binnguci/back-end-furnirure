package com.binnguci.furniture.controller.user;

import com.binnguci.furniture.dto.ReviewDTO;
import com.binnguci.furniture.domain.response.APIResponse;
import com.binnguci.furniture.enums.ErrorCode;
import com.binnguci.furniture.exception.AppException;
import com.binnguci.furniture.service.review.IReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/reviews")
public class ReviewController {
    private final IReviewService reviewService;

    @GetMapping()
    public ResponseEntity<APIResponse<Page<ReviewDTO>>> getAllReviews(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        log.info("Request to get all reviews");
        Pageable pageable = PageRequest.of(page, size);
        Page<ReviewDTO> reviewPage = reviewService.findAll(pageable);
        return buildResponse(reviewPage, ErrorCode.SUCCESS);
    }

    @PostMapping("/create")
    public ResponseEntity<APIResponse<ReviewDTO>> createReview(@Valid @RequestBody ReviewDTO reviewDTO) {
        log.info("Request to create review");
        ReviewDTO review = reviewService.updateAndCreate(reviewDTO);
        return buildResponse(review, ErrorCode.CREATE_SUCCESS);
    }

    @PutMapping("/update")
    public ResponseEntity<APIResponse<ReviewDTO>> updateReview(@RequestBody ReviewDTO reviewDTO) {
        log.info("Request to update review");
        ReviewDTO review = reviewService.updateAndCreate(reviewDTO);
        return buildResponse(review, ErrorCode.UPDATE_SUCCESS);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<APIResponse<ReviewDTO>> deleteReview(@PathVariable Long id) {
        log.info("Request to delete review with id: {}", id);
        ReviewDTO review = reviewService.delete(id);
        return buildResponse(review, ErrorCode.DELETE_SUCCESS);
    }

    private <T> ResponseEntity<APIResponse<T>> buildResponse(T result, ErrorCode errorCode) {
        APIResponse<T> response = APIResponse.<T>builder()
                .code(errorCode.getCode())
                .message(errorCode.getMessage())
                .result(result)
                .build();
        return ResponseEntity.ok(response);
    }
}
