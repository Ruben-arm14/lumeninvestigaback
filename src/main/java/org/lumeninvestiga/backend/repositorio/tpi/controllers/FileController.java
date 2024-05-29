package org.lumeninvestiga.backend.repositorio.tpi.controllers;

import org.lumeninvestiga.backend.repositorio.tpi.entities.data.File;
import org.lumeninvestiga.backend.repositorio.tpi.services.FileService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@RestController
@RequestMapping("/files")
public class FileController {
    private final FileService fileService;

    public FileController(FileService FileService) {
        this.fileService = FileService;
    }

    @PostMapping
    public ResponseEntity<?> createFile(@RequestBody MultipartFile item) {
        return ResponseEntity.ok(fileService.saveFile(item));
    }

    @GetMapping
    public ResponseEntity<?> getAllFiles() {
        return ResponseEntity.status(HttpStatus.OK).body(fileService.getAllFiles());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> readFileById(@PathVariable Long id) {
        return ResponseEntity.ok(fileService.getFileById(id));
    }

    //Solo se puede modificar el nombre
    @PutMapping("/{id}")
    public ResponseEntity<?> updateFileById(@PathVariable Long id, @RequestBody File file) {
        Optional<File> fileOptional = fileService.getFileById(id);
        fileOptional.ifPresent(fileDb -> {
            fileDb.setName(file.getName());
        });
        return ResponseEntity.status(HttpStatus.CREATED).body(fileService.saveFile((MultipartFile) fileOptional.get()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFileById(@PathVariable Long id){
        Optional<File> fileOptional = fileService.getFileById(id);
        if(fileOptional.isPresent()) {
            fileService.deleteFileById(id);
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
