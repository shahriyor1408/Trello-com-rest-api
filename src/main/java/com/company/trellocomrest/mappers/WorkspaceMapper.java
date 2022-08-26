package com.company.trellocomrest.mappers;

import com.company.trellocomrest.domains.project.Workspace;
import com.company.trellocomrest.dtos.project.WorkspaceCreateDto;
import com.company.trellocomrest.dtos.project.WorkspaceDto;
import com.company.trellocomrest.dtos.project.WorkspaceUpdateDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface WorkspaceMapper {
    Workspace fromCreateDto(WorkspaceCreateDto dto);

    Workspace fromUpdateDto(WorkspaceUpdateDto dto);

    List<WorkspaceDto> toDto(List<Workspace> workspaces);
}
