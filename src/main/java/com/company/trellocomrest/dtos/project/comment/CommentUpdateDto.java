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
public class CommentUpdateDto {
    @NotNull(message = "Comment id can not be null!")
    private Long id;

    @NotBlank(message = "Message can not be blank!")
    private String message;
}
