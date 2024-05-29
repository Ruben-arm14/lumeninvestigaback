package org.lumeninvestiga.backend.repositorio.tpi.repositories;

import org.lumeninvestiga.backend.repositorio.tpi.entities.data.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FileRepository extends JpaRepository<File, Long> {
}
