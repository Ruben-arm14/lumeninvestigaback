package org.lumeninvestiga.backend.repositorio.tpi.dto;

import jakarta.validation.constraints.NotBlank;

public record UserProfileDTO(
        @NotBlank
        String name,
        @NotBlank
        String lastname,
        @NotBlank
        String emailAddress
        ) {
}
