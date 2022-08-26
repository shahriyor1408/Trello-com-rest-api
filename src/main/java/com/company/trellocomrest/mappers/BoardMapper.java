package com.company.trellocomrest.mappers;

import com.company.trellocomrest.domains.project.Board;
import com.company.trellocomrest.dtos.project.BoardCreateDto;
import com.company.trellocomrest.dtos.project.BoardUpdateDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BoardMapper {
    Board fromCreateDto(BoardCreateDto dto);

    Board fromUpdateDto(BoardUpdateDto boardUpdateDto);
}
