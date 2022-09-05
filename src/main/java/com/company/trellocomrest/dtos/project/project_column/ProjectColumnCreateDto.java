package com.company.trellocomrest.dtos.project.project_column;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProjectColumnCreateDto {
    @NotBlank(message = "Title can not be null!")
    private String title;

    @NotNull(message = "Board id can not be null!")
    private Long boardId;
}
