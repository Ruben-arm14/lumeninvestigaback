package org.lumeninvestiga.backend.repositorio.tpi.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.lumeninvestiga.backend.repositorio.tpi.dto.request.ArticleUpdateRequest;
import org.lumeninvestiga.backend.repositorio.tpi.dto.response.ArticleResponse;
import org.lumeninvestiga.backend.repositorio.tpi.dto.response.CommentDTO;
import org.lumeninvestiga.backend.repositorio.tpi.entities.user.User;
import org.lumeninvestiga.backend.repositorio.tpi.exceptions.NotFoundResourceException;
import org.lumeninvestiga.backend.repositorio.tpi.repositories.UserRepository;
import org.lumeninvestiga.backend.repositorio.tpi.security.jwt.JwtService;
import org.lumeninvestiga.backend.repositorio.tpi.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/articles")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public ResponseEntity<List<ArticleResponse>> readAllArticles(@PageableDefault Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(articleService.getAllArticles(pageable));
    }

    @GetMapping("/search")
    public ResponseEntity<List<ArticleResponse>> readAllArticlesByKeyword(
            @PageableDefault Pageable pageable,
            @RequestParam String keyword) {
        return ResponseEntity.status(HttpStatus.OK).body(articleService.getAllArticlesByKeyword(pageable, keyword));
    }

    @GetMapping("/v2/search")
    public ResponseEntity<Page<ArticleResponse>> searchArticles(
            @PageableDefault Pageable pageable,
            @RequestParam(required = false) String area,
            @RequestParam(required = false) String subArea,
            @RequestParam(required = false) String period,
            @RequestParam(required = false) String ods,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String author,
            @RequestParam(required = false) String advisor,
            @RequestParam(required = false) String resume,
            @RequestParam(required = false) String keywords) {
        return ResponseEntity.status(HttpStatus.OK).body(
                articleService.searchArticles(pageable, area, subArea, period, ods, title, author, advisor, resume, keywords)
        );
    }

    @PostMapping(
            value = "/upload",
            consumes = "multipart/form-data",
            produces = "application/json")
    public ResponseEntity<?> uploadFiles(@RequestParam("files") List<MultipartFile> files, HttpServletRequest httpRequest) {
        articleService.saveArticle(files, httpRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<ArticleResponse> readByName(@PathVariable String name) {
        return ResponseEntity.status(HttpStatus.FOUND).body(articleService.getArticleByName(name).orElseThrow(() -> new NotFoundResourceException("Articulo no encontrado")));
    }

    @GetMapping("/{article_id}")
    public ResponseEntity<ArticleResponse> readArticleById(@PathVariable("article_id") Long articleId) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(articleService.getArticleById(articleId).orElseThrow(() -> new NotFoundResourceException("Articulo no encontrado")));
    }

    @PutMapping("/{article_id}")
    public ResponseEntity<?> updateArticleById(
            @PathVariable("article_id") Long articleId,
            @Valid @RequestBody ArticleUpdateRequest request) {
        // TODO: Implement method in service layer to update article content
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @DeleteMapping("/{article_id}")
    public ResponseEntity<?> deleteArticleById(@PathVariable("article_id") Long articleId) {
        articleService.deleteArticleById(articleId);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @PostMapping("/{id}/comments")
    public ResponseEntity<CommentDTO> addComment(@PathVariable Long id, @RequestBody CommentDTO commentDTO, HttpServletRequest request) {
        String token = jwtService.extractTokenFromRequest(request);
        String username = jwtService.getUsernameFromToken(token);

        User user = userRepository.findByUsername(username).orElseThrow(() -> new NotFoundResourceException("User not found"));

        CommentDTO savedComment = articleService.addComment(id, commentDTO, user);
        return ResponseEntity.ok(savedComment);
    }
}
