package com.company.trellocomrest.services.project;

import com.company.trellocomrest.domains.auth.AuthUser;
import com.company.trellocomrest.domains.project.Board;
import com.company.trellocomrest.domains.project.Workspace;
import com.company.trellocomrest.dtos.project.BoardCreateDto;
import com.company.trellocomrest.dtos.project.BoardUpdateDto;
import com.company.trellocomrest.exceptions.GenericNotFoundException;
import com.company.trellocomrest.mappers.BoardMapper;
import com.company.trellocomrest.repository.project.BoardRepository;
import com.company.trellocomrest.response.ApiResponse;
import com.company.trellocomrest.services.AuthUserService;
import lombok.RequiredArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@ParameterObject
public class BoardService {
    private final BoardRepository boardRepository;
    private final AuthUserService authUserService;
    private final WorkspaceService workspaceService;
    private final BoardMapper boardMapper;

    public Long create(BoardCreateDto dto) {
        Board board = boardMapper.fromCreateDto(dto);
        AuthUser authUser = authUserService.getCurrentAuthUser();
        board.setAuthUsers(List.of(authUser));
        board.setCreatedBy(authUser.getId());
        board.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
        Workspace workspace = workspaceService.get(dto.getWorkspaceId());
        if (Objects.isNull(workspace)) {
            throw new GenericNotFoundException("Workspace not found!", 404);
        }
        board.setWorkspace(workspace);
        return boardRepository.save(board).getId();
    }

    public Board get(Long id) {
        Board board = boardRepository.findById(id).orElseThrow(() -> {
            throw new GenericNotFoundException("Board not found!", 404);
        });
        if (board.isDeleted()) {
            throw new GenericNotFoundException("Board not found!", 404);
        }
        return board;
    }

    public List<Board> getAll() {
        return boardRepository.findAllNotDeleted(authUserService.getCurrentAuthUser().getId());
    }

    public ApiResponse<Board> update(BoardUpdateDto boardUpdateDto) {
        Board boardUpdate = boardMapper.fromUpdateDto(boardUpdateDto);
        Board board = boardRepository.findById(boardUpdateDto.getId()).orElseThrow(() -> {
            throw new GenericNotFoundException("Board not found!", 404);
        });
        board.setTitle(boardUpdate.getTitle());
        board.setDescription(boardUpdate.getDescription());
        board.setBoardType(boardUpdate.getBoardType());
        board.setWorkspace(board.getWorkspace());
        board.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));
        board.setUpdatedBy(authUserService.getCurrentAuthUser().getId());
        return new ApiResponse<>(boardRepository.save(board));
    }

    public ApiResponse<Void> delete(Long id) {
        Board board = boardRepository.findById(id).orElseThrow(() -> {
            throw new GenericNotFoundException("Board not found!", 404);
        });
        board.setDeleted(true);
        boardRepository.save(board);
        return new ApiResponse<>(200);
    }
}
