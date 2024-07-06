package org.lumeninvestiga.backend.repositorio.tpi.controllers;

import jakarta.validation.Valid;
import org.lumeninvestiga.backend.repositorio.tpi.dto.request.UserLoginRequest;
import org.lumeninvestiga.backend.repositorio.tpi.dto.request.UserRegistrationRequest;
import org.lumeninvestiga.backend.repositorio.tpi.dto.request.UserUpdateRequest;
import org.lumeninvestiga.backend.repositorio.tpi.dto.response.UserResponse;
import org.lumeninvestiga.backend.repositorio.tpi.services.UserService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<?> createUser(@Valid @RequestBody UserRegistrationRequest request) {
        Optional<UserResponse> response = userService.saveUser(request);
        if(response.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@Valid @RequestBody UserLoginRequest request) {
        if(!userService.loginSession(request)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @GetMapping()
    public ResponseEntity<?> readUsers(@PageableDefault Pageable pageable) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(userService.getAllUsers(pageable));
    }

    @GetMapping("/{user_id}")
    public ResponseEntity<?> readUser(@PathVariable("user_id") Long userId) {
        Optional<UserResponse> response = userService.getUserById(userId);
        if(response.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
    }

    @GetMapping("/{user_id}/profile")
    public ResponseEntity<?> readUserProfile(@PathVariable("user_id") Long userId) {
        Optional<UserResponse> response = userService.getUserById(userId);
        if(response.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
    }

    @PutMapping("/{user_id}")
    public ResponseEntity<?> updateUserById(
            @PathVariable("user_id") Long userId,
            @Valid @RequestBody UserUpdateRequest request) {
        Optional<UserResponse> response = userService.updateUserById(userId, request);
        if(response.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
    }

    @DeleteMapping("/{user_id}")
    public ResponseEntity<?> deleteUserById(@PathVariable("user_id") Long userId) {
        boolean responseStatus = userService.deleteUserById(userId);
        if(!responseStatus) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}
