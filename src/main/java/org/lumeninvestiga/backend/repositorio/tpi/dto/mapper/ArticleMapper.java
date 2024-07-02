package org.lumeninvestiga.backend.repositorio.tpi.dto.mapper;

import org.lumeninvestiga.backend.repositorio.tpi.dto.response.ArticleDetail;
import org.lumeninvestiga.backend.repositorio.tpi.dto.response.ArticleResponse;
import org.lumeninvestiga.backend.repositorio.tpi.entities.data.Article;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ArticleMapper {
    ArticleMapper INSTANCE = Mappers.getMapper(ArticleMapper.class);

    @Mapping(target = "articleDetail", source = "articleDetail")
    ArticleResponse toArticleResponse(Article article);

    @Mapping(target = "ods", expression = "java(articleDetail.getODS().name())")
    @Mapping(target = "keywords", expression = "java(String.join(\",\", articleDetail.getKeywords()))")
    ArticleDetail mapArticleDetail(org.lumeninvestiga.backend.repositorio.tpi.entities.data.ArticleDetail articleDetail);
}
