package org.lumeninvestiga.backend.repositorio.tpi.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record AuthorizationRequest(
        @Email(message = "invalid email address")
            @NotEmpty
        String email,
        @Size(min = 8, message = "password must be at least 8 characters long")
            @NotEmpty
        String password
) {
}
