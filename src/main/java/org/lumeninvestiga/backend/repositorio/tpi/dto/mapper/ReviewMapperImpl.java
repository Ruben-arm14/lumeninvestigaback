package org.lumeninvestiga.backend.repositorio.tpi.dto.mapper;

import org.lumeninvestiga.backend.repositorio.tpi.dto.response.ReviewResponse;
import org.lumeninvestiga.backend.repositorio.tpi.entities.user.Review;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ReviewMapperImpl implements ReviewMapper{
    @Override
    public ReviewResponse toReviewResponse(Review review) {
        return new ReviewResponse(
                review.getUser().getUserDetail().getName().concat(" "+ review.getUser().getUserDetail().getLastName()),
                review.getComment(),
                LocalDate.of(review.getCreatedDate().getYear(),
                        review.getCreatedDate().getMonth().getValue(),
                        review.getCreatedDate().getDayOfMonth())
        );
    }
}
