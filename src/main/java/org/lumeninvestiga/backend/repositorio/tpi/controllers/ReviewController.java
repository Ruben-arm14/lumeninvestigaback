package org.lumeninvestiga.backend.repositorio.tpi.controllers;

import jakarta.servlet.http.HttpServletRequest;
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
@RequestMapping("/api/v1")
public class ReviewController {
    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("/articles/{article_id}")
    public ResponseEntity<?> createReview(
            @PathVariable("article_id") Long articleId,
            @Valid @RequestBody ReviewPostRequest request,
            HttpServletRequest httpRequest) {
        return ResponseEntity.ok(reviewService.saveReview(articleId, request, httpRequest));
    }

    @GetMapping("/reviews")
    public ResponseEntity<?> readReviews(@PageableDefault Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(reviewService.getAllReviews(pageable));
    }

    @GetMapping("/reviews/{article_id}")
    public ResponseEntity<?> readReviewsByArticleId(@PathVariable("article_id") Long reviewId, @PageableDefault Pageable pageable) {
        return ResponseEntity.ok(reviewService.getReviewsByArticleId(reviewId, pageable));
    }

    @PutMapping("/articles/{article_id}/{review_id}")
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