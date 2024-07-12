package org.lumeninvestiga.backend.repositorio.tpi.entities.user;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.lumeninvestiga.backend.repositorio.tpi.entities.data.SavedArticle;
import org.lumeninvestiga.backend.repositorio.tpi.entities.data.StorableItem;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_detail_id", referencedColumnName = "id")
    private UserDetail userDetail;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<StorableItem> storableItems = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<SavedArticle> savedArticles = new ArrayList<>();

    // Implementación de métodos de la interfaz UserDetails
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Implementación de este método si es necesario
        return null;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    // Métodos específicos de la clase User
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public UserDetail getUserDetail() {
        return userDetail;
    }

    public void setUserDetail(UserDetail userDetail) {
        this.userDetail = userDetail;
    }

    public List<StorableItem> getStorableItems() {
        return storableItems;
    }

    public void setStorableItems(List<StorableItem> storableItems) {
        this.storableItems = storableItems;
    }

    public void addStorableItem(StorableItem storableItem) {
        storableItems.add(storableItem);
        storableItem.setUser(this);
    }

    public void removeStorableItem(StorableItem storableItem) {
        storableItems.remove(storableItem);
        storableItem.setUser(null);
    }

    public List<SavedArticle> getSavedArticles() {
        return savedArticles;
    }

    public void setSavedArticles(List<SavedArticle> savedArticles) {
        this.savedArticles = savedArticles;
    }

    public void addSavedArticle(SavedArticle savedArticle) {
        savedArticles.add(savedArticle);
        savedArticle.setUser(this);
    }

    public void removeSavedArticle(SavedArticle savedArticle) {
        savedArticles.remove(savedArticle);
        savedArticle.setUser(null);
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
