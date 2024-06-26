package org.lumeninvestiga.backend.repositorio.tpi.entities.data;

import jakarta.persistence.*;

@Entity
@Table(
        name = "articles"
)
public class Article extends File{
    @Column(name = "like_count", nullable = false)
    private Integer likeCount;

    @OneToOne(
            targetEntity = ArticleDetail.class,
            cascade = {
              CascadeType.PERSIST,
              CascadeType.REMOVE,
              CascadeType.REFRESH
            }
    )
    private ArticleDetail articleDetail;

    public Article() {
        this.likeCount = 0;
        this.articleDetail = new ArticleDetail();
    }

    public void incrementLikeCount() {
        this.likeCount++;
    }

    public void decrementLikeCount() {
        if(this.likeCount > 0) {
            this.likeCount--;
        }
    }
}
