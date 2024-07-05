package org.lumeninvestiga.backend.repositorio.tpi.services;

import org.lumeninvestiga.backend.repositorio.tpi.dto.mapper.ArticleMapper;
import org.lumeninvestiga.backend.repositorio.tpi.dto.response.ArticleResponse;
import org.lumeninvestiga.backend.repositorio.tpi.entities.data.Article;
import org.lumeninvestiga.backend.repositorio.tpi.entities.data.ODS_GOALS;
import org.lumeninvestiga.backend.repositorio.tpi.exceptions.InvalidDocumentFormatException;
import org.lumeninvestiga.backend.repositorio.tpi.exceptions.NotFoundResourceException;
import org.lumeninvestiga.backend.repositorio.tpi.exceptions.ResourceCountException;
import org.lumeninvestiga.backend.repositorio.tpi.exceptions.SavingErrorException;
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
    public Optional<ArticleResponse> saveArticle(List<MultipartFile> multipartFiles) {
        validateFiles(multipartFiles);

        MultipartFile articleFile = null;
        MultipartFile fichaFile = null;

        for (MultipartFile file : multipartFiles) {
            String fileName = file.getOriginalFilename().toLowerCase();
            if (fileName.startsWith("articulo") || fileName.startsWith("artículo")) {
                articleFile = file;
            } else if (fileName.startsWith("ficha")) {
                fichaFile = file;
            } else {
                throw new InvalidDocumentFormatException("Formato de archivo no reconocido: " + fileName);
            }
        }

        if (articleFile == null || fichaFile == null) {
            throw new ResourceCountException("Se esperan dos documentos, uno de tipo artículo y otro de tipo ficha");
        }

        Article articleDb = new Article();
        try {
            articleDb.setName(articleFile.getOriginalFilename());
            articleDb.setSize(articleFile.getSize());
            articleDb.setCreatedDate(LocalDateTime.now());
            articleDb.setMimeType(articleFile.getContentType());
            articleDb.setData(articleFile.getBytes());

            List<String> articleList = pdfAcademicExtractor.readArticleByBytes(articleFile.getBytes());
            List<String> fichaList = pdfAcademicExtractor.readFichaByBytes(fichaFile.getBytes());

            articleDb.getArticleDetail().setTitle(getTitle(articleList, fichaList));
            articleDb.getArticleDetail().setAuthor(articleList.get(1));
            articleDb.getArticleDetail().setAdvisor(""); // Implementar lógica para obtener el asesor
            articleDb.getArticleDetail().setResume(articleList.get(2));
            articleDb.getArticleDetail().setKeywords(articleList.get(3));

            articleDb.getArticleDetail().setArea(getArea(fichaList));
            articleDb.getArticleDetail().setSubArea(getSubArea(fichaList));
            articleDb.getArticleDetail().setODS(fichaList.get(3));

            articleRepository.save(articleDb);
        } catch (IOException e) {
            throw new SavingErrorException("Error al guardar el artículo", e);
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
    public List<ArticleResponse> getAllArticlesByKeyword(Pageable pageable, String keyword) {
        int page = Utility.getCurrentPage(pageable);
        return articleRepository.findByTitleContaining(PageRequest.of(page, pageable.getPageSize()), keyword)
                .stream()
                .map(ArticleMapper.INSTANCE::toArticleResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ArticleResponse> getArticleById(Long id) {
        Optional<Article> articleOptional = articleRepository.findById(id);
        if(articleOptional.isEmpty()) {
            throw new NotFoundResourceException("Entidad no encontrada");
        }
        return Optional.of(ArticleMapper.INSTANCE.toArticleResponse(articleOptional.get()));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ArticleResponse> getArticleByName(String name) {
        Optional<Article> articleOptional = articleRepository.findByName(name);
        if(articleOptional.isEmpty()) {
            throw new NotFoundResourceException("Entidad no encontrada");
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

    private void validateFiles(List<MultipartFile> files) {
        if (files.size() != 2 || files.isEmpty()) {
            throw new ResourceCountException("Se esperan dos documentos");
        }
    }

    private String getTitle(List<String> articleList, List<String> fichaList) {
        return articleList.get(0).equals("No encontrado") ? fichaList.get(0) : "No encontrado";
    }

    private String getArea(List<String> fichaList) {
        return fichaList.get(2).split(",")[0].trim();
    }

    private String getSubArea(List<String> fichaList) {
        return fichaList.get(2).split(",")[1].trim();
    }
}
