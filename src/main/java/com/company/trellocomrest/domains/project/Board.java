package com.company.trellocomrest.domains.project;

import com.company.trellocomrest.domains.Auditable;
import com.company.trellocomrest.domains.auth.AuthUser;
import com.company.trellocomrest.enums.BoardType;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Board extends Auditable {
    private String title;
    private String description;
    @Enumerated(EnumType.STRING)
    private BoardType boardType;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Workspace workspace;

    @ManyToMany(targetEntity = AuthUser.class, fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinTable(
            joinColumns = @JoinColumn(name = "board_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "auth_user_id", referencedColumnName = "id")
    )
    private List<AuthUser> authUsers;

    @Builder(builderMethodName = "childBuilder")
    public Board(Long id, Timestamp createdAt,
                 Long createdBy, Timestamp updatedAt,
                 Long updatedBy, boolean isDeleted,
                 String title, String description,
                 BoardType boardType, Workspace workspace,
                 List<AuthUser> authUsers) {
        super(id, createdAt, createdBy, updatedAt, updatedBy, isDeleted);
        this.title = title;
        this.description = description;
        this.boardType = boardType;
        this.workspace = workspace;
        this.authUsers = authUsers;
    }
}
