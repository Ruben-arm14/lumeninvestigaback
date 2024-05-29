package org.lumeninvestiga.backend.repositorio.tpi.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
public record UserRegistrationRequest(
        @NotBlank(message = "Name cannot be blank")
        String name,
        @NotBlank(message = "Last name cannot be blank")
        String lastName,
        @Email(message = "Invalid email address")
        String emailAddress,
        @Size(min = 8, message = "Password must be at least 8 characters long")
        String password
        ) {
}
