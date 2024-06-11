package org.lumeninvestiga.backend.repositorio.tpi.dto;

public record PreviewArticleDTO(
        String title,
        String author,
        String resume,
        boolean liked
) {
}
