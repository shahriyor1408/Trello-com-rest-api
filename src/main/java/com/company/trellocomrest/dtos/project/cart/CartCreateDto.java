package com.company.trellocomrest.dtos.project.cart;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartCreateDto {
    @NotBlank(message = "Title can not be blank!")
    private String title;

    @NotBlank(message = "Description can not be blank!")
    private String description;

//    @ValidLocalDateTime(message = "Starting time must be in the future time!")
    private Timestamp startingTime;

//    @ValidLocalDateTime(message = "Deadline time must be in the future time!")
    private Timestamp deadline;

    @NotNull(message = "Project column id can not be null!")
    private Long projectColumnId;
}
