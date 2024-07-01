package org.lumeninvestiga.backend.repositorio.tpi.services;

import org.lumeninvestiga.backend.repositorio.tpi.dto.response.FileResponse;
import org.lumeninvestiga.backend.repositorio.tpi.entities.data.File;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

public interface FileService {
    Optional<FileResponse> saveFile(MultipartFile multipartFile);
    Optional<FileResponse> saveFileToUser(Long id, MultipartFile multipartFile);
    List<FileResponse> getAllFiles(Pageable pageable);
    Optional<FileResponse> getFileById(Long id);
    Optional<FileResponse> getFileByName(String name);
    void deleteFileById(Long id);
    boolean existFileById(Long id);
}
