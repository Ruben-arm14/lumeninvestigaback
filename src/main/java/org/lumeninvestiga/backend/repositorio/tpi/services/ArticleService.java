package org.lumeninvestiga.backend.repositorio.tpi.services;

import jakarta.servlet.http.HttpServletRequest;
import org.lumeninvestiga.backend.repositorio.tpi.dto.response.ArticleResponse;
import org.lumeninvestiga.backend.repositorio.tpi.dto.response.CommentDTO;
import org.lumeninvestiga.backend.repositorio.tpi.entities.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface ArticleService {
    Optional<ArticleResponse> saveArticle(List<MultipartFile> multipartFiles, HttpServletRequest httpRequest);
    List<ArticleResponse> getAllArticles(Pageable pageable);
    List<ArticleResponse> getAllArticlesByKeyword(Pageable pageable, String keyword);
    Page<ArticleResponse> searchArticles(Pageable pageable, String area, String subArea,
                                         String period, String ods, String title,
                                         String author, String advisor, String resume,
                                         String keywords);
    Optional<ArticleResponse> getArticleById(Long id);
    Optional<ArticleResponse> getArticleByName(String name);
    void deleteArticleById(Long id);
    boolean existArticleById(Long id);
    CommentDTO addComment(Long articleId, CommentDTO commentDTO, User user);
}
