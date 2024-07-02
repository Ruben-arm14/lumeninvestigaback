package org.lumeninvestiga.backend.repositorio.tpi.dto.mapper;

import org.lumeninvestiga.backend.repositorio.tpi.dto.response.ReviewResponse;
import org.lumeninvestiga.backend.repositorio.tpi.entities.user.Review;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ReviewMapper {
    ReviewMapper INSTANCE = Mappers.getMapper(ReviewMapper.class);

    @Mapping(source = "user.userDetail.name", target = "name")
    @Mapping(source = "comment", target = "comment")
    ReviewResponse toReviewResponse(Review review);

//    @Mapping(source = "user.id", target = "userId")
//    @Mapping(source = "article.id", target = "articleId")
//    @Mapping(source = "comment", target = "comment")
//    ReviewResponse toReviewResponse(Review review);
}
