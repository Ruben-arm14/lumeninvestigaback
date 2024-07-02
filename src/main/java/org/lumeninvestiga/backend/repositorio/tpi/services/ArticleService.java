package org.lumeninvestiga.backend.repositorio.tpi.services;

import org.lumeninvestiga.backend.repositorio.tpi.dto.response.ArticleResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface ArticleService {
    Optional<ArticleResponse> saveArticle(List<MultipartFile> multipartFile);
    List<ArticleResponse> getAllArticles(Pageable pageable);
    Optional<ArticleResponse> getArticleById(Long id);
    Optional<ArticleResponse> getArticleByName(String name);
    void deleteArticleById(Long id);
    boolean existArticleById(Long id);
}
