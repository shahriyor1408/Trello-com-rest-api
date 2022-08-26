package com.company.trellocomrest.mappers;

import com.company.trellocomrest.domains.project.ProjectColumn;
import com.company.trellocomrest.dtos.project.ProjectColumnCreateDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProjectColumnMapper {
    ProjectColumn fromCreateDto(ProjectColumnCreateDto dto);
}
