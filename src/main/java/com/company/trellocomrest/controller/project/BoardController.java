package com.company.trellocomrest.controller.project;

import com.company.trellocomrest.controller.ApiController;
import com.company.trellocomrest.dtos.project.board.BoardCreateDto;
import com.company.trellocomrest.dtos.project.board.BoardDto;
import com.company.trellocomrest.dtos.project.board.BoardUpdateDto;
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
    public ApiResponse<BoardDto> get(@PathVariable Long id) {
        return new ApiResponse<>(service.get(id));
    }

    @GetMapping(value = PATH + "/board/getAll/{id}", produces = "application/json")
    public List<BoardDto> getAll(@PathVariable Long id) {
        return service.getAll(id);
    }

    @PutMapping(value = PATH + "/board/update", produces = "application/json")
    public ApiResponse<BoardDto> update(@Valid @RequestBody BoardUpdateDto boardUpdateDto) {
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
