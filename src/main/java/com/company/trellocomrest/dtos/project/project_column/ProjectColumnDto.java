package com.company.trellocomrest.dtos.project.project_column;

import com.company.trellocomrest.dtos.AuditableDto;
import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProjectColumnDto extends AuditableDto {
    private String title;
    private int columnOrder;

    @Builder(builderMethodName = "childBuilder")
    public ProjectColumnDto(Long id, Timestamp createdAt, Long createdBy, Timestamp updatedAt, Long updatedBy, boolean isDeleted, String title, int columnOrder) {
        super(id, createdAt, createdBy, updatedAt, updatedBy, isDeleted);
        this.title = title;
        this.columnOrder = columnOrder;
    }
}
