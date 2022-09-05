package com.company.trellocomrest.mappers;

import com.company.trellocomrest.domains.project.ProjectColumn;
import com.company.trellocomrest.dtos.project.project_column.ProjectColumnCreateDto;
import com.company.trellocomrest.dtos.project.project_column.ProjectColumnDto;
import com.company.trellocomrest.dtos.project.project_column.ProjectColumnUpdateDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;
import java.util.Optional;

@Mapper(componentModel = "spring")
public interface ProjectColumnMapper {
    ProjectColumn fromCreateDto(ProjectColumnCreateDto dto);

    ProjectColumnDto toDto(ProjectColumn projectColumn);

    List<ProjectColumnDto> toDto(List<ProjectColumn> projectColumns);

    void fromUpdateDto(ProjectColumnUpdateDto projectColumnUpdateDto, @MappingTarget ProjectColumn projectColumn);
}
