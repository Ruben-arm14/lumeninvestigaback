package org.lumeninvestiga.backend.repositorio.tpi.services;

import org.lumeninvestiga.backend.repositorio.tpi.entities.data.Folder;
import org.lumeninvestiga.backend.repositorio.tpi.repositories.FolderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FolderServiceImpl implements FolderService{
    private final FolderRepository folderRepository;

    public FolderServiceImpl(FolderRepository folderRepository) {
        this.folderRepository = folderRepository;
    }

    @Override
    public Optional<Folder> saveFolder(Folder folder) {
        return Optional.of(folderRepository.save(folder));
    }

    //TODO: Continuar después de armar la autenticación.
    @Override
    public Optional<Folder> saveFolderToUser(Long id, Folder folder) {
        return Optional.empty();
    }

    @Override
    public List<Folder> getAllFolders() {
        return folderRepository.findAll();
    }

    @Override
    public Optional<Folder> getFolderById(Long id) {
        return folderRepository.findById(id);
    }

    @Override
    public void deleteFolderById(Long id) {
        folderRepository.deleteById(id);
    }

    @Override
    public boolean existFolderById(Long id) {
        return folderRepository.existsById(id);
    }
}
