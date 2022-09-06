package com.company.trellocomrest.dtos.project.comment;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommentCreateDto {
    @NotBlank(message = "Message can not be blank!")
    private String message;

    @NotNull(message = "Cart id can not be null!")
    private Long cartId;
}
