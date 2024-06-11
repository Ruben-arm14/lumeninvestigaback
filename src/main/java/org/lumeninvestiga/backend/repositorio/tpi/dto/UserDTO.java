package org.lumeninvestiga.backend.repositorio.tpi.dto;

import jakarta.validation.constraints.NotBlank;

public record UserDTO(
        String name,
        String lastname,
        String emailAddress
        ) {
}
