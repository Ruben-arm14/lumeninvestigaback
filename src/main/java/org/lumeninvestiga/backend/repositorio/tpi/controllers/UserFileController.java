package org.lumeninvestiga.backend.repositorio.tpi.controllers;

import org.lumeninvestiga.backend.repositorio.tpi.entities.user.User;
import org.lumeninvestiga.backend.repositorio.tpi.services.FileService;
import org.lumeninvestiga.backend.repositorio.tpi.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/users/{userId}/files")
public class UserFileController {
    private final UserService userService;
    private final FileService fileService;

    public UserFileController(UserService userService, FileService fileService) {
        this.userService = userService;
        this.fileService = fileService;
    }

//    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
//    public ResponseEntity<Void> uploadFiles(@PathVariable Long userId, @RequestParam("files") MultipartFile[] files) {
//        // Validar que el usuario con ID userId sea el usuario autenticado
//        //User currentUser = userService.getCurrentUser();
//        if (currentUser.getId() != userId) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//        }
//
//        // Validar los archivos
//        for (MultipartFile file : files) {
//            if (!file.isEmpty() && (file.getContentType().equals("application/pdf") || file.getContentType().startsWith("image/"))) {
//                // Subir el archivo
//                //fileService.saveFile(userId, file);
//            } else {
//                // Manejar error de tipo de archivo no válido
//                return ResponseEntity.badRequest().build();
//            }
//        }
//
//        return ResponseEntity.ok().build();
//    }

//    @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
//    public ResponseEntity<Void> uploadFile(@PathVariable Long userId, @RequestParam MultipartFile files) {
//        // Validar que el usuario con ID userId sea el usuario autenticado
//        User currentUser = userService.getCurrentUser();
//        if (currentUser.getId() != userId) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//        }
//
//        // Validar los archivos
//        for (MultipartFile file : files) {
//            if (!file.isEmpty() && (file.getContentType().equals("application/pdf") || file.getContentType().startsWith("image/"))) {
//                // Subir el archivo
//                //fileService.saveFile(userId, file);
//            } else {
//                // Manejar error de tipo de archivo no válido
//                return ResponseEntity.badRequest().build();
//            }
//        }
//
//        return ResponseEntity.ok().build();
//    }

}
