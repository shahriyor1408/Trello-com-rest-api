package com.company.trellocomrest.dtos.project.comment;

import com.company.trellocomrest.dtos.AuditableDto;
import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto extends AuditableDto {
    private String message;

    @Builder(builderMethodName = "childBuilder")
    public CommentDto(Long id, Timestamp createdAt, Long createdBy, Timestamp updatedAt, Long updatedBy, boolean isDeleted, String message) {
        super(id, createdAt, createdBy, updatedAt, updatedBy, isDeleted);
        this.message = message;
    }
}
