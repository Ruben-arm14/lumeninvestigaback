package org.lumeninvestiga.backend.repositorio.tpi.dto.mapper;

import org.lumeninvestiga.backend.repositorio.tpi.dto.response.UserResponse;
import org.lumeninvestiga.backend.repositorio.tpi.entities.user.User;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {
    UserResponse toUserResponse(User user);
}
