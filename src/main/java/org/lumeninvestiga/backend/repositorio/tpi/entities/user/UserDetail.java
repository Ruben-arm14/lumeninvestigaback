package org.lumeninvestiga.backend.repositorio.tpi.entities.user;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(
        name = "user_details"
)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class UserDetail {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    @NotBlank
    @Size(min = 4, max = 12)
    @Column(nullable = false)
    private String name;
    @NotBlank
    @Size(min = 4, max = 12)
    @Column(nullable = false)
    private String lastName;
    @Column(
            nullable = false,
            unique = true
    )
    private String emailAddress;
    @Column(nullable = false)
    private String password;
    //private Image imageProfile;
    @OneToOne(
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            name = "id_user",
            nullable = false
    )
    @JsonBackReference
    private User user;

    public UserDetail() {
        this.name = "";
        this.lastName = "";
        this.emailAddress = "";
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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    //    public Image getImageProfile() {
//        return imageProfile;
//    }
//
//    public void setImageProfile(Image imageProfile) {
//        this.imageProfile = imageProfile;
//    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
