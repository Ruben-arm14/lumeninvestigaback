package org.lumeninvestiga.backend.repositorio.tpi.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.lumeninvestiga.backend.repositorio.tpi.utils.MethodList;

@Entity
@Table(
        name = "user_details"
)
public class UserDetail {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    private String name;
    private String lastName;
    private String emailAddress;
    //private Image imageProfile;
    @OneToOne
    @JoinColumn(
            name = "id_user"
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
