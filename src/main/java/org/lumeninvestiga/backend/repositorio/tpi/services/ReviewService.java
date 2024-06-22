package org.lumeninvestiga.backend.repositorio.tpi.services;

import org.lumeninvestiga.backend.repositorio.tpi.entities.user.Review;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ReviewService {
    Optional<Review> saveReview(Review review);
    List<Review> getAllReviews(Pageable pageable);
    Optional<Review> getReviewById(Long id);
    void deleteReviewById(Long id);
    boolean existReviewById(Long id);
}
