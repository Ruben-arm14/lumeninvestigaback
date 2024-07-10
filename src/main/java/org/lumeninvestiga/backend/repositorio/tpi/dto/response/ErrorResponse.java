package org.lumeninvestiga.backend.repositorio.tpi.dto.response;

import java.time.LocalDateTime;

public record ErrorResponse(
        String path,
        String message,
        int statusCode,
        LocalDateTime localDateTime
) {
}
