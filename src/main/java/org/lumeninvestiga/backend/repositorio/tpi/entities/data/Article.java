package org.lumeninvestiga.backend.repositorio.tpi.entities.data;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(
        name = "articles"
)
public class Article extends File{
    @OneToOne(
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            mappedBy = "article"
    )
    private ArticleDetail articleDetail;
}
