package org.lumeninvestiga.backend.repositorio.tpi.controllers;

import jakarta.validation.Valid;
import org.lumeninvestiga.backend.repositorio.tpi.dto.request.ReviewUpdateRequest;
import org.lumeninvestiga.backend.repositorio.tpi.dto.request.UserRegistrationRequest;
import org.lumeninvestiga.backend.repositorio.tpi.dto.request.UserUpdateRequest;
import org.lumeninvestiga.backend.repositorio.tpi.dto.response.UserResponse;
import org.lumeninvestiga.backend.repositorio.tpi.entities.user.User;
import org.lumeninvestiga.backend.repositorio.tpi.entities.user.UserDetail;
import org.lumeninvestiga.backend.repositorio.tpi.services.UserService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody UserRegistrationRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.saveUser(request));
    }

    @GetMapping
    public ResponseEntity<?> readUsers(@PageableDefault Pageable pageable) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(userService.getAllUsers(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> readUser(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(userService.getUserById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUserById(@PathVariable Long id, @RequestBody UserUpdateRequest request) {
        userService.updateUserById(id, request);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable Long id) {
        userService.deleteUserById(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

}
