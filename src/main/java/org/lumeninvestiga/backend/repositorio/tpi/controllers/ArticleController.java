package org.lumeninvestiga.backend.repositorio.tpi.controllers;

import org.lumeninvestiga.backend.repositorio.tpi.dto.request.ArticleUpdateRequest;
import org.lumeninvestiga.backend.repositorio.tpi.services.ArticleService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/articles")
public class ArticleController {
    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping
    public ResponseEntity<?> readAllArticles(@PageableDefault Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(articleService.getAllArticles(pageable));
    }

    @GetMapping("/search")
    public ResponseEntity<?> readAllArticlesByKeyword(@PageableDefault Pageable pageable, @RequestParam String keyword) {
        return ResponseEntity.status(HttpStatus.OK).body(articleService.getAllArticlesByKeyword(pageable, keyword));
    }

    @PostMapping(
            value = "/upload",
            consumes = "multipart/form-data",
            produces = "application/json")
    public ResponseEntity<?> uploadFiles(@RequestParam("files") List<MultipartFile> files) {
        articleService.saveArticle(files);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<?> readByName(@PathVariable String name) {
        return ResponseEntity.status(HttpStatus.FOUND).body(articleService.getArticleByName(name));
    }

    @GetMapping("/id/{article_id}")
    public ResponseEntity<?> readArticleById(@PathVariable("article_id") Long articleId) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(articleService.getArticleById(articleId));
    }

    @PutMapping("/id/{article_id}")
    public ResponseEntity<?> updateArticleById(
            @PathVariable("article_id") Long articleId,
            @RequestParam ArticleUpdateRequest request) {
        // TODO: Implement method in service layer to update article content
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @DeleteMapping("/id/{article_id}")
    public ResponseEntity<?> deleteArticleById(@PathVariable("article_id") Long articleId){
        articleService.deleteArticleById(articleId);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    // Consider removing this method if search functionality is implemented differently.
    @GetMapping("/search/name/{name}")
    public ResponseEntity<?> searchArticleByName(@PathVariable String name) {
        // TODO: Implement method in service layer to search article by name
        // Adjust to make searches from a frontend search component
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

//    @GetMapping("/search/all/{name}")
//    public ResponseEntity<?> searchAllArticleByName(@PathVariable String name) {
//        // TODO: Implement method in service layer to search articles by name
//        // Adjust to make searches from a frontend search component
//        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
//    }
}
