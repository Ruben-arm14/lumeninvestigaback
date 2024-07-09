package org.lumeninvestiga.backend.repositorio.tpi.services;

import org.lumeninvestiga.backend.repositorio.tpi.dto.request.UserLoginRequest;
import org.lumeninvestiga.backend.repositorio.tpi.dto.request.UserRegistrationRequest;
import org.lumeninvestiga.backend.repositorio.tpi.dto.request.UserUpdateRequest;
import org.lumeninvestiga.backend.repositorio.tpi.dto.response.UserResponse;
import org.lumeninvestiga.backend.repositorio.tpi.entities.user.User;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<UserResponse> saveUser(UserRegistrationRequest request);
    List<UserResponse> getAllUsers(Pageable pageable);
    Optional<UserResponse> getUserById(Long id);
    Optional<UserResponse> updateUserById(Long id, UserUpdateRequest request);
    boolean deleteUserById(Long id);
    boolean existUserById(Long id);
}
