package com.company.trellocomrest.events.project;

import lombok.*;

import javax.validation.constraints.NotNull;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProjectColumnMoveEvent {
    @NotNull(message = "Column id can not be null!")
    private Long id;

    @NotNull(message = "Old order can not be null!")
    private int newOrder;

    @NotNull(message = "Board id can not be null!")
    private Long boardId;
}
