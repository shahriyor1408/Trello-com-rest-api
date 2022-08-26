package com.company.trellocomrest.domains;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;
    @Builder.Default
    protected Timestamp createdAt = Timestamp.valueOf(LocalDateTime.now());
    protected Long createdBy;
    protected Timestamp updatedAt;
    protected Long updatedBy;
    protected boolean isDeleted;
}
