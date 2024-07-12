package org.lumeninvestiga.backend.repositorio.tpi.services;

import org.lumeninvestiga.backend.repositorio.tpi.entities.data.SavedArticle;
import org.lumeninvestiga.backend.repositorio.tpi.entities.user.User;
import org.lumeninvestiga.backend.repositorio.tpi.repositories.SavedArticleRepository;
import org.lumeninvestiga.backend.repositorio.tpi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SavedArticleService {

    @Autowired
    private SavedArticleRepository savedArticleRepository;

    @Autowired
    private UserRepository userRepository;

    public List<SavedArticle> getSavedArticlesByUsername(String username) {
        return savedArticleRepository.findByUserUsername(username);
    }

    public SavedArticle saveArticle(SavedArticle savedArticle, String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        savedArticle.setUser(user);
        return savedArticleRepository.save(savedArticle);
    }

    public void deleteSavedArticle(Long id, String username) {
        SavedArticle savedArticle = savedArticleRepository.findByIdAndUserUsername(id, username)
                .orElseThrow(() -> new UsernameNotFoundException("Saved article not found"));
        savedArticleRepository.delete(savedArticle);
    }
}
