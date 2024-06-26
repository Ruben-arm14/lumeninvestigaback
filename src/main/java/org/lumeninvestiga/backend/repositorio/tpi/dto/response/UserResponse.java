package org.lumeninvestiga.backend.repositorio.tpi.dto.response;

public record UserResponse(
        String name,
        String lastName,
        String code,
        String emailAddress
        ) {
}
