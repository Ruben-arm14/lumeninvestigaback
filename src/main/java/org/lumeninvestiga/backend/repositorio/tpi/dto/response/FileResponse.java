package org.lumeninvestiga.backend.repositorio.tpi.dto.response;

import java.time.LocalDateTime;

public record FileResponse(
        String name,
        Long size,
        LocalDateTime createdDate,
        String userName,
        String mimeType,
        String folderName,
        boolean shared
) {
}
