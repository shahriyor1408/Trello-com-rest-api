package com.company.trellocomrest.controller.project;

import com.company.trellocomrest.controller.ApiController;
import com.company.trellocomrest.domains.project.ProjectColumn;
import com.company.trellocomrest.dtos.project.project_column.ProjectColumnCreateDto;
import com.company.trellocomrest.dtos.project.project_column.ProjectColumnDto;
import com.company.trellocomrest.dtos.project.project_column.ProjectColumnUpdateDto;
import com.company.trellocomrest.response.ApiResponse;
import com.company.trellocomrest.services.project.ProjectColumnService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class ProjectColumnController extends ApiController<ProjectColumnService> {
    protected ProjectColumnController(ProjectColumnService service) {
        super(service);
    }

    @PostMapping(value = PATH + "/projectColumn/create", produces = "application/json")
    public ApiResponse<Long> create(@Valid @RequestBody ProjectColumnCreateDto dto) {
        return new ApiResponse<>(service.create(dto));
    }

    @GetMapping(value = PATH + "/projectColumn/get/{id}", produces = "application/json")
    public ApiResponse<ProjectColumnDto> get(@PathVariable Long id) {
        return new ApiResponse<>(service.get(id));
    }

    @GetMapping(value = PATH + "/projectColumn/getAll/{id}", produces = "application/json")
    public List<ProjectColumnDto> getAll(@PathVariable Long id) {
        return service.getAll(id);
    }

    @PutMapping(value = PATH + "/projectColumn/update", produces = "application/json")
    public ApiResponse<ProjectColumnDto> update(@Valid @RequestBody ProjectColumnUpdateDto projectColumnUpdateDto) {
        return service.update(projectColumnUpdateDto);
    }

    @DeleteMapping(value = PATH + "projectColumn/delete/{id}", produces = "application/json")
    public ApiResponse<Void> delete(@PathVariable Long id) {
        return service.delete(id);
    }
}
