package org.lumeninvestiga.backend.repositorio.tpi.controllers;

import org.lumeninvestiga.backend.repositorio.tpi.entities.User;
import org.lumeninvestiga.backend.repositorio.tpi.repositories.ReviewRepository;
import org.lumeninvestiga.backend.repositorio.tpi.services.ReviewService;
import org.lumeninvestiga.backend.repositorio.tpi.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final ReviewService reviewService;

    public UserController(UserService userService, ReviewService reviewService) {
        this.userService = userService;
        this.reviewService = reviewService;
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody User user) {
        return ResponseEntity.ok(userService.createUser(user));
    }

    @GetMapping
    public ResponseEntity<?> getAllUsers() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUsers());
    }

    @DeleteMapping
    public ResponseEntity<?> deleteUser(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}
