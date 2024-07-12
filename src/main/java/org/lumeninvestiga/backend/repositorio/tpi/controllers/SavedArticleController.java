package org.lumeninvestiga.backend.repositorio.tpi.controllers;

import org.lumeninvestiga.backend.repositorio.tpi.entities.data.SavedArticle;
import org.lumeninvestiga.backend.repositorio.tpi.services.SavedArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/saved-articles")
public class SavedArticleController {

    @Autowired
    private SavedArticleService savedArticleService;

    @GetMapping
    public ResponseEntity<List<SavedArticle>> getSavedArticles(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        String username = user.getUsername();
        List<SavedArticle> savedArticles = savedArticleService.getSavedArticlesByUsername(username);
        return ResponseEntity.ok(savedArticles);
    }

    @PostMapping
    public ResponseEntity<SavedArticle> saveArticle(@RequestBody SavedArticle savedArticle, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        String username = user.getUsername();
        SavedArticle saved = savedArticleService.saveArticle(savedArticle, username);
        return ResponseEntity.ok(saved);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSavedArticle(@PathVariable Long id, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        String username = user.getUsername();
        savedArticleService.deleteSavedArticle(id, username);
        return ResponseEntity.noContent().build();
    }
}
