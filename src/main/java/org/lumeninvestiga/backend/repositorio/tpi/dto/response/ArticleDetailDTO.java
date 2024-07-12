package org.lumeninvestiga.backend.repositorio.tpi.dto.response;

import java.util.List;

public record ArticleDetailDTO(
        String area,
        String subArea,
        String period,
        List<String> ods,
        String title,
        String author,
        String advisor,
        String resume,
        List<String> keywords,
        String curso,
        String profesor
) {
}
