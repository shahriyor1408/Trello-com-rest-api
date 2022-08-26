package com.company.trellocomrest.services.project;

import com.company.trellocomrest.domains.project.ProjectColumn;
import com.company.trellocomrest.dtos.project.ProjectColumnCreateDto;
import com.company.trellocomrest.dtos.project.ProjectColumnUpdateDto;
import com.company.trellocomrest.mappers.ProjectColumnMapper;
import com.company.trellocomrest.repository.project.ProjectColumnRepository;
import com.company.trellocomrest.response.ApiResponse;
import com.company.trellocomrest.services.AuthUserService;
import lombok.RequiredArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@ParameterObject
public class ProjectColumnService {
    private final ProjectColumnRepository projectColumnRepository;
    private final ProjectColumnMapper projectColumnMapper;
    private final AuthUserService authUserService;

    public Long create(ProjectColumnCreateDto dto) {
        ProjectColumn projectColumn = projectColumnMapper.fromCreateDto(dto);
        projectColumn.setColumnOrder(projectColumnRepository.findAllNotDelete().size() + 1);
        projectColumn.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
        projectColumn.setCreatedBy(authUserService.getCurrentAuthUser().getId());
        return null;
    }

    public ProjectColumn get(Long id) {
        return null;
    }

    public List<ProjectColumn> getAll() {
        return null;
    }

    public ApiResponse<ProjectColumn> update(ProjectColumnUpdateDto projectColumnUpdateDto) {
        return null;
    }

    public ApiResponse<Void> delete(Long id) {
        return null;
    }
}
