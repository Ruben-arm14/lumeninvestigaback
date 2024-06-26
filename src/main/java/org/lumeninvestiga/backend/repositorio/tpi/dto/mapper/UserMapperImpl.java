package org.lumeninvestiga.backend.repositorio.tpi.dto.mapper;

import org.lumeninvestiga.backend.repositorio.tpi.dto.request.UserRegistrationRequest;
import org.lumeninvestiga.backend.repositorio.tpi.dto.response.UserResponse;
import org.lumeninvestiga.backend.repositorio.tpi.entities.user.User;
import org.springframework.stereotype.Service;

@Service
public class UserMapperImpl implements UserMapper{
    @Override
    public UserResponse toUserResponse(User user) {
        return new UserResponse(
                user.getUserDetail().getName(),
                user.getUserDetail().getLastName(),
                user.getUserDetail().getCode(),
                user.getUserDetail().getEmailAddress()
        );
    }
}
