package org.lumeninvestiga.backend.repositorio.tpi.controllers;

import jakarta.validation.Valid;
import org.lumeninvestiga.backend.repositorio.tpi.dto.request.ReviewPostRequest;
import org.lumeninvestiga.backend.repositorio.tpi.dto.request.ReviewUpdateRequest;
import org.lumeninvestiga.backend.repositorio.tpi.services.ReviewService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {
    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("/{user_id}/articles/{article_id}")
    public ResponseEntity<?> createReview(
            @PathVariable("user_id") Long userId,
            @PathVariable("article_id") Long articleId,
            @Valid @RequestBody ReviewPostRequest request) {
        return ResponseEntity.ok(reviewService.saveReview(userId, articleId, request));
    }

    @GetMapping
    public ResponseEntity<?> readReviews(@PageableDefault Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(reviewService.getAllReviews(pageable));
    }

    @GetMapping("/{article_id}")
    public ResponseEntity<?> readReviewsByArticleId(@PathVariable("article_id") Long reviewId) {
        return ResponseEntity.ok(reviewService.getReviewsByArticleId(reviewId));
    }

    //TODO: Revisar método.
    //NOTE: Esta lógica es para modificar el review dentro del contenido del artículo.
    @PutMapping("/{user_id}/articles/{article_id}/reviews/{review_id}")
    public ResponseEntity<?> updateReviewById(
            @PathVariable Long id,
            @Valid @RequestBody ReviewUpdateRequest request) {
        reviewService.updateReviewComment(id, request);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    //TODO: Revisar método
    @DeleteMapping("/{review_id}")
    public ResponseEntity<?> deleteReviewById(@PathVariable("review_id") Long reviewId){
        reviewService.deleteReviewById(reviewId);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}
