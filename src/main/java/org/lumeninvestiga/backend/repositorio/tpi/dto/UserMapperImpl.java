package org.lumeninvestiga.backend.repositorio.tpi.dto;

import org.lumeninvestiga.backend.repositorio.tpi.dto.request.UserRegistrationRequest;
import org.lumeninvestiga.backend.repositorio.tpi.dto.response.UserResponse;
import org.lumeninvestiga.backend.repositorio.tpi.entities.user.User;

public class UserMapperImpl implements UserMapper{
    @Override
    public UserRegistrationRequest toUserRegisterRequest(User user) {
        return new UserRegistrationRequest(
                user.getUserDetail().getName(),
                user.getUserDetail().getLastName(),
                user.getUserDetail().getCode(),
                user.getUserDetail().getEmailAddress(),
                user.getUserDetail().getPassword()
        );
    }

    @Override
    public UserResponse toUserResponse(User user) {
        return new UserResponse(
                user.getUserDetail().getName(),
                user.getUserDetail().getLastName(),
                user.getUserDetail().getEmailAddress()
        );
    }

}
