package org.lumeninvestiga.backend.repositorio.tpi.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(
        name = "students"
)
public class Student extends User{
    public Student() {
        super();
    }
}