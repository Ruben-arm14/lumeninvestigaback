package org.lumeninvestiga.backend.repositorio.tpi.controllers;

import org.lumeninvestiga.backend.repositorio.tpi.services.FileService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @DeleteMapping
    public ResponseEntity<?> deleteFile(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}
