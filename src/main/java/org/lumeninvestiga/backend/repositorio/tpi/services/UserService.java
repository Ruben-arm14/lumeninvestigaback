package org.lumeninvestiga.backend.repositorio.tpi.services;

import org.lumeninvestiga.backend.repositorio.tpi.entities.user.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<User> saveUser(User user);
    List<User> getAllUsers();
    Optional<User> getUserById(Long id);
    void deleteUserById(Long id);
    boolean existUserById(Long id);
}
