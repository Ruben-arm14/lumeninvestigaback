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
//        if (multipartFiles.size() <= 2 && !multipartFiles.isEmpty()) {
//            throw new ResourceCountException("Se espera dos documentos");
//        }
        MultipartFile articleFile = multipartFiles.get(0);
        MultipartFile fichaFile = multipartFiles.get(1);

        // Validar el nombre del primer archivo (Artículo)
        String articleFileName = articleFile.getOriginalFilename().toLowerCase();
        if (!articleFileName.startsWith("articulo") && !articleFileName.startsWith("artículo")) {
            throw new InvalidDocumentFormatException("Error en el formato del nombre de articulo");
        }

        // Validar el nombre del segundo archivo (Ficha)
        String fichaFileName = fichaFile.getOriginalFilename().toLowerCase();
        if (!fichaFileName.startsWith("ficha")) {
            throw new InvalidDocumentFormatException("Error en el formato del nombre de ficha");
        }
        Article articleDb = new Article();
        try {
            articleDb.setName(articleFile.getOriginalFilename());
            articleDb.setSize(articleFile.getSize());
            articleDb.setCreatedDate(LocalDateTime.now());
            articleDb.setMimeType(articleFile.getContentType());
            // No es necesario guardar el byte[]
            articleDb.setData(articleFile.getBytes());

            // Extractor para el artículo de investigación
            List<String> valueList = pdfAcademicExtractor.readArticleByBytes(articleFile.getBytes());
            articleDb.getArticleDetail().setTitle(valueList.get(0));
            articleDb.getArticleDetail().setAuthor(valueList.get(1));
            //TODO: implementar la logica para obtener el asesor en el artículo de investigación.
            articleDb.getArticleDetail().setAdvisor("");
            articleDb.getArticleDetail().setResume(valueList.get(2).replace("\r\n", ""));
            articleDb.getArticleDetail().setKeywords(stringToSet(valueList.get(3)
                    .replace("\r\n", "")
                    .replace(".", "")
                    )
            );
            List<String> valueList1 = pdfAcademicExtractor.readFichaByBytes(fichaFile.getBytes());
            //TODO: Falta area, subArea, period(Consultarlo), ods, advisor. (Obtenerlo del ficha de investigación)
            //Se realiza un split(",") separando usando de limitador el , y elegimos la posicion de cada palabra
            articleDb.getArticleDetail().setArea(valueList1.get(2).split(",")[0].trim());
            articleDb.getArticleDetail().setSubArea(valueList1.get(2).split(",")[1].trim());
            //TODO: Revisar el atributo porque se puede agregar más de un ODS
            articleDb.getArticleDetail().setODS(ODS_GOALS.FIRST);

            articleRepository.save(articleDb);
        } catch (IOException e) {
            //TODO: Revisar comentario
            throw new SavingErrorException("Error al guardar el articulo");
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
}
