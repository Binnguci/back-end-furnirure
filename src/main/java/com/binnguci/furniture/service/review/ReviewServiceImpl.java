package com.binnguci.furniture.service.review;

import com.binnguci.furniture.dto.ReviewDTO;
import com.binnguci.furniture.entity.ReviewEntity;
import com.binnguci.furniture.enums.ErrorCode;
import com.binnguci.furniture.exception.AppException;
import com.binnguci.furniture.mapper.ReviewMapper;
import com.binnguci.furniture.repository.IReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
@RequiredArgsConstructor
public class ReviewServiceImpl implements IReviewService {
    private final IReviewRepository reviewRepository;
    private final ReviewMapper reviewMapper;

    @Override
    public Page<ReviewDTO> findAll(Pageable pageable) {
        log.info("Request to get all reviews");
        Page<ReviewEntity> productPage = reviewRepository.findAll(pageable);
        return productPage.map(reviewMapper::toDTO);
    }

    @Override
    public ReviewDTO updateAndCreate(ReviewDTO reviewDTO) {
        log.info("Request to update and create review");
        ReviewEntity reviewEntity = reviewMapper.toEntity(reviewDTO);
        if (reviewEntity.getId() == null) {
            reviewEntity.setCreatedAt(LocalDateTime.now());
            log.info("Successfully to create review");
        } else {
            reviewEntity.setUpdatedAt(LocalDateTime.now());
            log.info("Successfully to update review");
        }
        ReviewEntity savedEntity = reviewRepository.save(reviewEntity);
        return reviewMapper.toDTO(savedEntity);
    }

    @Override
    public ReviewDTO delete(Long id) {
        log.info("Request to delete review with id: {}", id);
        return reviewRepository.findById(id)
                .map(reviewEntity -> {
                    reviewRepository.delete(reviewEntity);
                    log.info("Successfully deleted review with id: {}", id);
                    return reviewMapper.toDTO(reviewEntity);
                })
                .orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND));
    }
}
