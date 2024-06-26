package org.lumeninvestiga.backend.repositorio.tpi.dto;

import org.lumeninvestiga.backend.repositorio.tpi.dto.request.UserRegistrationRequest;
import org.lumeninvestiga.backend.repositorio.tpi.dto.response.UserResponse;
import org.lumeninvestiga.backend.repositorio.tpi.entities.user.User;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {
    UserRegistrationRequest toUserRegisterRequest(User user);
    UserResponse toUserResponse(User user);
}
