package org.lumeninvestiga.backend.repositorio.tpi.services;

import org.lumeninvestiga.backend.repositorio.tpi.dto.mapper.ArticleMapper;
import org.lumeninvestiga.backend.repositorio.tpi.dto.mapper.FileMapper;
import org.lumeninvestiga.backend.repositorio.tpi.dto.response.ArticleResponse;
import org.lumeninvestiga.backend.repositorio.tpi.entities.data.Article;
import org.lumeninvestiga.backend.repositorio.tpi.entities.data.File;
import org.lumeninvestiga.backend.repositorio.tpi.repositories.ArticleRepository;
import org.lumeninvestiga.backend.repositorio.tpi.utils.PDFAcademicExtractor;
import org.lumeninvestiga.backend.repositorio.tpi.utils.Utility;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ArticleServiceImpl implements ArticleService{
    private final ArticleRepository articleRepository;
    private final PDFAcademicExtractor pdfAcademicExtractor;

    public ArticleServiceImpl(ArticleRepository articleRepository, PDFAcademicExtractor pdfAcademicExtractor) {
        this.articleRepository = articleRepository;
        this.pdfAcademicExtractor = pdfAcademicExtractor;
    }

    @Override
    @Transactional
    public Optional<ArticleResponse> saveArticle(MultipartFile multipartFile) {
        if(multipartFile.isEmpty()) {
            //TODO: INVALID_RESOURCE_EXCEPTION
            throw new RuntimeException();
        }
        Article articleDb = new Article();
        try {
            articleDb.setName(multipartFile.getOriginalFilename());
            articleDb.setSize(multipartFile.getSize());
            articleDb.setCreatedDate(LocalDateTime.now());
            articleDb.setMimeType(multipartFile.getContentType());
            // No es necesario guardar el byte[]
            articleDb.setData(multipartFile.getBytes());

            List<String> valueList = pdfAcademicExtractor.readArticleByBytes(multipartFile.getBytes());
            articleDb.getArticleDetail().setTitle(valueList.get(1));
            articleDb.getArticleDetail().setAuthor(valueList.get(2));
            articleDb.getArticleDetail().setResume(valueList.get(3));
            articleDb.getArticleDetail().setKeywords(stringToSet(valueList.get(4)));
            //TODO: Falta area, subArea, period(Consultarlo), ods, advisor. (Obtenerlo del ficha de investigación)

            articleRepository.save(articleDb);
        } catch (IOException e) {
            //TODO: SAVING_ERROR EXCEPTION
            throw new RuntimeException(e);
        }
        return Optional.of(ArticleMapper.INSTANCE.toArticleResponse(articleDb));
    }

    @Override
    @Transactional(readOnly = true)
    public List<ArticleResponse> getAllArticles(Pageable pageable) {
        int page = Utility.getCurrentPage(pageable);
        return articleRepository.findAll(PageRequest.of(page, pageable.getPageSize())).stream()
                .map(ArticleMapper.INSTANCE::toArticleResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ArticleResponse> getArticleById(Long id) {
        Optional<Article> articleOptional = articleRepository.findById(id);
        if(articleOptional.isEmpty()) {
            //TODO: NOTFOUND_RESOURCE_EXCEPTION
            throw new RuntimeException();
        }
        return Optional.of(ArticleMapper.INSTANCE.toArticleResponse(articleOptional.get()));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ArticleResponse> getArticleByName(String name) {
        Optional<Article> articleOptional = articleRepository.findByName(name);
        if(articleOptional.isEmpty()) {
            //TODO: NOTFOUND_RESOURCE_EXCEPTION
            throw new RuntimeException();
        }
        return Optional.of(ArticleMapper.INSTANCE.toArticleResponse(articleOptional.get()));
    }

    @Override
    @Transactional
    public void deleteArticleById(Long id) {
        if(existArticleById(id)) {
            articleRepository.deleteById(id);
        }
    }

    @Override
    @Transactional
    public boolean existArticleById(Long id) {
        return articleRepository.existsById(id);
    }

    private Set<String> stringToSet(String keywords) {
        if (keywords == null || keywords.isBlank()) {
            //TODO:
            throw new RuntimeException();
        }
        return Stream.of(keywords.split(","))
                .map(String::trim) // Elimina espacios en blanco al principio y al final de cada palabra
                .collect(Collectors.toSet());
    }
}
