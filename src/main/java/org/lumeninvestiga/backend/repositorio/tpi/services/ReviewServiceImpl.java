package org.lumeninvestiga.backend.repositorio.tpi.services;

import org.lumeninvestiga.backend.repositorio.tpi.dto.mapper.ReviewMapper;
import org.lumeninvestiga.backend.repositorio.tpi.dto.request.ReviewPostRequest;
import org.lumeninvestiga.backend.repositorio.tpi.dto.request.ReviewUpdateRequest;
import org.lumeninvestiga.backend.repositorio.tpi.dto.response.ReviewResponse;
import org.lumeninvestiga.backend.repositorio.tpi.entities.user.Review;
import org.lumeninvestiga.backend.repositorio.tpi.repositories.ReviewRepository;
import org.lumeninvestiga.backend.repositorio.tpi.utils.Utility;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService{
    private final ReviewRepository reviewRepository;
    private final ReviewMapper reviewMapper;

    public ReviewServiceImpl(ReviewRepository reviewRepository, ReviewMapper reviewMapper) {
        this.reviewRepository = reviewRepository;
        this.reviewMapper = reviewMapper;
    }

    @Override
    @Transactional
    public Optional<ReviewResponse> saveReview(ReviewPostRequest request) {
        if(request.userId() == 0 && request.articleId() == 0) {
            //TODO: INVALID_RESOURCE_EXCEPTION
            throw new RuntimeException();
        }
        if(request.comment().isBlank()) {
            //TODO: NO_CONTENT_COMMENT_EXCEPTION
            throw new RuntimeException();
        }
        Review reviewRequest = new Review();
        // Review tomamos sus FK y los setteamos con los id's del request
        // cansiderar la logica de id's a los @PathVariable del controlador.
        reviewRequest.getUser().setId(request.userId());
        reviewRequest.getArticle().setId(request.articleId());
        reviewRequest.setComment(request.comment());
        reviewRepository.save(reviewRequest);
        return Optional.of(reviewMapper.toReviewResponse(reviewRequest));
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReviewResponse> getAllReviews(Pageable pageable) {
        int page = Utility.getCurrentPage(pageable);
        return reviewRepository.findAll(PageRequest.of(page, pageable.getPageSize())).stream()
                .map(reviewMapper::toReviewResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ReviewResponse> getReviewById(Long id) {
        Optional<Review> reviewOptional = reviewRepository.findById(id);
        if(reviewOptional.isEmpty()) {
            //TODO: NOTFOUND_RESOURCE_EXCEPTION
            throw new RuntimeException();
        }
        return Optional.of(reviewMapper.toReviewResponse(reviewOptional.get()));
    }

    @Override
    public void updateReviewComment(Long id, ReviewUpdateRequest request) {
        Optional<Review> reviewOptional = reviewRepository.findById(id);
        reviewOptional.ifPresentOrElse(reviewDb -> {
            reviewDb.setComment(request.comment());
        },
                //TODO: NOTFOUND_RESOURCE_EXCEPTION
                () -> new RuntimeException());
    }

    @Override
    @Transactional
    public void deleteReviewById(Long id) {
        if(existReviewById(id)) {
            reviewRepository.deleteById(id);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existReviewById(Long id) {
        return reviewRepository.existsById(id);
    }
}
