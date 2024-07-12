package org.lumeninvestiga.backend.repositorio.tpi.dto.response;

public record UserResponse(
        Long id,
        String username,
        String name,
        String lastName,
        String emailAddress
) {
}
