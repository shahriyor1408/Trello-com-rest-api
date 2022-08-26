package com.company.trellocomrest.dtos.project;

import com.company.trellocomrest.dtos.AuditableDto;
import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WorkspaceDto extends AuditableDto {
    private String name;
    private String description;
    private String workspaceType;
    private String workspaceVisibility;

    @Builder(builderMethodName = "childBuilder")
    public WorkspaceDto(Long id, Timestamp createdAt,
                        Long createdBy, Timestamp updatedAt,
                        Long updatedBy, boolean isDeleted,
                        String name, String description,
                        String workspaceType, String workspaceVisibility) {
        super(id, createdAt, createdBy, updatedAt, updatedBy, isDeleted);
        this.name = name;
        this.description = description;
        this.workspaceType = workspaceType;
        this.workspaceVisibility = workspaceVisibility;
    }
}
