package org.lumeninvestiga.backend.repositorio.tpi.dto.mapper;

import org.lumeninvestiga.backend.repositorio.tpi.dto.response.ArticleDetailDTO;
import org.lumeninvestiga.backend.repositorio.tpi.dto.response.ArticleResponse;
import org.lumeninvestiga.backend.repositorio.tpi.dto.response.CommentDTO;
import org.lumeninvestiga.backend.repositorio.tpi.entities.data.Article;
import org.lumeninvestiga.backend.repositorio.tpi.entities.data.ArticleDetail;
import org.lumeninvestiga.backend.repositorio.tpi.entities.user.Review;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface ArticleMapper {
    ArticleMapper INSTANCE = Mappers.getMapper(ArticleMapper.class);

    @Mapping(target = "articleDetailDTO", source = "articleDetail")
    @Mapping(target = "comments", source = "reviews") // Añadir esta línea
    ArticleResponse toArticleResponse(Article article);

    @Mapping(target = "ods", source = "ods", qualifiedByName = "stringToList")
    @Mapping(target = "keywords", source = "keywords", qualifiedByName = "stringToList")
    ArticleDetailDTO articleDetailToArticleDetailDTO(ArticleDetail articleDetail);

    @Named("stringToList")
    default List<String> stringToList(String value) {
        return Arrays.asList(value.split(","));
    }

    @Named("listToString")
    default String listToString(List<String> value) {
        return String.join(",", value);
    }

    // Añadir método para mapear los comentarios
    default List<CommentDTO> reviewsToCommentDTOs(List<Review> reviews) {
        return reviews.stream()
                .map(review -> new CommentDTO(
                        review.getUser().getUsername(),
                        review.getComment(),
                        review.getCreatedDate(),
                        review.getLikeCount() // Ajuste aquí
                ))
                .collect(Collectors.toList());
    }
}
