package org.lumeninvestiga.backend.repositorio.tpi.controllers;

import org.lumeninvestiga.backend.repositorio.tpi.entities.data.Folder;
import org.lumeninvestiga.backend.repositorio.tpi.services.FolderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/folders")
public class FolderController {
    private final FolderService folderService;

    public FolderController(FolderService folderService) {
        this.folderService = folderService;
    }

    @PostMapping
    public ResponseEntity<?> createFile(@RequestBody Folder folder) {
        return ResponseEntity.ok(folderService.saveFolder(folder));
    }

    @GetMapping
    public ResponseEntity<?> getAllFiles() {
        return ResponseEntity.status(HttpStatus.OK).body(folderService.getAllFolders());
    }

    @DeleteMapping
    public ResponseEntity<?> deleteFile(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}
