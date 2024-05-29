package org.lumeninvestiga.backend.repositorio.tpi.controllers;

import jakarta.validation.Valid;
import org.lumeninvestiga.backend.repositorio.tpi.entities.user.User;
import org.lumeninvestiga.backend.repositorio.tpi.services.UserService;
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
    public ResponseEntity<?> createUser(@Valid @RequestBody User user, BindingResult result) {
        if(result.hasErrors()) {
            return validation(result);
        }
        return ResponseEntity.ok(userService.saveUser(user));
    }

    @GetMapping
    public ResponseEntity<?> readAllUsers() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> readUserById(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserById(id));

    }

    //Solo se puede modificar el nombre, apellido y correo
    @PutMapping("/{id}")
    public ResponseEntity<?> updateUserById(@PathVariable Long id, @RequestBody User user) {
        Optional<User> userOptional = userService.getUserById(id);
        userOptional.ifPresent(userDb -> {
            userDb.getUserDetail().setName(user.getUserDetail().getName());
            userDb.getUserDetail().setLastName(user.getUserDetail().getLastName());
            userDb.getUserDetail().setEmailAddress(user.getUserDetail().getEmailAddress());
        });
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.saveUser(userOptional.get()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable Long id){
        Optional<User> userOptional = userService.getUserById(id);
        if(userOptional.isPresent()) {
            userService.deleteUserById(id);
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    private ResponseEntity<?> validation(BindingResult result) {
        Map<String, String> errors = new HashMap<>();
        result.getFieldErrors().forEach(fieldError -> {
            errors.put(fieldError.getField(), "El campo " + fieldError.getField() + " " + fieldError.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }
}
