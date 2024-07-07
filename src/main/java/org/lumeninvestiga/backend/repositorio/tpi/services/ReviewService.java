package org.lumeninvestiga.backend.repositorio.tpi.services;

import org.lumeninvestiga.backend.repositorio.tpi.dto.request.ReviewPostRequest;
import org.lumeninvestiga.backend.repositorio.tpi.dto.request.ReviewUpdateRequest;
import org.lumeninvestiga.backend.repositorio.tpi.dto.response.ReviewResponse;
import org.lumeninvestiga.backend.repositorio.tpi.entities.user.Review;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
// C R U D
public interface ReviewService {
    Optional<ReviewResponse> saveReview(Long articleId, ReviewPostRequest request);
    //TODO: Considerar devolver un Page<ReviewResponse>
    List<ReviewResponse> getAllReviews(Pageable pageable);
    List<ReviewResponse> getReviewsByArticleId(Long articleId);
    Optional<ReviewResponse> getReviewById(Long id);
    void updateReviewComment(Long id, ReviewUpdateRequest request);
    void deleteReviewById(Long id);
    boolean existReviewById(Long id);


}
