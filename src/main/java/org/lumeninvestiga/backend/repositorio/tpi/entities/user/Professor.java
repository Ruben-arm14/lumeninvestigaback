package org.lumeninvestiga.backend.repositorio.tpi.entities.user;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(
        name = "professors"
)
public class Professor extends User{
    @ManyToMany(
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.REFRESH,
                    CascadeType.REMOVE
            }
    )
    @JoinTable(
            name = "professor_course",
            joinColumns = @JoinColumn(
                    name = "professor_id"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "course_id"
            ),
            uniqueConstraints = @UniqueConstraint(
                    columnNames = {"professor_id", "course_id"}
            )
    )
    private List<Course> courses;

    public Professor() {
        super();
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
}
