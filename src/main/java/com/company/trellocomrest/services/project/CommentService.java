package com.company.trellocomrest.services.project;

import com.company.trellocomrest.domains.project.Cart;
import com.company.trellocomrest.domains.project.Comment;
import com.company.trellocomrest.dtos.project.comment.CommentCreateDto;
import com.company.trellocomrest.dtos.project.comment.CommentDto;
import com.company.trellocomrest.dtos.project.comment.CommentUpdateDto;
import com.company.trellocomrest.exceptions.GenericNotFoundException;
import com.company.trellocomrest.mappers.CommentMapper;
import com.company.trellocomrest.repository.project.CartRepository;
import com.company.trellocomrest.repository.project.CommentRepository;
import com.company.trellocomrest.response.ApiResponse;
import com.company.trellocomrest.services.auth.AuthUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentMapper commentMapper;
    private final CommentRepository commentRepository;
    private final AuthUserService authUserService;
    private final CartRepository cartRepository;

    public Long create(CommentCreateDto dto) {
        Cart cart = cartRepository.getByIdNotDeleted(dto.getCartId()).orElseThrow(() -> {
            throw new GenericNotFoundException("Cart not found!", 404);
        });
        Comment comment = commentMapper.fromCreateDto(dto);
        comment.setCreatedBy(authUserService.getCurrentAuthUser().getId());
        comment.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
        comment.setCart(cart);
        comment.setAuthUser(authUserService.getCurrentAuthUser());
        return commentRepository.save(comment).getId();
    }

    public CommentDto get(Long id) {
        Comment comment = commentRepository.getByIdNotDeleted(id).orElseThrow(() -> {
            throw new GenericNotFoundException("Comment not found!", 404);
        });
        return commentMapper.toDto(comment);
    }

    public List<CommentDto> getAll(Long id) {
        return commentMapper.toDto(commentRepository.getAllNotDeleted(id));
    }

    public ApiResponse<CommentDto> update(CommentUpdateDto commentUpdateDto) {
        Comment comment = commentRepository.getByIdNotDeleted(commentUpdateDto.getId()).orElseThrow(() -> {
            throw new GenericNotFoundException("Comment not found!", 404);
        });
        commentMapper.fromUpdateDto(commentUpdateDto, comment);
        comment.setUpdatedBy(authUserService.getCurrentAuthUser().getId());
        comment.setUpdatedAt(Timestamp.valueOf(LocalDateTime.now()));
        return new ApiResponse<>(commentMapper.toDto(commentRepository.save(comment)));
    }

    public ApiResponse<Void> delete(Long id) {
        Comment comment = commentRepository.getByIdNotDeleted(id).orElseThrow(() -> {
            throw new GenericNotFoundException("Comment not found!", 404);
        });
        comment.setDeleted(true);
        commentRepository.save(comment);
        return new ApiResponse<>(200, true);
    }
}
