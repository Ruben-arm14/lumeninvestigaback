package org.lumeninvestiga.backend.repositorio.tpi.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UserUpdateRequest(
        @NotBlank(message = "Name cannot be blank")
        String name,
        @NotBlank(message = "Last name cannot be blank")
        String lastName,
        @Email(message = "Invalid email address")
        String email
) {
}
