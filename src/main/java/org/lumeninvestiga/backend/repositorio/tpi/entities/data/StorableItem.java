package org.lumeninvestiga.backend.repositorio.tpi.entities.data;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import org.lumeninvestiga.backend.repositorio.tpi.entities.user.User;

import java.time.LocalDateTime;

@Entity
@Inheritance(
        strategy = InheritanceType.JOINED
)
@Table(
        name = "storable_items"
)
public class StorableItem {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "storableItem_seq"
    )
    @SequenceGenerator(
            name = "storableItem_seq",
            sequenceName = "storableItem_sequence",
            allocationSize = 1
    )
    private Long id;
    private String name;
    private Long size;
    private LocalDateTime createdDate;
    @ManyToOne
    @JoinColumn(
            name = "user_id"
    )
    @JsonBackReference
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
