package com.company.trellocomrest.controller.project;

import com.company.trellocomrest.controller.ApiController;
import com.company.trellocomrest.dtos.project.comment.CommentCreateDto;
import com.company.trellocomrest.dtos.project.comment.CommentDto;
import com.company.trellocomrest.dtos.project.comment.CommentUpdateDto;
import com.company.trellocomrest.response.ApiResponse;
import com.company.trellocomrest.services.project.CommentService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class CommentController extends ApiController<CommentService> {
    protected CommentController(CommentService service) {
        super(service);
    }

    @PostMapping(value = PATH + "/comment/create", produces = "application/json")
    public ApiResponse<Long> create(@Valid @RequestBody CommentCreateDto dto) {
        return new ApiResponse<>(service.create(dto));
    }

    @GetMapping(value = PATH + "/comment/get/{id}", produces = "application/json")
    public ApiResponse<CommentDto> get(@PathVariable Long id) {
        return new ApiResponse<>(service.get(id));
    }

    @GetMapping(value = PATH + "/comment/getAll/{id}", produces = "application/json")
    public List<CommentDto> getAll(@PathVariable Long id) {
        return service.getAll(id);
    }

    @PutMapping(value = PATH + "/comment/update", produces = "application/json")
    public ApiResponse<CommentDto> update(@Valid @RequestBody CommentUpdateDto commentUpdateDto) {
        return service.update(commentUpdateDto);
    }

    @DeleteMapping(value = PATH + "comment/delete/{id}", produces = "application/json")
    public ApiResponse<Void> delete(@PathVariable Long id) {
        return service.delete(id);
    }
}
