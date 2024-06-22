package org.lumeninvestiga.backend.repositorio.tpi.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ReviewPostRequest(
        @NotNull
        long userId,
        @NotNull
        long articleId,
        @NotBlank(message = "it cannot be empty")
                @Size(min = 2, max = 255, message = "comment must not exceed 255 characters in length")
        String comment
) {
}
