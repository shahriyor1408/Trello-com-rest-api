package com.company.trellocomrest.dtos.project;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProjectColumnUpdateDto {
    @NotBlank(message = "Title can not be null!")
    private String title;

    @NotNull(message = "Column order can not be null!")
    private int columnOrder;
}
