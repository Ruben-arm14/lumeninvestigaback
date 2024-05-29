package org.lumeninvestiga.backend.repositorio.tpi.services;

import org.lumeninvestiga.backend.repositorio.tpi.entities.data.Folder;

import java.util.List;
import java.util.Optional;

public interface FolderService {
    Optional<Folder> saveFolder(Folder folder);
    Optional<Folder> saveFolderToUser(Long id, Folder folder);
    List<Folder> getAllFolders();
    Optional<Folder> getFolderById(Long id);
    void deleteFolderById(Long id);
    boolean existFolderById(Long id);
}
