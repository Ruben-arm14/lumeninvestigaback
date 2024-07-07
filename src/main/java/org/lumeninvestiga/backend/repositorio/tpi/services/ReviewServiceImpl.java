package org.lumeninvestiga.backend.repositorio.tpi.services;

import org.lumeninvestiga.backend.repositorio.tpi.dto.mapper.ReviewMapper;
import org.lumeninvestiga.backend.repositorio.tpi.dto.request.ReviewPostRequest;
import org.lumeninvestiga.backend.repositorio.tpi.dto.request.ReviewUpdateRequest;
import org.lumeninvestiga.backend.repositorio.tpi.dto.response.ReviewResponse;
import org.lumeninvestiga.backend.repositorio.tpi.entities.data.Article;
import org.lumeninvestiga.backend.repositorio.tpi.entities.user.Review;
import org.lumeninvestiga.backend.repositorio.tpi.entities.user.User;
import org.lumeninvestiga.backend.repositorio.tpi.exceptions.NotContentCommentException;
import org.lumeninvestiga.backend.repositorio.tpi.exceptions.NotFoundResourceException;
import org.lumeninvestiga.backend.repositorio.tpi.exceptions.ReferenceNotFoundException;
import org.lumeninvestiga.backend.repositorio.tpi.repositories.ArticleRepository;
import org.lumeninvestiga.backend.repositorio.tpi.repositories.ReviewRepository;
import org.lumeninvestiga.backend.repositorio.tpi.repositories.UserRepository;
import org.lumeninvestiga.backend.repositorio.tpi.utils.Utility;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewServiceImpl implements ReviewService{
    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final ArticleRepository articleRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository, UserRepository userRepository, ArticleRepository articleRepository) {
        this.reviewRepository = reviewRepository;
        this.userRepository = userRepository;
        this.articleRepository = articleRepository;
    }

    @Override
    @Transactional
    public Optional<ReviewResponse> saveReview(Long articleId, ReviewPostRequest request) {
        //TODO: Actualizar lógica una vez se tenga la autenticación JWT.
        Optional<User> userOptional = userRepository.findById(1L);
        Optional<Article> articleOptional = articleRepository.findById(articleId);
        if(userOptional.isEmpty() && articleOptional.isEmpty()) {
            throw new ReferenceNotFoundException("No se encontró referencia para el review");
        }
        if(request.comment().isBlank()) {
            throw new NotContentCommentException("No se encontró comentario en el review");
        }
        Review reviewRequest = new Review();
        reviewRequest.getUser().setId(1L);
        reviewRequest.getArticle().setId(articleId);
        reviewRequest.setComment(request.comment());
        reviewRepository.save(reviewRequest);
        return Optional.of(ReviewMapper.INSTANCE.toReviewResponse(reviewRequest));
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReviewResponse> getAllReviews(Pageable pageable) {
        int page = Utility.getCurrentPage(pageable);
        return reviewRepository.findAll(PageRequest.of(page, pageable.getPageSize())).stream()
                .map(ReviewMapper.INSTANCE::toReviewResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReviewResponse> getReviewsByArticleId(Long articleId) {
        Article article = articleRepository.findById(articleId)
                .orElseThrow(() -> new NotFoundResourceException("Artículo no encontrado"));

        // Obtenemos la lista de reviews del artículo
        return article.getReviews().stream()
                .map(ReviewMapper.INSTANCE::toReviewResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ReviewResponse> getReviewById(Long id) {
        Optional<Review> reviewOptional = reviewRepository.findById(id);
        if(reviewOptional.isEmpty()) {
            throw new NotFoundResourceException("No se encontró el recurso solicitado");
        }
        return Optional.of(ReviewMapper.INSTANCE.toReviewResponse(reviewOptional.get()));
    }

    @Override
    public void updateReviewComment(Long id, ReviewUpdateRequest request) {
        Optional<Review> reviewOptional = reviewRepository.findById(id);
        reviewOptional.ifPresentOrElse(reviewDb -> {
            reviewDb.setComment(request.comment());
        },
                () -> new NotFoundResourceException("No se encontró el recurso solicitado")
        );
    }

    @Override
    @Transactional
    public void deleteReviewById(Long id) {
        if(existReviewById(id)) {
            reviewRepository.deleteById(id);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existReviewById(Long id) {
        return reviewRepository.existsById(id);
    }
}
