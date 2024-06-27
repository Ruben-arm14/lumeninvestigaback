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
            generator = "storable_item_seq"
    )
    @SequenceGenerator(
            name = "storable_item_seq",
            sequenceName = "storable_item_sequence",
            allocationSize = 1
    )
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private Long size;
    @Column(name="created_date",  nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdDate;

    @ManyToOne(targetEntity = User.class)
    @JsonBackReference
    private User user;

    public StorableItem() {
        this.name = "";
        this.size = 0L;
        this.createdDate = LocalDateTime.now();
    }

    public void setId(Long id) {
        this.id = id;
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
