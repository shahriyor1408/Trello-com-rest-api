package com.company.trellocomrest.dtos.project;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardUpdateDto {
    @NotNull(message = "Id can not be null!")
    private Long id;

    @NotBlank(message = "Title can not be blank!")
    private String title;

    @NotBlank(message = "Description can not be blank!")
    private String description;

    @NotBlank(message = "Board type can not be blank!")
    private String boardType;
}
