package org.lumeninvestiga.backend.repositorio.tpi.controllers;

import org.lumeninvestiga.backend.repositorio.tpi.dto.request.UserLoginRequest;
import org.lumeninvestiga.backend.repositorio.tpi.dto.request.UserRegistrationRequest;
import org.lumeninvestiga.backend.repositorio.tpi.dto.request.UserUpdateRequest;
import org.lumeninvestiga.backend.repositorio.tpi.services.UserService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody UserRegistrationRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.saveUser(request));
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody UserLoginRequest request) {
        //TODO: Crear el método login en la capa service.
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @GetMapping()
    public ResponseEntity<?> readUsers(@PageableDefault Pageable pageable) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(userService.getAllUsers(pageable));
    }

    @GetMapping("/{user_id}")
    public ResponseEntity<?> readUser(@PathVariable("user_id") Long userId) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(userService.getUserById(userId));
    }

    @GetMapping("/{user_id}/profile")
    public ResponseEntity<?> readUserProfile(@PathVariable("user_id") Long userId) {
        //TODO: Revisar si se necesita un método que solo devuelva datos del profile-user
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(userService.getUserById(userId));
    }

    @PutMapping("/{user_id}")
    public ResponseEntity<?> updateUserById(
            @PathVariable Long userId,
            @RequestBody UserUpdateRequest request) {
        userService.updateUserById(userId, request);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @DeleteMapping("/{user_id}")
    public ResponseEntity<?> deleteUserById(@PathVariable("user_id") Long userId) {
        userService.deleteUserById(userId);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}
