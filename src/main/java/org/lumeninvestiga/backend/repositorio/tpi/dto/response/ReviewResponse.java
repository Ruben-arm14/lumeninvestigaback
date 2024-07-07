package org.lumeninvestiga.backend.repositorio.tpi.dto.response;

import java.time.LocalDateTime;

public record ReviewResponse(
        String name,
        LocalDateTime createdDate,
        String comment
) {
}
