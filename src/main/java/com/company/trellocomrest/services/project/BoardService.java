package com.company.trellocomrest.services.project;

import com.company.trellocomrest.domains.auth.AuthUser;
import com.company.trellocomrest.domains.project.Board;
import com.company.trellocomrest.domains.project.Workspace;
import com.company.trellocomrest.dtos.project.board.BoardCreateDto;
import com.company.trellocomrest.dtos.project.board.BoardDto;
import com.company.trellocomrest.dtos.project.board.BoardUpdateDto;
import com.company.trellocomrest.exceptions.GenericNotFoundException;
import com.company.trellocomrest.mappers.BoardMapper;
import com.company.trellocomrest.repository.project.BoardRepository;
import com.company.trellocomrest.repository.project.WorkspaceRepository;
import com.company.trellocomrest.response.ApiResponse;
import com.company.trellocomrest.services.auth.AuthUserService;
import lombok.RequiredArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@ParameterObject
public class BoardService {
    private final BoardRepository boardRepository;
    private final AuthUserService authUserService;
    private final WorkspaceRepository workspaceRepository;
    private final BoardMapper boardMapper;

    public Long create(BoardCreateDto dto) {
        Board board = boardMapper.fromCreateDto(dto);
        AuthUser authUser = authUserService.getCurrentAuthUser();
        board.setAuthUsers(List.of(authUser));
        board.setCreatedBy(authUser.getId());
        board.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
        Workspace workspace = workspaceRepository.getByIdAndIsDeleted(dto.getWorkspaceId()).orElseThrow(()
                -> new GenericNotFoundException("Workspace not found!", 404));
        board.setWorkspace(workspace);
        return boardRepository.save(board).getId();
    }

    public BoardDto get(Long id) {
        return boardMapper.toDto(boardRepository.getByIdAndIsDeleted(id).orElseThrow(() -> {
            throw new GenericNotFoundException("Board not found!", 404);
        }));
    }

    public List<BoardDto> getAll(Long id) {
        return boardMapper.toDto(boardRepository.findAllNotDeleted(authUserService.getCurrentAuthUser().getId(), id));
    }

    public ApiResponse<BoardDto> update(BoardUpdateDto boardUpdateDto) {
        Board board = boardRepository.findById(boardUpdateDto.getId()).orElseThrow(() -> {
            throw new GenericNotFoundException("Board not found!", 404);
        });
        boardMapper.fromUpdateDto(boardUpdateDto, board);
        board.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));
        board.setUpdatedBy(authUserService.getCurrentAuthUser().getId());
        return new ApiResponse<>(boardMapper.toDto(boardRepository.save(board)));
    }

    public ApiResponse<Void> delete(Long id) {
        Board board = boardRepository.getByIdAndIsDeleted(id).orElseThrow(() -> {
            throw new GenericNotFoundException("Board not found!", 404);
        });
        board.setDeleted(true);
        boardRepository.save(board);
        return new ApiResponse<>(200, true);
    }
}
