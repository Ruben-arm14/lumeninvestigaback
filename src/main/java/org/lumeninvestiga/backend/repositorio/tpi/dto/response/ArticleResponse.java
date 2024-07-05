package org.lumeninvestiga.backend.repositorio.tpi.dto.response;

import java.util.List;

public record ArticleResponse(
        ArticleDetailDTO articleDetailDTO
        //List<ReviewResponse> reviews
) {
}
