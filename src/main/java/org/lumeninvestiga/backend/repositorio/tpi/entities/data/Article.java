package org.lumeninvestiga.backend.repositorio.tpi.entities.data;

import jakarta.persistence.*;
import org.lumeninvestiga.backend.repositorio.tpi.entities.user.Review;

import java.util.List;

@Entity
@Table(
        name = "articles"
)
public class Article extends File{
    @Column(name = "like_count", nullable = false)
    private Integer likeCount;

    @OneToMany(
            targetEntity = Review.class,
            cascade = {
                    CascadeType.REFRESH,
                    CascadeType.REMOVE
            },
            fetch = FetchType.LAZY,
            mappedBy = "article"
    )
    private List<Review> reviews;

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
        super();
        this.likeCount = 0;
        this.articleDetail = new ArticleDetail();
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public ArticleDetail getArticleDetail() {
        return articleDetail;
    }

    public void setArticleDetail(ArticleDetail articleDetail) {
        this.articleDetail = articleDetail;
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
