package org.lumeninvestiga.backend.repositorio.tpi.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(
        name = "advisors"
)
public class Advisor extends User {
    public Advisor() {
        super();
    }
}
