package org.lumeninvestiga.backend.repositorio.tpi.dto.request;

import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

public record FilePostRequest(
        @NotBlank(message = "enter a text to name")
                @Size(min = 8, message = "name must be at least 8 characters long")
        String name,
        @NotEmpty
        long size,
        @NotNull
        LocalDateTime createdDate,
        @NotNull
        byte[] data,
        @NotBlank
        String mimeType,
        @NotEmpty
                @Min(1)
        long idUser,
        @NotEmpty
                @Min(1)
        long idFolder
) {
}
