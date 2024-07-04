package org.lumeninvestiga.backend.repositorio.tpi.dto.response;

public record ArticleDetailDTO(
        String area,
        String subArea,
        String period,
        String ods,
        String title,
        String author,
        String advisor,
        String resume,
        String keywords
) {
}
