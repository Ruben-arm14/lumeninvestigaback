package org.lumeninvestiga.backend.repositorio.tpi.entities.user;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.time.LocalDateTime;

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
    @Column(nullable = false)
    private boolean liked;
    @Column(nullable = false)
    private String comment;
    @Column(name="created_date",  nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdDate;

    @ManyToOne(targetEntity = User.class)
    @JsonBackReference
    private User user;

    public Review() {
        this.createdDate = LocalDateTime.now();
        this.liked = false;
        this.comment = "";
    }

    public Long getId() {
        return id;
    }

    public boolean isLiked() {
        return liked;
    }

    public void setLiked(boolean liked) {
        this.liked = liked;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
