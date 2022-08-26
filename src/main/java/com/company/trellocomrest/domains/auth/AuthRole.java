package com.company.trellocomrest.domains.auth;

import com.company.trellocomrest.domains.auth.AuthPermission;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "auth_role")
public class AuthRole implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String code;

    private String name;


    @ManyToMany(targetEntity = AuthPermission.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "auth_role_auth_permission",
            joinColumns = @JoinColumn(name = "auth_role_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "auth_permission_id", referencedColumnName = "id")
    )
    private Collection<AuthPermission> permissions;

    @Override
    public String getAuthority() {
        return "ROLE_" + this.code;
    }
}