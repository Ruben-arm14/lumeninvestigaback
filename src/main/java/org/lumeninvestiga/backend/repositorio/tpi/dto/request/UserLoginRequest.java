package org.lumeninvestiga.backend.repositorio.tpi.dto.request;

import jakarta.validation.constraints.*;

public record UserLoginRequest(
        @NotBlank
        String username,
        @Size(min = 8, message = "password must be at least 8 characters long")
                @NotBlank
        String password
) {
}
