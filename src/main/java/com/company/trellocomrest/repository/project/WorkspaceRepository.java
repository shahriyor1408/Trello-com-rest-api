package com.company.trellocomrest.repository.project;

import com.company.trellocomrest.domains.project.Workspace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WorkspaceRepository extends JpaRepository<Workspace, Long> {
    @Query(nativeQuery = true, value = "select * from workspace where id in (select workspace_id from workspace_auth_users where auth_user_id = :in_id)")
    List<Workspace> findAllNotDeleted(@Param(value = "in_id") Long id);
}
