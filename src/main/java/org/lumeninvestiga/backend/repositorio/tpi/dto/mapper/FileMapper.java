package org.lumeninvestiga.backend.repositorio.tpi.dto.mapper;

import org.lumeninvestiga.backend.repositorio.tpi.dto.response.FileResponse;
import org.lumeninvestiga.backend.repositorio.tpi.entities.data.File;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface FileMapper {
    FileMapper INSTANCE = Mappers.getMapper(FileMapper.class);

    //TODO: Revisar con record de FileResponse
    @Mapping(target = "userName", source = "user.userDetail.name")
    @Mapping(target = "folderName", source = "folder.name")
    @Mapping(target = "shared", source = "folder.shared")
    @Mapping(target = "mimeType", source = "file.mimeType")
    FileResponse toFileResponse(File file);
}
