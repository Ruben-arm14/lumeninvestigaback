package org.lumeninvestiga.backend.repositorio.tpi.entities;

import jakarta.persistence.*;

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
    private boolean liked;
    private String comment;
    @ManyToOne
    @JoinColumn(
            name = "user_id"
    )
    private User user;

    public Review() {
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
