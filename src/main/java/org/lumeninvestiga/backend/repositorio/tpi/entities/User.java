package org.lumeninvestiga.backend.repositorio.tpi.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(
        strategy = InheritanceType.TABLE_PER_CLASS
)
@Table(
        name = "users"
)
public class User {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_seq"
    )
    @SequenceGenerator(
            name = "user_seq",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    private Long id;
    @OneToOne(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            mappedBy = "user"
    )
    private UserDetail userDetail;
    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            mappedBy = "user"
    )
    private List<Review> reviews;
    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            mappedBy = "user"
    )
    private List<StorableItem> storableItem;

    public User() {
        this.reviews = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public UserDetail getUserDetail() {
        return userDetail;
    }

    public void setUserDetail(UserDetail userDetail) {
        this.userDetail = userDetail;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public List<StorableItem> getStorableItem() {
        return storableItem;
    }

    public void setStorableItem(List<StorableItem> storableItem) {
        this.storableItem = storableItem;
    }
}
