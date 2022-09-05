package com.company.trellocomrest.services.project;

import com.company.trellocomrest.domains.auth.AuthUser;
import com.company.trellocomrest.domains.project.Workspace;
import com.company.trellocomrest.dtos.project.workspace.WorkspaceCreateDto;
import com.company.trellocomrest.dtos.project.workspace.WorkspaceDto;
import com.company.trellocomrest.dtos.project.workspace.WorkspaceUpdateDto;
import com.company.trellocomrest.exceptions.GenericNotFoundException;
import com.company.trellocomrest.mappers.WorkspaceMapper;
import com.company.trellocomrest.repository.project.WorkspaceRepository;
import com.company.trellocomrest.response.ApiResponse;
import com.company.trellocomrest.services.auth.AuthUserService;
import lombok.RequiredArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@ParameterObject
public class WorkspaceService {
    private final WorkspaceMapper workspaceMapper;
    private final WorkspaceRepository workspaceRepository;
    private final AuthUserService authUserService;

    public Long create(WorkspaceCreateDto dto) {
        Workspace workspace = workspaceMapper.fromCreateDto(dto);
        AuthUser authUser = authUserService.getCurrentAuthUser();
        List<AuthUser> list = new ArrayList<>();
        list.add(authUser);
        workspace.setAuthUsers(list);
        workspace.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
        workspace.setCreatedBy(authUser.getId());
        workspaceRepository.save(workspace);
        return workspace.getId();
    }

    public WorkspaceDto get(Long id) {
        return workspaceMapper.toDto(workspaceRepository.getByIdAndIsDeleted(id).orElseThrow(() -> {
            throw new GenericNotFoundException("User not found!", 404);
        }));
    }

    public List<WorkspaceDto> getAll() {
        List<Workspace> workspaces = workspaceRepository.findAllNotDeleted(authUserService.getCurrentAuthUser().getId());
        List<WorkspaceDto> workspaceDTOS = new ArrayList<>();
        workspaces.forEach(workspace -> workspaceDTOS.add(workspaceMapper.toDto(workspace)));
        return workspaceDTOS;
    }

    public ApiResponse<WorkspaceDto> update(WorkspaceUpdateDto dto) {
        Workspace workspace = workspaceRepository.findById(dto.getId()).orElseThrow(() -> {
            throw new GenericNotFoundException("WorkSpace not found!", 404);
        });
        workspaceMapper.fromUpdateDto(dto, workspace);
        workspace.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));
        workspace.setUpdatedBy(authUserService.getCurrentAuthUser().getId());
        workspaceRepository.save(workspace);
        return new ApiResponse<>(workspaceMapper.toDto(workspace));
    }

    public ApiResponse<Void> delete(Long id) {
        Workspace workspace = workspaceRepository.getByIdAndIsDeleted(id).orElseThrow(() -> {
            throw new GenericNotFoundException("Workspace not found!", 404);
        });
        workspace.setDeleted(true);
        workspaceRepository.save(workspace);
        return new ApiResponse<>(HttpStatus.OK.value(), true);
    }
}
