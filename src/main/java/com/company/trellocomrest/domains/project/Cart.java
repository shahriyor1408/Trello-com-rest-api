package com.company.trellocomrest.domains.project;

import com.company.trellocomrest.domains.Auditable;
import com.company.trellocomrest.domains.auth.AuthUser;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Cart extends Auditable {
    private String title;
    private int cartOrder;
    private String description;
    private Timestamp startingTime;
    private Timestamp deadline;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private ProjectColumn projectColumn;

    @ManyToMany(targetEntity = AuthUser.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            joinColumns = @JoinColumn(name = "cart_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "auth_user_id", referencedColumnName = "id")
    )
    private List<AuthUser> authUsers;

    @Builder(builderMethodName = "childBuilder")
    public Cart(Long id, Timestamp createdAt,
                Long createdBy, Timestamp updatedAt,
                Long updatedBy, boolean isDeleted,
                String title, int cartOrder,
                String description, Timestamp startingTime,
                Timestamp deadline, ProjectColumn projectColumn, List<AuthUser> authUsers) {
        super(id, createdAt, createdBy, updatedAt, updatedBy, isDeleted);
        this.title = title;
        this.cartOrder = cartOrder;
        this.description = description;
        this.startingTime = startingTime;
        this.deadline = deadline;
        this.projectColumn = projectColumn;
        this.authUsers = authUsers;
    }
}
