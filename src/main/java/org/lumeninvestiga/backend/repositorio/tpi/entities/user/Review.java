package org.lumeninvestiga.backend.repositorio.tpi.entities.user;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import org.lumeninvestiga.backend.repositorio.tpi.entities.data.Article;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(
        name = "reviews"
)
public class Review {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    @Column(name = "like_count", nullable = false)
    private Integer likeCount;
    @Column(nullable = false)
    private String comment;
    @Column(name="created_date",  nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdDate;

    @ManyToOne(targetEntity = User.class)
    @JsonBackReference
    private User user;

    @ManyToOne(targetEntity = Article.class)
    private Article article;

    public Review() {
        this.createdDate = LocalDateTime.now();
        this.likeCount = 0;
        this.comment = "";
        this.article = new Article();
    }

    public Long getId() {
        return id;
    }

    public Integer getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public void incrementLikeCount() {
        this.likeCount++;
    }

    public void decrementLikeCount() {
        if(this.likeCount > 0) {
            this.likeCount--;
        }
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Review review = (Review) object;
        return Objects.equals(id, review.id) && Objects.equals(likeCount, review.likeCount) && Objects.equals(comment, review.comment) && Objects.equals(createdDate, review.createdDate) && Objects.equals(user, review.user) && Objects.equals(article, review.article);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, likeCount, comment, createdDate, user, article);
    }
}
