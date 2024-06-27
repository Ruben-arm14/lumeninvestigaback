package org.lumeninvestiga.backend.repositorio.tpi.controllers;

import org.lumeninvestiga.backend.repositorio.tpi.dto.request.ReviewPostRequest;
import org.lumeninvestiga.backend.repositorio.tpi.dto.request.ReviewUpdateRequest;
import org.lumeninvestiga.backend.repositorio.tpi.dto.response.ReviewResponse;
import org.lumeninvestiga.backend.repositorio.tpi.entities.user.Review;
import org.lumeninvestiga.backend.repositorio.tpi.services.ReviewService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/reviews")
public class ReviewController {
    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> createReview(@RequestBody ReviewPostRequest request) {
        return ResponseEntity.ok(reviewService.saveReview(request));
    }

    @GetMapping
    public ResponseEntity<?> readReviews(@PageableDefault Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(reviewService.getAllReviews(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> readReviewById(@PathVariable Long id) {
        return ResponseEntity.ok(reviewService.getReviewById(id));
    }

    //Solo se puede modificar el comentario y isLiked
    @PutMapping("/{id}")
    public ResponseEntity<?> updateReviewById(@PathVariable Long id, @RequestBody ReviewUpdateRequest request) {
        reviewService.updateReviewComment(id, request);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteReviewById(@PathVariable Long id){
        reviewService.deleteReviewById(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}
