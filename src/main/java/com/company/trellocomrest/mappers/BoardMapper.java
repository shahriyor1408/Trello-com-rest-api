package com.company.trellocomrest.mappers;

import com.company.trellocomrest.domains.project.Board;
import com.company.trellocomrest.dtos.project.board.BoardCreateDto;
import com.company.trellocomrest.dtos.project.board.BoardDto;
import com.company.trellocomrest.dtos.project.board.BoardUpdateDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BoardMapper {
    Board fromCreateDto(BoardCreateDto dto);

    void fromUpdateDto(BoardUpdateDto boardUpdateDto, @MappingTarget Board board);

    List<BoardDto> toDto(List<Board> board);

    BoardDto toDto(Board board);
}
