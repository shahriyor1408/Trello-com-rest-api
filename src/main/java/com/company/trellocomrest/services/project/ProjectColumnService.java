package com.company.trellocomrest.services.project;

import com.company.trellocomrest.domains.project.ProjectColumn;
import com.company.trellocomrest.dtos.project.project_column.ProjectColumnCreateDto;
import com.company.trellocomrest.dtos.project.project_column.ProjectColumnDto;
import com.company.trellocomrest.dtos.project.project_column.ProjectColumnUpdateDto;
import com.company.trellocomrest.events.EventPublisher;
import com.company.trellocomrest.events.GenericEvent;
import com.company.trellocomrest.events.project.ProjectColumnMoveEvent;
import com.company.trellocomrest.exceptions.GenericNotFoundException;
import com.company.trellocomrest.mappers.ProjectColumnMapper;
import com.company.trellocomrest.repository.project.BoardRepository;
import com.company.trellocomrest.repository.project.ProjectColumnRepository;
import com.company.trellocomrest.response.ApiResponse;
import com.company.trellocomrest.services.auth.AuthUserService;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Service
@ParameterObject
public class ProjectColumnService {
    private final ProjectColumnRepository projectColumnRepository;
    private final BoardRepository boardRepository;
    private final ProjectColumnMapper projectColumnMapper;
    private final AuthUserService authUserService;

    private final EventPublisher publisher;

    @Autowired
    public ProjectColumnService(ProjectColumnRepository projectColumnRepository,
                                BoardRepository boardRepository, ProjectColumnMapper projectColumnMapper,
                                AuthUserService authUserService, EventPublisher publisher) {
        this.projectColumnRepository = projectColumnRepository;
        this.boardRepository = boardRepository;
        this.projectColumnMapper = projectColumnMapper;
        this.authUserService = authUserService;
        this.publisher = publisher;
    }

    public Long create(ProjectColumnCreateDto dto) {
        ProjectColumn projectColumn = projectColumnMapper.fromCreateDto(dto);
        projectColumn.setColumnOrder(projectColumnRepository.findAllNotDelete(dto.getBoardId()).size() + 1);
        projectColumn.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
        projectColumn.setCreatedBy(authUserService.getCurrentAuthUser().getId());
        projectColumn.setBoard(boardRepository.getByIdAndIsDeleted(dto.getBoardId()).orElseThrow(() -> {
            throw new GenericNotFoundException("Board not found!", 404);
        }));
        return projectColumnRepository.save(projectColumn).getId();
    }

    public ProjectColumnDto get(Long id) {
        return projectColumnMapper.toDto(projectColumnRepository.getByIdAndIsDeleted(id).orElseThrow(() -> {
            throw new GenericNotFoundException("Project column not found!", 404);
        }));
    }

    public List<ProjectColumnDto> getAll(Long id) {
        return projectColumnMapper.toDto(projectColumnRepository.findAllNotDelete(id));
    }

    public ApiResponse<ProjectColumnDto> update(ProjectColumnUpdateDto projectColumnUpdateDto) {
        ProjectColumn projectColumn = projectColumnRepository.getByIdAndIsDeleted(projectColumnUpdateDto.getId()).orElseThrow(() -> {
            throw new GenericNotFoundException("Project column not found!", 404);
        });
        projectColumnMapper.fromUpdateDto(projectColumnUpdateDto, projectColumn);
        projectColumn.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));
        projectColumn.setUpdatedBy(authUserService.getCurrentAuthUser().getId());
        return new ApiResponse<>(projectColumnMapper.toDto(projectColumnRepository.save(projectColumn)));
    }

    public ApiResponse<Void> delete(Long id) {
        ProjectColumn projectColumn = projectColumnRepository.getByIdAndIsDeleted(id).orElseThrow(() -> {
            throw new GenericNotFoundException("Project column not found!", 404);
        });
        projectColumn.setDeleted(true);
        projectColumnRepository.save(projectColumn);
        return new ApiResponse<>(200, true);
    }

    public ApiResponse<Void> move(ProjectColumnMoveEvent projectColumnMoveEvent) {
        publisher.publishCustomEvent(new GenericEvent<>(projectColumnMoveEvent, true));
        return new ApiResponse<>(200, true);
    }
}
