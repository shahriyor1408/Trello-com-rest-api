package com.company.trellocomrest.dtos;

import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuditableDto {
    protected Long id;
    protected Timestamp createdAt;
    protected Long createdBy;
    protected Timestamp updatedAt;
    protected Long updatedBy;
    protected boolean isDeleted;
}
