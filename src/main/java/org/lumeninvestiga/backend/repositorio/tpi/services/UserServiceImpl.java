package org.lumeninvestiga.backend.repositorio.tpi.services;

import org.lumeninvestiga.backend.repositorio.tpi.dto.mapper.UserMapper;
import org.lumeninvestiga.backend.repositorio.tpi.dto.request.UserRegistrationRequest;
import org.lumeninvestiga.backend.repositorio.tpi.dto.request.UserUpdateRequest;
import org.lumeninvestiga.backend.repositorio.tpi.dto.response.UserResponse;
import org.lumeninvestiga.backend.repositorio.tpi.entities.user.User;
import org.lumeninvestiga.backend.repositorio.tpi.repositories.UserRepository;
import org.lumeninvestiga.backend.repositorio.tpi.utils.Utility;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public Optional<UserResponse> saveUser(UserRegistrationRequest request) {
        Optional<User> userOptional = userRepository.findByEmailAddress(request.emailAddress());
        if(userOptional.isPresent()) {
            //TODO: INVALID_RESOURCE_EXCEPTION
            throw new RuntimeException();
        }
        User userRequest = new User();
        userRequest.getUserDetail().setName(request.name());
        userRequest.getUserDetail().setLastName(request.lastName());
        userRequest.getUserDetail().setCode(request.code());
        userRequest.getUserDetail().setEmailAddress(request.emailAddress());
        userRequest.getUserDetail().setPassword(passwordEncoder.encode(request.password()));

        userRepository.save(userRequest);
        return Optional.of(UserMapper.INSTANCE.toUserResponse(userRequest));
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserResponse> getAllUsers(Pageable pageable) {
        int page = Utility.getCurrentPage(pageable);
        return userRepository.findAll(PageRequest.of(page, pageable.getPageSize())).stream()
                .map(UserMapper.INSTANCE::toUserResponse)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UserResponse> getUserById(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        if(userOptional.isEmpty()) {
            //TODO: NOTFOUND_RESOURCE_EXCEPTION
            throw new RuntimeException();
        }
        return Optional.of(UserMapper.INSTANCE.toUserResponse(userOptional.get()));
    }

    @Override
    @Transactional
    public void updateUserById(Long id, UserUpdateRequest request) {
        Optional<User> userOptional = userRepository.findById(id);
        userOptional.ifPresentOrElse(userDb -> {
            userDb.getUserDetail().setName(request.name());
            userDb.getUserDetail().setLastName(request.lastName());
            userDb.getUserDetail().setEmailAddress(request.emailAddress());
        },
                //TODO: NOTFOUND_RESOURCE_EXCEPTION
                () -> new RuntimeException());
    }

    @Override
    @Transactional
    public void deleteUserById(Long id) {
        if(existUserById(id)) {
            userRepository.deleteById(id);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existUserById(Long id) {
        return userRepository.existsById(id);
    }
}
