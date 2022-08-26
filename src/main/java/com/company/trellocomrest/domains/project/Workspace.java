package com.company.trellocomrest.domains.project;

import com.company.trellocomrest.domains.Auditable;
import com.company.trellocomrest.domains.auth.AuthUser;
import com.company.trellocomrest.enums.WorkspaceType;
import com.company.trellocomrest.enums.WorkspaceVisibility;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Workspace extends Auditable {
    private String name;
    private String description;

    @Enumerated(EnumType.STRING)
    private WorkspaceType workSpaceType;
    @Enumerated(EnumType.STRING)
    private WorkspaceVisibility workspaceVisibility;

    @ManyToMany(targetEntity = AuthUser.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            joinColumns = @JoinColumn(name = "workspace_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "auth_user_id", referencedColumnName = "id")
    )
    private List<AuthUser> authUsers;

    @Builder(builderMethodName = "childBuilder")
    public Workspace(Long id, Timestamp createdAt,
                     Long createdBy, Timestamp updatedAt,
                     Long updatedBy, boolean isDeleted,
                     String name, String description,
                     WorkspaceType workSpaceType,
                     WorkspaceVisibility workspaceVisibility,
                     List<AuthUser> authUsers) {
        super(id, createdAt, createdBy, updatedAt, updatedBy, isDeleted);
        this.name = name;
        this.description = description;
        this.workSpaceType = workSpaceType;
        this.workspaceVisibility = workspaceVisibility;
        this.authUsers = authUsers;
    }
}
