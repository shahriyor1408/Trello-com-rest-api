package com.company.trellocomrest.mappers;

import com.company.trellocomrest.domains.project.Workspace;
import com.company.trellocomrest.dtos.project.workspace.WorkspaceCreateDto;
import com.company.trellocomrest.dtos.project.workspace.WorkspaceDto;
import com.company.trellocomrest.dtos.project.workspace.WorkspaceUpdateDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface WorkspaceMapper {
    Workspace fromCreateDto(WorkspaceCreateDto dto);

    void fromUpdateDto(WorkspaceUpdateDto dto, @MappingTarget Workspace workspace);

    WorkspaceDto toDto(Workspace workspaces);
}
