package org.lumeninvestiga.backend.repositorio.tpi.dto.response;

import java.util.List;

public record ArticleResponse(
        Long id,
        ArticleDetailDTO articleDetailDTO,
        List<CommentDTO> comments
) {
}
