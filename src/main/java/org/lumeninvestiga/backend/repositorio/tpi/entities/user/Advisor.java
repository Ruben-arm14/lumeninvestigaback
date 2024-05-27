package org.lumeninvestiga.backend.repositorio.tpi.entities.user;

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
