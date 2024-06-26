package org.lumeninvestiga.backend.repositorio.tpi.dto.mapper;

import org.lumeninvestiga.backend.repositorio.tpi.dto.response.UserResponse;
import org.lumeninvestiga.backend.repositorio.tpi.entities.user.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(source = "userDetail.name", target = "name")
    @Mapping(source = "userDetail.lastName", target = "lastName")
    @Mapping(source = "userDetail.code", target = "code")
    @Mapping(source = "userDetail.emailAddress", target = "emailAddress")
    UserResponse toUserResponse(User user);
}
