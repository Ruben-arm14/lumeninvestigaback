package org.lumeninvestiga.backend.repositorio.tpi.controllers;

import org.lumeninvestiga.backend.repositorio.tpi.entities.data.Folder;
import org.lumeninvestiga.backend.repositorio.tpi.services.FolderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/folders")
public class FolderController {
    private final FolderService folderService;

    public FolderController(FolderService folderService) {
        this.folderService = folderService;
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> createFile(@RequestBody Folder folder) {
        return ResponseEntity.ok(folderService.saveFolder(folder));
    }

    @GetMapping
    public ResponseEntity<?> readAllFiles() {
        return ResponseEntity.status(HttpStatus.OK).body(folderService.getAllFolders());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> readFolderById(@PathVariable Long id) {
        return ResponseEntity.ok(folderService.getFolderById(id));
    }

    //Solo se puede modificar el nombre, isShared
    @PutMapping("/{id}")
    public ResponseEntity<?> updateFolderById(@PathVariable Long id, @RequestBody Folder folder) {
        Optional<Folder> folderOptional = folderService.getFolderById(id);
        folderOptional.ifPresent(folderDb -> {
            folderDb.setName(folder.getName());
            folderDb.setShared(folder.isShared());
        });
        return ResponseEntity.status(HttpStatus.CREATED).body(folderService.saveFolder(folderOptional.get()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFolderById(@PathVariable Long id){
        Optional<Folder> folderOptional = folderService.getFolderById(id);
        if(folderOptional.isPresent()) {
            folderService.deleteFolderById(id);
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
