package org.lumeninvestiga.backend.repositorio.tpi.entities.user;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.lumeninvestiga.backend.repositorio.tpi.entities.data.StorableItem;

import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(
        strategy = InheritanceType.JOINED
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
    @JsonManagedReference
    private UserDetail userDetail;
    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "user"
    )
    @JsonManagedReference
    private List<Review> reviews;
    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            mappedBy = "user"
    )
    @JsonManagedReference
    private List<StorableItem> storableItems;

    public User() {
        this.reviews = new ArrayList<>();
        this.storableItems = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public UserDetail getUserDetail() {
        return userDetail;
    }

    public void setUserDetail(UserDetail userDetail) {
        this.userDetail = userDetail;
        userDetail.setUser(this);
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public List<StorableItem> getStorableItems() {
        return storableItems;
    }

    public void setStorableItems(List<StorableItem> storableItems) {
        this.storableItems = storableItems;
    }

    public void addReview(Review review) {
        this.reviews.add(review);
        review.setUser(this);

    }

    public void removeReview(Review review) {
        this.reviews.remove(review);
        review.setUser(null);

    }

    public void addStorableItem(StorableItem storableItem) {
        this.storableItems.add(storableItem);
        storableItem.setUser(this);
    }

    public void removeStorableItem(StorableItem storableItem) {
        this.storableItems.remove(storableItem);
        storableItem.setUser(null);
    }
}
