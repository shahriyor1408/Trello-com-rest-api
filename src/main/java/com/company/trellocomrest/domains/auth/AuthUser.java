package com.company.trellocomrest.domains.auth;

import com.company.trellocomrest.domains.auth.AuthRole;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    private int loginTryCount;

    @Column(unique = true, nullable = false)
    private String email;

    private LocalDateTime lastLoginTime;

    @Builder.Default
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status = Status.NOT_ACTIVE;


    @ManyToMany(targetEntity = AuthRole.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "auth_user_auth_role",
            joinColumns = @JoinColumn(name = "auth_user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "auth_role_id", referencedColumnName = "id")
    )
    private Collection<AuthRole> roles;

    public enum Status {
        ACTIVE, NOT_ACTIVE, BLOCKED;
    }

    public boolean isActive() {
        return Status.ACTIVE.equals(this.status);
    }
}