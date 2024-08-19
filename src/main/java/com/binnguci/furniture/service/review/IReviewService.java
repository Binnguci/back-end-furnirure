package com.binnguci.furniture.service.review;

import com.binnguci.furniture.dto.ReviewDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IReviewService {
    Page<ReviewDTO> findAll(Pageable pageable);
    ReviewDTO updateAndCreate(ReviewDTO reviewDTO);
    ReviewDTO delete(Long id);
}
