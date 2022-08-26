package com.company.trellocomrest.repository.auth;

import com.company.trellocomrest.domains.auth.AuthRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthRoleRepository extends JpaRepository<AuthRole, Long> {
}
