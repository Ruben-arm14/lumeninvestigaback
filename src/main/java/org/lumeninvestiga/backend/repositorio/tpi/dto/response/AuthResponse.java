package org.lumeninvestiga.backend.repositorio.tpi.dto.response;

import lombok.Builder;

@Builder
public record AuthResponse(
        String token
) {
}
