package com.company.trellocomrest.mappers;

import com.company.trellocomrest.domains.project.Comment;
import com.company.trellocomrest.dtos.project.comment.CommentCreateDto;
import com.company.trellocomrest.dtos.project.comment.CommentDto;
import com.company.trellocomrest.dtos.project.comment.CommentUpdateDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    Comment fromCreateDto(CommentCreateDto dto);

    CommentDto toDto(Comment comment);

    List<CommentDto> toDto(List<Comment> comment);

    void fromUpdateDto(CommentUpdateDto commentUpdateDto, @MappingTarget Comment comment);
}
