package org.lumeninvestiga.backend.repositorio.tpi.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserLoginRequest(
        @Email(message = "invalid email address")
                @NotEmpty
        String email,
        @Size(min = 8, message = "password must be at least 8 characters long")
                @NotEmpty
        String password
) {
}
