package org.lumeninvestiga.backend.repositorio.tpi.controllers;

import org.lumeninvestiga.backend.repositorio.tpi.entities.user.Review;
import org.lumeninvestiga.backend.repositorio.tpi.services.ReviewService;
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

    @PostMapping
    public ResponseEntity<?> createReview(@RequestBody Review review) {
        return ResponseEntity.ok(reviewService.saveReview(review));
    }

    @GetMapping
    public ResponseEntity<?> readReviews() {
        return ResponseEntity.status(HttpStatus.OK).body(reviewService.getAllReviews());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> readReviewById(@PathVariable Long id) {
        return ResponseEntity.ok(reviewService.getReviewById(id));
    }

    //Solo se puede modificar el comentario y isLiked
    @PutMapping("/{id}")
    public ResponseEntity<?> updateReviewById(@PathVariable Long id, @RequestBody Review review) {
        Optional<Review> reviewOptional = reviewService.getReviewById(id);
        reviewOptional.ifPresent(reviewDb -> {
            reviewDb.setComment(review.getComment());
            reviewDb.setLiked(review.isLiked());            
        });
        return ResponseEntity.status(HttpStatus.CREATED).body(reviewService.saveReview(reviewOptional.get()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteReviewById(@PathVariable Long id){
        Optional<Review> reviewOptional = reviewService.getReviewById(id);
        if(reviewOptional.isPresent()) {
            reviewService.deleteReviewById(id);
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
