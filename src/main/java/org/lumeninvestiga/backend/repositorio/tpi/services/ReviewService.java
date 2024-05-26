package org.lumeninvestiga.backend.repositorio.tpi.services;

import org.lumeninvestiga.backend.repositorio.tpi.entities.Review;

import java.util.List;
import java.util.Optional;

public interface ReviewService {
    Optional<Review> createReview(Review review);
    List<Review> getAllReviews();
    Optional<Review> getReviewById(Long id);
    void deleteReviewById(Long id);
    boolean existReviewById(Long id);
}
