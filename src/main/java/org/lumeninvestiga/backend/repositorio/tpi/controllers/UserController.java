package org.lumeninvestiga.backend.repositorio.tpi.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.lumeninvestiga.backend.repositorio.tpi.dto.mapper.UserMapper;
import org.lumeninvestiga.backend.repositorio.tpi.dto.request.UserUpdateRequest;
import org.lumeninvestiga.backend.repositorio.tpi.dto.response.UserResponse;
import org.lumeninvestiga.backend.repositorio.tpi.entities.user.User;
import org.lumeninvestiga.backend.repositorio.tpi.repositories.UserRepository;
import org.lumeninvestiga.backend.repositorio.tpi.security.jwt.JwtService;
import org.lumeninvestiga.backend.repositorio.tpi.services.UserService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final UserMapper userMapper;

    public UserController(UserService userService, UserRepository userRepository, JwtService jwtService, UserMapper userMapper) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.userMapper = userMapper;
    }

    @GetMapping()
    public ResponseEntity<?> readUsers(@PageableDefault Pageable pageable) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(userService.getAllUsers(pageable));
    }

    @GetMapping("/{user_id}")
    public ResponseEntity<?> readUser(@PathVariable("user_id") Long userId) {
        Optional<UserResponse> response = userService.getUserById(userId);
        if (response.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
    }

    // Endpoint para obtener el perfil del usuario autenticado
    @GetMapping("/profile")
    public ResponseEntity<?> readUserProfile(HttpServletRequest request) {
        String token = request.getHeader("Authorization").replace("Bearer ", "");
        String username = jwtService.getUsernameFromToken(token);
        Optional<User> userOptional = userRepository.findByUsername(username);

        if (userOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

        User user = userOptional.get();
        UserResponse userResponse = userMapper.toUserResponse(user);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(userResponse);
    }

    @PutMapping("/{user_id}")
    public ResponseEntity<?> updateUserById(
            @PathVariable("user_id") Long userId,
            @Valid @RequestBody UserUpdateRequest request) {
        Optional<UserResponse> response = userService.updateUserById(userId, request);
        if (response.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
    }

    @DeleteMapping("/{user_id}")
    public ResponseEntity<?> deleteUserById(@PathVariable("user_id") Long userId) {
        boolean responseStatus = userService.deleteUserById(userId);
        if (!responseStatus) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    // Nuevo endpoint para obtener el rol del usuario autenticado
    @GetMapping("/role")
    public ResponseEntity<?> getUserRole(HttpServletRequest request) {
        String token = request.getHeader("Authorization").replace("Bearer ", "");
        String username = jwtService.getUsernameFromToken(token);
        Optional<User> userOptional = userRepository.findByUsername(username);

        if (userOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

        User user = userOptional.get();
        return ResponseEntity.ok().body(Map.of("role", user.getRole()));
    }
}
