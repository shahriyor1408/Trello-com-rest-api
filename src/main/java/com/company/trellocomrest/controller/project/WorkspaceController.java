package com.company.trellocomrest.controller.project;

import com.company.trellocomrest.controller.ApiController;
import com.company.trellocomrest.dtos.project.workspace.WorkspaceCreateDto;
import com.company.trellocomrest.dtos.project.workspace.WorkspaceDto;
import com.company.trellocomrest.dtos.project.workspace.WorkspaceUpdateDto;
import com.company.trellocomrest.enums.WorkspaceType;
import com.company.trellocomrest.response.ApiResponse;
import com.company.trellocomrest.services.project.WorkspaceService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class WorkspaceController extends ApiController<WorkspaceService> {
    protected WorkspaceController(WorkspaceService service) {
        super(service);
    }

    @PostMapping(value = PATH + "/workspace/create", produces = "application/json")
    public ApiResponse<Long> create(@Valid @RequestBody WorkspaceCreateDto dto) {
        return new ApiResponse<>(service.create(dto));
    }

    @GetMapping(value = PATH + "/workspace/get/{id}", produces = "application/json")
    public ApiResponse<WorkspaceDto> get(@PathVariable Long id) {
        return new ApiResponse<>(service.get(id));
    }

    @GetMapping(value = PATH + "/workspace/getAll", produces = "application/json")
    public List<WorkspaceDto> getAll() {
        return service.getAll();
    }

    @PutMapping(value = PATH + "/workspace/update", produces = "application/json")
    public ApiResponse<WorkspaceDto> update(@Valid @RequestBody WorkspaceUpdateDto workspaceUpdateDto) {
        return service.update(workspaceUpdateDto);
    }

    @DeleteMapping(value = PATH + "workspace/delete/{id}", produces = "application/json")
    public ApiResponse<Void> delete(@PathVariable Long id) {
        return service.delete(id);
    }

    @GetMapping(PATH + "workspace/getAll/types")
    public WorkspaceType[] getAllTypes() {
        return WorkspaceType.values();
    }
}
