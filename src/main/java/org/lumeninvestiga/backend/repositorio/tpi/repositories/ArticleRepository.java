package org.lumeninvestiga.backend.repositorio.tpi.repositories;

import org.lumeninvestiga.backend.repositorio.tpi.entities.data.Article;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long>, JpaSpecificationExecutor<Article> {
    Optional<Article> findByName(String name);
    @Query("SELECT a FROM Article a WHERE a.articleDetail.title LIKE %:keyword%")
    List<Article> findByTitleContaining(Pageable pageable, @Param("keyword") String keyword);
}
