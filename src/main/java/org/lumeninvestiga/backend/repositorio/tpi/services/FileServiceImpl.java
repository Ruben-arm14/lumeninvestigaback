package org.lumeninvestiga.backend.repositorio.tpi.services;

import org.lumeninvestiga.backend.repositorio.tpi.entities.data.File;
import org.lumeninvestiga.backend.repositorio.tpi.entities.data.MIME_TYPE;
import org.lumeninvestiga.backend.repositorio.tpi.repositories.FileRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class FileServiceImpl implements FileService{
    private final FileRepository fileRepository;

    public FileServiceImpl(FileRepository FileRepository) {
        this.fileRepository = FileRepository;
    }

    @Override
    @Transactional
    public Optional<File> saveFile(MultipartFile file) {
        File fileDb = new File();
        return Optional.of(fileRepository.save(Objects.requireNonNull(
                convertMultipartFileToFile(file, fileDb))));
    }

    //TODO: Continuar después de armar la autenticación.
    @Override
    @Transactional
    public Optional<File> saveFileToUser(Long id, MultipartFile multipartFile) {
        return Optional.empty();
    }

    @Override
    @Transactional(readOnly = true)
    public List<File> getAllFiles() {
        return fileRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<File> getFileById(Long id) {
        return fileRepository.findById(id);
    }

    @Override
    public Optional<File> getFileByName(String name) {
        return fileRepository.findByName(name);
    }

    @Override
    @Transactional
    public void deleteFileById(Long id) {
        fileRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existFileById(Long id) {
        return fileRepository.existsById(id);
    }

    private File convertMultipartFileToFile(MultipartFile file, File fileDb) {
        if(!file.isEmpty()) {
            try {
                fileDb.setName(file.getOriginalFilename());
                fileDb.setMimeType(file.getContentType());
                fileDb.setSize(file.getSize());
                fileDb.setData(file.getBytes());
                fileDb.setCreatedDate(LocalDateTime.now());
                return fileDb;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }
}
