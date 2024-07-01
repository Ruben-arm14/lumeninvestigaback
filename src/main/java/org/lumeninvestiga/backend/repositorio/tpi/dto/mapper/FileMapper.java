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
    @Mapping(source = "mimeType", target = "mimeType")
    @Mapping(source = "folder.name", target = "folderName")
    @Mapping(source = "data", target = "fileData")
    FileResponse toFileResponse(File file);
}
