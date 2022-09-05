package com.company.trellocomrest.dtos.project.board;

import com.company.trellocomrest.dtos.AuditableDto;
import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BoardDto extends AuditableDto {
    private String title;
    private String description;
    private String boardType;

    @Builder(builderMethodName = "childBuilder")
    public BoardDto(Long id, Timestamp createdAt, Long createdBy, Timestamp updatedAt, Long updatedBy, boolean isDeleted, String title, String description, String boardType) {
        super(id, createdAt, createdBy, updatedAt, updatedBy, isDeleted);
        this.title = title;
        this.description = description;
        this.boardType = boardType;
    }
}
