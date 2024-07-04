package org.lumeninvestiga.backend.repositorio.tpi.dto.response;

public record ArticleDetail(
        String area,
        String subArea,
        String period,
        // El valor String del ODS_GOALS
        String ods,
        String title,
        String author,
        String advisor,
        String resume,
        String keywords
) {
}
