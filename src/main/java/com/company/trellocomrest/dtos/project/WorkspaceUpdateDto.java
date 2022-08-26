package com.company.trellocomrest.dtos.project;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WorkspaceUpdateDto {
    @NotNull(message = "Id can not be null!")
    private Long id;
    @NotBlank(message = "Name can not be blank!")
    private String name;

    @NotBlank(message = "Description can not be blank!")
    private String description;

    @NotNull(message = "Workspace type can not be null!")
    private String workSpaceType;

    @NotNull(message = "Visibility can not be null!")
    private String visibility;
}
