package org.lumeninvestiga.backend.repositorio.tpi.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "storable_items"
)
public class StorableItem {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    private String name;
    private Long size;
    private LocalDateTime createdDate;
    @ManyToOne
    @JoinColumn(
            name = "user_id"
    )
    private User user;

    public StorableItem() {
        this.name = "";
        this.size = 0L;
        this.createdDate = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
