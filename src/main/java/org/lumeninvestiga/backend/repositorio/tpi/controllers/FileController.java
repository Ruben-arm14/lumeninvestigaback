package org.lumeninvestiga.backend.repositorio.tpi.controllers;

import org.lumeninvestiga.backend.repositorio.tpi.entities.data.File;
import org.lumeninvestiga.backend.repositorio.tpi.services.FileService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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

    @PostMapping(consumes = "multipart/form-data", produces = "application/json")
    public ResponseEntity<?> createFile(@RequestParam("file") MultipartFile file) {
        Optional<File> fileOptional = fileService.saveFile(file);
        if (fileOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(fileOptional.get());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error saving file");
    }

    @GetMapping
    public ResponseEntity<?> readAllFiles(@PageableDefault Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(fileService.getAllFiles(pageable));
    }

    @GetMapping("by/{name}")
    public ResponseEntity<?> readByName(@PathVariable String name) {
        Optional<File> fileOptional = fileService.getFileByName(name);
        if(fileOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.FOUND).body(fileOptional);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> readFileById(@PathVariable Long id) {
        Optional<File> fileOptional = fileService.getFileById(id);
        if (fileOptional.isPresent()) {
            return ResponseEntity.ok(fileOptional.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    //Solo se puede modificar el nombre
    @PutMapping("/{id}")
    public ResponseEntity<?> updateFileById(@PathVariable Long id, @RequestParam("name") File file) {
        Optional<File> fileOptional = fileService.getFileById(id);
        if (fileOptional.isPresent()) {
            File fileDb = fileOptional.get();
            fileDb.setName(file.getName());
            //TODO: cambiar a un dto con un mapper
            fileService.saveFile((MultipartFile) fileDb);
            return ResponseEntity.status(HttpStatus.CREATED).body(fileDb);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
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
