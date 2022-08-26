package com.company.trellocomrest.domains.project;

import com.company.trellocomrest.domains.Auditable;
import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.sql.Timestamp;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProjectColumn extends Auditable {
    private String title;
    private int columnOrder;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Board board;

    @Builder(builderMethodName = "childBuilder")
    public ProjectColumn(Long id, Timestamp createdAt,
                         Long createdBy, Timestamp updatedAt,
                         Long updatedBy, boolean isDeleted,
                         String title, int columnOrder, Board board) {
        super(id, createdAt, createdBy, updatedAt, updatedBy, isDeleted);
        this.title = title;
        this.columnOrder = columnOrder;
        this.board = board;
    }
}
