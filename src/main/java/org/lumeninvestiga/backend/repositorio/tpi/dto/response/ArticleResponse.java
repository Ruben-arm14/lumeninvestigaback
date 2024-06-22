package org.lumeninvestiga.backend.repositorio.tpi.dto.response;

public record ArticleResponse(
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
