package org.lumeninvestiga.backend.repositorio.tpi.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ReviewUpdateRequest(
        @NotBlank(message = "it cannot be empty")
                @Size(min = 2, max = 255, message = "comment must not exceed 255 characters in length")
        String comment
) {
}
