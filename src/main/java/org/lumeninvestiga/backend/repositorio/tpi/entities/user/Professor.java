package org.lumeninvestiga.backend.repositorio.tpi.entities.user;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(
        name = "professors"
)
public class Professor extends User{
    @OneToOne(
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.REMOVE
            }
    )
    @JoinColumn(name="user_id")
    private User user;

    @ManyToMany
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
        this.courses = new ArrayList<>();
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    public void addCourse(Course course) {
        this.courses.add(course);
    }

    public void removeCourse(Course course) {
        this.courses.remove(course);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Professor professor = (Professor) object;
        return Objects.equals(user, professor.user) && Objects.equals(courses, professor.courses);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, courses);
    }
}
