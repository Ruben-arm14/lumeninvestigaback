package org.lumeninvestiga.backend.repositorio.tpi.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public record UserLoginRequest(
        @Email(message = "invalid email address")
        String email,
        @Size(min = 8, message = "password must be at least 8 characters long")
        String password
) {
}
