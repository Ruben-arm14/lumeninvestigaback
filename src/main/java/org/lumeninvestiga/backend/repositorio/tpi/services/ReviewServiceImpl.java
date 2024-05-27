package org.lumeninvestiga.backend.repositorio.tpi.services;

import org.lumeninvestiga.backend.repositorio.tpi.entities.user.Review;
import org.lumeninvestiga.backend.repositorio.tpi.repositories.ReviewRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService{
    private final ReviewRepository reviewRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    @Transactional
    public Optional<Review> createReview(Review review) {
        return Optional.of(reviewRepository.save(review));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Review> getReviewById(Long id) {
        return reviewRepository.findById(id);
    }

    @Override
    @Transactional
    public void deleteReviewById(Long id) {
        reviewRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existReviewById(Long id) {
        return reviewRepository.existsById(id);
    }
}
