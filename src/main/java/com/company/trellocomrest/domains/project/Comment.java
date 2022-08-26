package com.company.trellocomrest.domains.project;

import com.company.trellocomrest.domains.Auditable;
import com.company.trellocomrest.domains.auth.AuthUser;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Comment extends Auditable {
    private String message;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private AuthUser authUser;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Cart cart;

    @Builder(builderMethodName = "childBuilder")
    public Comment(Long id, Timestamp createdAt,
                   Long createdBy, Timestamp updatedAt,
                   Long updatedBy, boolean isDeleted,
                   String message, AuthUser authUser, Cart cart) {
        super(id, createdAt, createdBy, updatedAt, updatedBy, isDeleted);
        this.message = message;
        this.authUser = authUser;
        this.cart = cart;
    }
}
