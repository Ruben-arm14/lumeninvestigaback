package org.lumeninvestiga.backend.repositorio.tpi.services;

import org.lumeninvestiga.backend.repositorio.tpi.dto.request.UserLoginRequest;
import org.lumeninvestiga.backend.repositorio.tpi.dto.request.UserRegistrationRequest;
import org.lumeninvestiga.backend.repositorio.tpi.dto.response.AuthResponse;

import java.util.Optional;

public interface AuthService {
    Optional<AuthResponse> login(UserLoginRequest request);
    Optional<AuthResponse> register(UserRegistrationRequest request);
}
