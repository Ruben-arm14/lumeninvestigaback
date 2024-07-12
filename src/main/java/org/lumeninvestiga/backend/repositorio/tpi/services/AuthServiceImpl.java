package org.lumeninvestiga.backend.repositorio.tpi.services;

import org.lumeninvestiga.backend.repositorio.tpi.dto.request.UserLoginRequest;
import org.lumeninvestiga.backend.repositorio.tpi.dto.request.UserRegistrationRequest;
import org.lumeninvestiga.backend.repositorio.tpi.dto.response.AuthResponse;
import org.lumeninvestiga.backend.repositorio.tpi.entities.user.Role;
import org.lumeninvestiga.backend.repositorio.tpi.entities.user.User;
import org.lumeninvestiga.backend.repositorio.tpi.entities.user.UserDetail;
import org.lumeninvestiga.backend.repositorio.tpi.repositories.UserRepository;
import org.lumeninvestiga.backend.repositorio.tpi.security.jwt.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthServiceImpl(UserRepository userRepository, JwtService jwtService, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<AuthResponse> login(UserLoginRequest request) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.username(), request.password()));
            UserDetails userDetails = userRepository.findByUsername(request.username())
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));
            String token = jwtService.getToken(userDetails);
            return Optional.of(new AuthResponse(token));
        } catch (Exception e) {
            // Agrega logging aquí para verificar errores
            e.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    @Transactional
    public Optional<AuthResponse> register(UserRegistrationRequest request) {
        User user = new User();
        UserDetail userDetail = new UserDetail();
        userDetail.setName(request.name());
        userDetail.setLastName(request.lastName());
        userDetail.setEmailAddress(request.emailAddress());
        user.setUserDetail(userDetail);
        user.setUsername(request.username());
        user.setPassword(passwordEncoder.encode(request.password()));
        user.setRole(Role.STUDENT); // O el rol correspondiente
        userRepository.save(user);
        String token = jwtService.getToken(user);
        return Optional.of(new AuthResponse(token));
    }
}
