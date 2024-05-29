package org.lumeninvestiga.backend.repositorio.tpi.entities.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
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
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
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
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            mappedBy = "user"
    )
    @JsonManagedReference
    private List<Review> reviews;
    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            mappedBy = "user"
    )
    @JsonManagedReference
    private List<StorableItem> storableItems;
    @ManyToMany
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(
                    name = "users_id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "roles_id"
            ),
            uniqueConstraints = @UniqueConstraint(
                    columnNames = {"users_id", "roles_id"}
            )
    )
    @JsonManagedReference
    private List<Role> roles;
    @Column(nullable = false)
    private boolean enabled;
    @JsonIgnore
    @JsonProperty(
            access = JsonProperty.Access.WRITE_ONLY
    )
    private boolean admin;

    public User() {
        this.enabled = true;
        this.reviews = new ArrayList<>();
        this.storableItems = new ArrayList<>();
        this.roles = new ArrayList<>();
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

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
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

    public void addRole(Role role) {
        this.roles.add(role);
        role.getUsers().add(this);
    }

    public void removeRole(Role role) {
        this.roles.remove(role);
        role.getUsers().add(this);
    }
}
