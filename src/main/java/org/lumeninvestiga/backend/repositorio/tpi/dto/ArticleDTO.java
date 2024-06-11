package org.lumeninvestiga.backend.repositorio.tpi.dto;

public record ArticleDTO(
        String title,
        String author,
        String resume,
        String course,
        String advisor,
        String area,
        String subArea,
        String professor,
        String ods,
        String period,
        String keywords,
        int likedCount
) {
}
