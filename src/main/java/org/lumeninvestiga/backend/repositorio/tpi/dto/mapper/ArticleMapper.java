package org.lumeninvestiga.backend.repositorio.tpi.dto.mapper;

import org.lumeninvestiga.backend.repositorio.tpi.dto.response.ArticleResponse;
import org.lumeninvestiga.backend.repositorio.tpi.entities.data.Article;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ArticleMapper {
    ArticleMapper INSTANCE = Mappers.getMapper(ArticleMapper.class);

    @Mapping(target = "articleDetail.area", source = "articleDetail.area")
    @Mapping(target = "articleDetail.subArea", source = "articleDetail.subArea")
    @Mapping(target = "articleDetail.period", source = "articleDetail.period")
    @Mapping(target = "articleDetail.ods", source = "articleDetail.ODS.name()")
    @Mapping(target = "articleDetail.title", source = "articleDetail.title")
    @Mapping(target = "articleDetail.author", source = "articleDetail.author")
    @Mapping(target = "articleDetail.advisor", source = "articleDetail.advisor")
    @Mapping(target = "articleDetail.resume", source = "articleDetail.resume")
    @Mapping(target = "articleDetail.keywords", expression = "java(String.join(\",\", article.getArticleDetail().getKeywords()))")
    ArticleResponse toArticleResponse(Article article);
}
