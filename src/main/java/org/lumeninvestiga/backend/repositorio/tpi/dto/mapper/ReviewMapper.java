package org.lumeninvestiga.backend.repositorio.tpi.dto.mapper;

import org.lumeninvestiga.backend.repositorio.tpi.dto.response.ReviewResponse;
import org.lumeninvestiga.backend.repositorio.tpi.entities.user.Review;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ReviewMapper {
    ReviewMapper INSTANCE = Mappers.getMapper(ReviewMapper.class);

    //TODO: Agregar la fecha de creacion del review
    @Mapping(target = "name", expression = "java(review.getUser().getUserDetail().getName())")
    @Mapping(target = "createdDate", expression = "java(review.getCreatedDate())")
    @Mapping(target = "comment", source = "comment")
    ReviewResponse toReviewResponse(Review review);
}
