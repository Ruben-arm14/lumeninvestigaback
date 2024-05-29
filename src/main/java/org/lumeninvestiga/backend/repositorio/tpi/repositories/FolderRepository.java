package org.lumeninvestiga.backend.repositorio.tpi.repositories;

import org.lumeninvestiga.backend.repositorio.tpi.entities.data.Folder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FolderRepository extends JpaRepository<Folder, Long> {
}
