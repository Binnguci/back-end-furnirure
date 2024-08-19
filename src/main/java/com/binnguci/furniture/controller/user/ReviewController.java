package com.binnguci.furniture.controller.user;

import com.binnguci.furniture.dto.ReviewDTO;
import com.binnguci.furniture.dto.response.APIResponse;
import com.binnguci.furniture.enums.ErrorCode;
import com.binnguci.furniture.exception.AppException;
import com.binnguci.furniture.service.review.IReviewService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/reviews")
public class ReviewController {
    private final IReviewService reviewService;

    @PostMapping("/create")
    public ResponseEntity<APIResponse<ReviewDTO>> createReview(@Valid @RequestBody ReviewDTO reviewDTO) {
        log.info("Request to create review");
        try {
            ReviewDTO review = reviewService.updateAndCreate(reviewDTO);
            APIResponse<ReviewDTO> response = APIResponse.<ReviewDTO>builder()
                    .code(ErrorCode.CREATE_SUCCESS.getCode())
                    .message(ErrorCode.CREATE_SUCCESS.getMessage())
                    .result(review)
                    .build();
            return ResponseEntity.ok(response);
        } catch (Exception ex) {
            throw new AppException(ErrorCode.CREATE_FAILED);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<APIResponse<ReviewDTO>> updateReview(@RequestBody ReviewDTO reviewDTO) {
        log.info("Request to update review");
        try {
            ReviewDTO review = reviewService.updateAndCreate(reviewDTO);
            APIResponse<ReviewDTO> response = APIResponse.<ReviewDTO>builder()
                    .code(ErrorCode.UPDATE_SUCCESS.getCode())
                    .message(ErrorCode.UPDATE_SUCCESS.getMessage())
                    .result(review)
                    .build();
            return ResponseEntity.ok(response);
        } catch (Exception ex) {
            throw new AppException(ErrorCode.UPDATE_FAILED);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<APIResponse<ReviewDTO>> deleteReview(@PathVariable Long id) {
        log.info("Request to delete review with id: {}", id);
        try {
            ReviewDTO review = reviewService.delete(id);
            APIResponse<ReviewDTO> response = APIResponse.<ReviewDTO>builder()
                    .code(ErrorCode.DELETE_SUCCESS.getCode())
                    .message(ErrorCode.DELETE_SUCCESS.getMessage())
                    .result(review)
                    .build();
            return ResponseEntity.ok(response);
        } catch (Exception ex) {
            throw new AppException(ErrorCode.DELETE_FAILED);
        }
    }
}
