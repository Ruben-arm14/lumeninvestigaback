package org.lumeninvestiga.backend.repositorio.tpi.dto.request;

import jakarta.validation.constraints.NotBlank;

public record SearchRequest(
        @NotBlank(message = "enter a text to search for scientific articles")
        String searchText
) {
}
