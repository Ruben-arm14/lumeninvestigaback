package org.lumeninvestiga.backend.repositorio.tpi.controllers;

import org.lumeninvestiga.backend.repositorio.tpi.entities.user.Review;
import org.lumeninvestiga.backend.repositorio.tpi.services.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reviews")
public class ReviewController {
    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping
    public ResponseEntity<?> createReview(@RequestBody Review review) {
        return ResponseEntity.ok(reviewService.createReview(review));
    }

    @GetMapping
    public ResponseEntity<?> getAllReviews() {
        return ResponseEntity.status(HttpStatus.OK).body(reviewService.getAllReviews());
    }

    @GetMapping("/{id}")


    @DeleteMapping
    public ResponseEntity<?> deleteReview(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

}
