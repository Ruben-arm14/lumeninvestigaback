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
@RequestMapping("/api/article")
public class ArticleController {
    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping
    public ResponseEntity<?> readAllArticles(@PageableDefault Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(articleService.getAllArticles(pageable));
    }

    @PostMapping(
            value = "/upload",
            consumes = "multipart/form-data",
            produces = "application/json")
    public ResponseEntity<?> uploadFiles(@RequestParam("file") List<MultipartFile> files) {
        articleService.saveArticle(files);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/{name}")
    public ResponseEntity<?> readByName(@PathVariable String name) {
        return ResponseEntity.status(HttpStatus.FOUND).body(articleService.getArticleByName(name));
        //return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/{article_id}")
    public ResponseEntity<?> readArticleById(@PathVariable("article_id") Long articleId) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(articleService.getArticleById(articleId));
    }

    @PutMapping("/{article_id}")
    public ResponseEntity<?> updateArticleById(
            @PathVariable("article_id") Long articleId,
            @RequestParam ArticleUpdateRequest request) {
        //TODO: Hacer un método en la capa service para actualizar el contenido del artículo
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @DeleteMapping("/{article_id}")
    public ResponseEntity<?> deleteArticleById(@PathVariable("article_id") Long articleId){
        articleService.deleteArticleById(articleId);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    //NOTE: considerar eliminar método porque la búsqueda de hace de un todo de artículos.
    @GetMapping("/search/{name}")
    public ResponseEntity<?> searchArticleByName(@PathVariable String name) {
        //TODO: Hacer un método en la capa service para buscar un artículo por nombre
        // adecuarlo para que las busquedas sean por un buscador del frontend
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @GetMapping("/search/{name}")
    public ResponseEntity<?> searchAllArticleByName(@PathVariable String name) {
        //TODO: Hacer un método en la capa service para buscar artículos por nombre
        // adecuarlo para que las busquedas sean por un buscador del frontend
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}
