package org.lumeninvestiga.backend.repositorio.tpi.dto.response;

import java.time.LocalDateTime;

public class CommentDTO {
    private String username;
    private String comment;
    private LocalDateTime createdDate;
    private Integer likeCount;

    public CommentDTO() {}

    public CommentDTO(String username, String comment, LocalDateTime createdDate, Integer likeCount) {
        this.username = username;
        this.comment = comment;
        this.createdDate = createdDate;
        this.likeCount = likeCount;
    }

    // Getters y setters

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public Integer getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }
}
