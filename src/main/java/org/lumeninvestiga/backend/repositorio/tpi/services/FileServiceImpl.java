package org.lumeninvestiga.backend.repositorio.tpi.services;

import org.lumeninvestiga.backend.repositorio.tpi.dto.mapper.FileMapper;
import org.lumeninvestiga.backend.repositorio.tpi.dto.mapper.UserMapper;
import org.lumeninvestiga.backend.repositorio.tpi.dto.response.FileResponse;
import org.lumeninvestiga.backend.repositorio.tpi.entities.data.Article;
import org.lumeninvestiga.backend.repositorio.tpi.entities.data.File;
import org.lumeninvestiga.backend.repositorio.tpi.entities.user.User;
import org.lumeninvestiga.backend.repositorio.tpi.repositories.FileRepository;
import org.lumeninvestiga.backend.repositorio.tpi.utils.Utility;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public Optional<FileResponse> saveFile(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            // TODO: INVALID_RESOURCE_EXCEPTION
            throw new RuntimeException();
        }
        File fileDb = new File();
        // TODO: **Se confirmó el acceso del Article con atributos super.**
        try {
            fileDb.setName(file.getOriginalFilename());
            fileDb.setMimeType(file.getContentType());
            fileDb.setSize(file.getSize());
            fileDb.setData(file.getBytes());
            fileDb.setCreatedDate(LocalDateTime.now());
            fileRepository.save(fileDb);
            return Optional.of(FileMapper.INSTANCE.toFileResponse(fileDb));
        } catch (IOException e) {
            // TODO: SAVING_ERROR EXCEPTION
            throw new RuntimeException();
        }
    }

    //TODO: Continuar después de armar la autenticación.
    @Override
    @Transactional
    public Optional<FileResponse> saveFileToUser(Long id, MultipartFile file) {
        return Optional.empty();
    }

    @Override
    @Transactional(readOnly = true)
    public List<FileResponse> getAllFiles(Pageable pageable) {
        int page = Utility.getCurrentPage(pageable);
        return fileRepository.findAll(PageRequest.of(page, pageable.getPageSize())).stream()
                .map(FileMapper.INSTANCE::toFileResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<FileResponse> getFileById(Long id) {
        Optional<File> fileOptional = fileRepository.findById(id);
        if(fileOptional.isEmpty()) {
            //TODO: NOTFOUND_RESOURCE_EXCEPTION
            throw new RuntimeException();
        }
        return Optional.of(FileMapper.INSTANCE.toFileResponse(fileOptional.get()));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<FileResponse> getFileByName(String name) {
        //TODO: Verificar si se puede buscar por nombre desde File.
        Optional<File> fileOptional = fileRepository.findByName(name);
        if(fileOptional.isEmpty()) {
            //TODO: NOTFOUND_RESOURCE_EXCEPTION
            throw new RuntimeException();
        }
        return Optional.of(FileMapper.INSTANCE.toFileResponse(fileOptional.get()));
    }

    @Override
    @Transactional
    public void deleteFileById(Long id) {
        if(existFileById(id)) {
            fileRepository.deleteById(id);
        }
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
