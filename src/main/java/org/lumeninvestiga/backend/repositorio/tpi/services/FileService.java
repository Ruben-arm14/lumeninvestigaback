package org.lumeninvestiga.backend.repositorio.tpi.services;

import org.lumeninvestiga.backend.repositorio.tpi.entities.data.File;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface FileService {
    Optional<File> saveFile(MultipartFile multipartFile);
    Optional<File> saveFileToUser(Long id, MultipartFile multipartFile);
    List<File> getAllFiles();
    Optional<File> getFileById(Long id);
    Optional<File> getFileByName(String name);
    void deleteFileById(Long id);
    boolean existFileById(Long id);
}
