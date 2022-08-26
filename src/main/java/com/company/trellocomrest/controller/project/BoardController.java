package com.company.trellocomrest.controller.project;

import com.company.trellocomrest.controller.ApiController;
import com.company.trellocomrest.domains.project.Board;
import com.company.trellocomrest.dtos.project.BoardCreateDto;
import com.company.trellocomrest.dtos.project.BoardUpdateDto;
import com.company.trellocomrest.enums.BoardType;
import com.company.trellocomrest.response.ApiResponse;
import com.company.trellocomrest.services.project.BoardService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class BoardController extends ApiController<BoardService> {
    protected BoardController(BoardService service) {
        super(service);
    }

    @PostMapping(value = PATH + "/board/create", produces = "application/json")
    public ApiResponse<Long> create(@Valid @RequestBody BoardCreateDto dto) {
        return new ApiResponse<>(service.create(dto));
    }

    @GetMapping(value = PATH + "/board/get/{id}", produces = "application/json")
    public ApiResponse<Board> get(@PathVariable Long id) {
        return new ApiResponse<>(service.get(id));
    }

    @GetMapping(value = PATH + "/board/getAll", produces = "application/json")
    public List<Board> getAll() {
        return service.getAll();
    }

    @PutMapping(value = PATH + "/board/update", produces = "application/json")
    public ApiResponse<Board> update(@Valid @RequestBody BoardUpdateDto boardUpdateDto) {
        return service.update(boardUpdateDto);
    }

    @DeleteMapping(value = PATH + "board/delete/{id}", produces = "application/json")
    public ApiResponse<Void> delete(@PathVariable Long id) {
        return service.delete(id);
    }

    @GetMapping(PATH + "board/getAll/types")
    public BoardType[] getAllTypes() {
        return BoardType.values();
    }
}
